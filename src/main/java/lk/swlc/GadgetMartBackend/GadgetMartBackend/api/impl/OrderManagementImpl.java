

package lk.swlc.GadgetMartBackend.GadgetMartBackend.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.api.OrderManagement;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.entity.ApplicationUser;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.entity.Order;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.order.*;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.orderdetail.*;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.repository.OrderRepo;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.repository.UserRepo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderManagementImpl implements OrderManagement {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserRepo userRepo;

    @Value("${singerurl}")
    private String singerurl;
    @Value("${abnasurl}")
    private String abnasurl;
    private HttpEntity<String> response;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RestTemplate restTemplate;


    private JSONObject orderJsonObject = new JSONObject();
    private JSONObject orderJsonObject2 = new JSONObject();


    private HttpHeaders headers = new HttpHeaders();

    @Override
    public Order addOrderAPI(CommonOrderModel commonOrderModel) {
        String brandurl = singerurl + "order";
        String brandurl2 = abnasurl + "order/add";
        OrderModel orderModel = null;
        int count = 0;
        headers.setContentType(MediaType.APPLICATION_JSON);
        for (OrderDetailsModel orderDetailsModel : commonOrderModel.getOrderDetailsModels()) {
            if (orderDetailsModel.getItemtype().equalsIgnoreCase("Abans")) {
                JSONArray array = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("item", orderDetailsModel.getItem());
                jsonObject.put("qty", orderDetailsModel.getQty());
                array.put(jsonObject);
                orderJsonObject.put("orderDetailsModels", array);
            } else {
                count++;
                JSONArray array = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("item", orderDetailsModel.getItem());
                jsonObject.put("qty", orderDetailsModel.getQty());
                array.put(jsonObject);
                orderJsonObject2.put("orderDetailsModels", array);
            }
        }

        orderJsonObject.put("customer", commonOrderModel.getCustomer());
        orderJsonObject.put("contact", commonOrderModel.getContact());
        orderJsonObject.put("fullAmount", commonOrderModel.getFullAmount());
        orderJsonObject.put("orderAddress", commonOrderModel.getOrderAddress());

        orderJsonObject2.put("customer", commonOrderModel.getCustomer());
        orderJsonObject2.put("contact", commonOrderModel.getContact());
        orderJsonObject2.put("fullAmount", commonOrderModel.getFullAmount());
        orderJsonObject2.put("orderAddress", commonOrderModel.getOrderAddress());


        HttpEntity<String> request = new HttpEntity<String>(orderJsonObject.toString(), headers);
        HttpEntity<String> request2 = new HttpEntity<String>(orderJsonObject2.toString(), headers);

        NodeOrderReturnModel resultBrandNodeModelString = getEntity(brandurl2, request);
        Order order = new Order();
        UUID uuid = UUID.randomUUID();
        order.setMainorderid(uuid.toString());
        ApplicationUser byUsername = userRepo.findByUsername(commonOrderModel.getCustomer());
        order.setUserid(byUsername.getId());
        if (resultBrandNodeModelString == null) {
            orderModel = getOrderModel(brandurl, request2);
            if (orderModel != null) {
                order.setApioneorderid(orderModel.getId() + "");
            }
        } else {
            NodeOrderModelChange nodeOrderModelChange = resultBrandNodeModelString.getNodeOrderModels().getNodeOrderModelChange();
            order.setApitwoorderid(nodeOrderModelChange.getId());
            if (count > 0) {
                orderModel = getOrderModel(brandurl, request2);
                if (orderModel != null) {
                    order.setApioneorderid(orderModel.getId() + "");
                }
            }
        }


        return orderRepo.save(order);

    }

    private OrderModel getOrderModel(String brandurl, HttpEntity<String> request) {
        /**
         * Brand Spring Boot API Call
         */
        try {
            OrderModel orderModel;
            ResponseEntity<OrderModel> responseEntity = restTemplate.exchange(
                    brandurl,
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<OrderModel>() {
                    });
            orderModel = responseEntity.getBody();
            System.out.println(orderModel.toString());
            return orderModel;
        } catch (final HttpClientErrorException httpClientErrorException) {
            return null;
        } catch (final HttpServerErrorException httpServerErrorException) {
            return null;
        } catch (final NumberFormatException numberFormatException) {
            return null;
        } catch (final Exception exception) {
            return null;
        }
    }

    private NodeOrderReturnModel getEntity(String brandurl2, HttpEntity<String> request) {
        /**
         * Brand NodeJS API Call
         */

        try {
            NodeOrderReturnModel nodeOrderReturnModel = null;

            ResponseEntity<NodeOrderReturnModel> responseEntity = restTemplate.exchange(
                    brandurl2,
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<NodeOrderReturnModel>() {
                    });
            nodeOrderReturnModel = responseEntity.getBody();
            return nodeOrderReturnModel;


//            response = restTemplate.exchange(brandurl2, HttpMethod.POST, request, String.class);
//            String resultBrandNodeModelString = response.getBody();
//            return resultBrandNodeModelString;
        } catch (final HttpClientErrorException httpClientErrorException) {
            return null;
        } catch (final HttpServerErrorException httpServerErrorException) {
            return null;
        } catch (final Exception exception) {
            return null;
        }

    }


    @Override
    public NodeOrderReturnModel checkfunction(CommonOrderModel commonOrderModel) {
        String brandurl2 = abnasurl + "order/add";
        OrderModel orderModel = null;
        headers.setContentType(MediaType.APPLICATION_JSON);

        for (OrderDetailsModel orderDetailsModel : commonOrderModel.getOrderDetailsModels()) {
            JSONArray array = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("item", orderDetailsModel.getItem());
            jsonObject.put("qty", orderDetailsModel.getQty());
            array.put(jsonObject);
            orderJsonObject.put("orderDetailsModels", array);
        }

        orderJsonObject.put("customer", commonOrderModel.getCustomer());
        orderJsonObject.put("contact", commonOrderModel.getContact());
        orderJsonObject.put("fullAmount", commonOrderModel.getFullAmount());
        orderJsonObject.put("orderAddress", commonOrderModel.getOrderAddress());

        HttpEntity<String> request = new HttpEntity<String>(orderJsonObject.toString(), headers);
        try {
            NodeOrderReturnModel nodeOrderReturnModel = null;

            ResponseEntity<NodeOrderReturnModel> responseEntity = restTemplate.exchange(
                    brandurl2,
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<NodeOrderReturnModel>() {
                    });
            nodeOrderReturnModel = responseEntity.getBody();
            return nodeOrderReturnModel;


//            response = restTemplate.exchange(brandurl2, HttpMethod.POST, request, String.class);
//            String resultBrandNodeModelString = response.getBody();
//            return resultBrandNodeModelString;
        } catch (final HttpClientErrorException httpClientErrorException) {
            return null;
        } catch (final HttpServerErrorException httpServerErrorException) {
            return null;
        } catch (final Exception exception) {
            return null;
        }


    }

    @Override
    public List<FrontReturnOrderModel> searchorder(String username) {
        ApplicationUser byUsername = userRepo.findByUsername(username);
        NodeOrderDetailsReturnModel nodeOrderDetailsReturnModel = null;
        List<Order> orderByUserid = null;

        List<FrontReturnOrderModel> frontReturnOrderModels = new ArrayList<>();
        OrderSearchSpringModel commonOrderModels = null;
        if (byUsername != null) {
            orderByUserid = orderRepo.findOrderByUserid(byUsername.getId());
            for (Order order : orderByUserid) {
                if (order.getApioneorderid() != null) {
                    commonOrderModels = springorderget(order);
                    if (commonOrderModels != null) {
                        List<OrderDetailsSearchModel> orderDetailsModels = commonOrderModels.getOrderDetailsModels();
                        List<OrderDetailsModel> orderDetailsModels1 = new ArrayList<>();
                        for (OrderDetailsSearchModel orderDetailsSearchModel : orderDetailsModels) {
                            orderDetailsModels1.add(new OrderDetailsModel(orderDetailsSearchModel.getId() + "", orderDetailsSearchModel.getItem(), orderDetailsSearchModel.getQty(), orderDetailsSearchModel.getPrice(), "Singer"));
                        }
                        frontReturnOrderModels.add(new FrontReturnOrderModel(order.getId(), commonOrderModels.getCustomer(), commonOrderModels.getContact(), commonOrderModels.getDate(), commonOrderModels.getFullAmount(), commonOrderModels.getOrderAddress(), orderDetailsModels1));
                    }
                }
                if (order.getApitwoorderid() != null) {
                    nodeOrderDetailsReturnModel = nodeorderget(order);
                    if (nodeOrderDetailsReturnModel != null) {
                        List<OrderDetailsModel> orderDetailsModels1 = new ArrayList<>();
                        List<OrderDetailsSearchNodeModel> orderDetailsSearchNodeModels = nodeOrderDetailsReturnModel.getNodeOrderOrderDetails().getOrderDetailsSearchNodeModels();
                        for (OrderDetailsSearchNodeModel orderDetailsSearchNodeModel : orderDetailsSearchNodeModels) {
                            orderDetailsModels1.add(new OrderDetailsModel(orderDetailsSearchNodeModel.getOrder(), orderDetailsSearchNodeModel.getItem(), Integer.parseInt(orderDetailsSearchNodeModel.getQty()), Double.parseDouble(orderDetailsSearchNodeModel.getPrice()), "Abans"));
                        }
                        NodeOrderModelChange nodeOrderModelChange = nodeOrderDetailsReturnModel.getNodeOrderOrderDetails().getNodeOrderModelChange();
                        frontReturnOrderModels.add(new FrontReturnOrderModel(order.getId(), nodeOrderModelChange.getCustomer(), nodeOrderModelChange.getContact(), nodeOrderModelChange.getDate(), Double.parseDouble(nodeOrderModelChange.getFullAmount()), nodeOrderModelChange.getOrderAddress(), orderDetailsModels1));
                    }
                }
            }
        }

        return frontReturnOrderModels;

    }

    @Override
    public List<FrontReturnOrderModel> getallOrders() {


        NodeOrderDetailsReturnModel nodeOrderDetailsReturnModel = null;
        List<Order> orderByUserid = null;

        List<FrontReturnOrderModel> frontReturnOrderModels = new ArrayList<>();
        OrderSearchSpringModel commonOrderModels = null;

        if (true) {
            orderByUserid = orderRepo.findAll();
            for (Order order : orderByUserid) {
                if (order.getApioneorderid() != null) {
                    commonOrderModels = springorderget(order);
                    if (commonOrderModels != null) {
                        List<OrderDetailsSearchModel> orderDetailsModels = commonOrderModels.getOrderDetailsModels();
                        List<OrderDetailsModel> orderDetailsModels1 = new ArrayList<>();
                        for (OrderDetailsSearchModel orderDetailsSearchModel : orderDetailsModels) {
                            orderDetailsModels1.add(new OrderDetailsModel(orderDetailsSearchModel.getId() + "", orderDetailsSearchModel.getItem(), orderDetailsSearchModel.getQty(), orderDetailsSearchModel.getPrice(), "Singer"));
                        }
                        frontReturnOrderModels.add(new FrontReturnOrderModel(order.getId(), commonOrderModels.getCustomer(), commonOrderModels.getContact(), commonOrderModels.getDate(), commonOrderModels.getFullAmount(), commonOrderModels.getOrderAddress(), orderDetailsModels1));
                    }
                }
                if (order.getApitwoorderid() != null) {
                    nodeOrderDetailsReturnModel = nodeorderget(order);
                    if (nodeOrderDetailsReturnModel != null) {
                        List<OrderDetailsModel> orderDetailsModels1 = new ArrayList<>();
                        List<OrderDetailsSearchNodeModel> orderDetailsSearchNodeModels = nodeOrderDetailsReturnModel.getNodeOrderOrderDetails().getOrderDetailsSearchNodeModels();
                        for (OrderDetailsSearchNodeModel orderDetailsSearchNodeModel : orderDetailsSearchNodeModels) {
                            orderDetailsModels1.add(new OrderDetailsModel(orderDetailsSearchNodeModel.getOrder(), orderDetailsSearchNodeModel.getItem(), Integer.parseInt(orderDetailsSearchNodeModel.getQty()), Double.parseDouble(orderDetailsSearchNodeModel.getPrice()), "Abans"));
                        }
                        NodeOrderModelChange nodeOrderModelChange = nodeOrderDetailsReturnModel.getNodeOrderOrderDetails().getNodeOrderModelChange();
                        frontReturnOrderModels.add(new FrontReturnOrderModel(order.getId(), nodeOrderModelChange.getCustomer(), nodeOrderModelChange.getContact(), nodeOrderModelChange.getDate(), Double.parseDouble(nodeOrderModelChange.getFullAmount()), nodeOrderModelChange.getOrderAddress(), orderDetailsModels1));
                    }
                }
            }
        }

        return frontReturnOrderModels;
    }

    public OrderSearchSpringModel springorderget(Order order) {
        String brandurl = singerurl + "order/" + order.getApioneorderid();
        try {
            ResponseEntity<OrderSearchSpringModel> responseEntity = restTemplate.exchange(
                    brandurl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<OrderSearchSpringModel>() {
                    });
            return responseEntity.getBody();
        } catch (final HttpClientErrorException httpClientErrorException) {
            return null;
        } catch (final HttpServerErrorException httpServerErrorException) {
            return null;
        } catch (final Exception exception) {
            return null;
        }

    }

    public NodeOrderDetailsReturnModel nodeorderget(Order order) {
        String brandurl = abnasurl + "order/get/" + order.getApitwoorderid();
        try {
            ResponseEntity<NodeOrderDetailsReturnModel> responseEntity = restTemplate.exchange(
                    brandurl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<NodeOrderDetailsReturnModel>() {
                    });
            return responseEntity.getBody();

        } catch (final HttpClientErrorException httpClientErrorException) {
            return null;
        } catch (final HttpServerErrorException httpServerErrorException) {
            return null;
        } catch (final Exception exception) {
            return null;
        }
    }


}


