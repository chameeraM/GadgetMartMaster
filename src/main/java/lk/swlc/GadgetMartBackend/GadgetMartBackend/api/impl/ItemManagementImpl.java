

package lk.swlc.GadgetMartBackend.GadgetMartBackend.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.api.ItemManagement;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.exception.DownstreamApi;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.exception.MyRestTemplateException;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemManagementImpl implements ItemManagement {

    @Value("${singerurl}")
    private String singerurl;
    @Value("${abnasurl}")
    private String abnasurl;

    private HttpEntity<String> response;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Return get all Item data from Singer & Abans API
     *
     * @return
     */

    @Override
    public List<CommonItemModel> getalldataAPI() {
        String brandurl = singerurl + "item";
        String brandurl2 = abnasurl + "item/get_all";
        List<ItemModel> itemModel = null;
        ItemNodeModel itemNodeModelreturn = null;

        /**
         * Brand NodeJS API Call
         */

        response = restTemplate.exchange(brandurl2, HttpMethod.GET, null, String.class);
        String resultItemNodeModelString = response.getBody();

        /**
         * Brand Spring Boot API Call
         */

        ResponseEntity<List<ItemModel>> responseEntity = restTemplate.exchange(
                brandurl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ItemModel>>() {
                });
        itemModel = responseEntity.getBody();

        try {
            itemNodeModelreturn = mapper.readValue(resultItemNodeModelString, ItemNodeModel.class);
            return convertIteams(itemNodeModelreturn, itemModel);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public ItemModel searchspringAPI(String s) {
        return null;
    }




    @Override
    public CommonItemModel searchspringnodeAPI(String s) {
        String brandurl = singerurl + "item/" + s;
        String brandurl2 = abnasurl + "item/get/" + s;
        ItemModel itemModel = null;
        ItemSingleModel itemSingleModel = null;

        itemModel = getItemModel(brandurl, itemModel);
        if (itemModel == null) {
            itemSingleModel = getItemSingleModel(brandurl2, itemSingleModel);
        }


        if (itemSingleModel != null) {
            return new CommonItemModel(itemSingleModel.getItem().getId() + "", itemSingleModel.getItem().getName(), itemSingleModel.getItem().getPrice() + "", itemSingleModel.getItem().getDiscount() + "", itemSingleModel.getItem().getWarranty() + "", itemSingleModel.getItem().getQty() + "",itemSingleModel.getItem().getImageUrl(), itemSingleModel.getItem().getBrand(), itemSingleModel.getItem().getSubCategory(),itemSingleModel.getItem().getItemtype(),itemSingleModel.getItem().getLongdes(),itemSingleModel.getItem().getShortdes());
        } else if (itemModel != null) {
            return new CommonItemModel(itemModel.getId() + "", itemModel.getName(), itemModel.getPrice() + "", itemModel.getDiscount() + "", itemModel.getWarranty() + "", itemModel.getQty() + "",itemModel.getImageUrl(), itemModel.getBrandid(), itemModel.getSubcatid(),itemModel.getItemtype(),itemModel.getLongdes(),itemModel.getShortdes());
        } else {
            throw new MyRestTemplateException(DownstreamApi.MY_API_1, HttpStatus.NOT_FOUND, "Data Not Found");
        }
    }

    private ItemSingleModel getItemSingleModel(String brandurl2, ItemSingleModel itemSingleModel) {
        try {
            String resultString;
            /**
             * Item NodeJS API Call
             */
            response = restTemplate.exchange(brandurl2, HttpMethod.GET, null, String.class);
            resultString = response.getBody();
            try {
                itemSingleModel = mapper.readValue(resultString, ItemSingleModel.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return itemSingleModel;
        } catch (final HttpClientErrorException httpClientErrorException) {
            return itemSingleModel;
        } catch (final HttpServerErrorException httpServerErrorException) {
            return itemSingleModel;
        } catch (final Exception exception) {
            return itemSingleModel;
        }

    }

    private ItemModel getItemModel(String brandurl, ItemModel itemModel) {
        try {
            String resultString;
            /**
             * Item NodeJS API Call
             */
            response = restTemplate.exchange(brandurl, HttpMethod.GET, null, String.class);
            resultString = response.getBody();
            try {
                itemModel = mapper.readValue(resultString, ItemModel.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return itemModel;
        } catch (final HttpClientErrorException httpClientErrorException) {
            return itemModel;
        } catch (final HttpServerErrorException httpServerErrorException) {
            return itemModel;
        } catch (final Exception exception) {
            return itemModel;
        }
    }

    @Override
    public ItemModel searchnodeAPI(String s) {
        return null;
    }

    @Override
    public boolean deleteSpringAPI(String s) {
        return false;
    }

    @Override
    public boolean deleteNodeAPI(String s) {
        return false;
    }

    @Override
    public ItemModel postAPI(ItemModel itemModel) {
        return null;
    }

    @Override
    public ItemModel updateAPI(String s, ItemModel itemModel) {
        return null;
    }

    /**
     * Convert Item Spring and Node model to one ArrayList
     *
     * @param itemNodeModelreturn
     * @param itemModel
     * @return
     */

    private List<CommonItemModel> convertIteams(ItemNodeModel itemNodeModelreturn, List<ItemModel> itemModel) {
        List<CommonItemModel> commonItemModels = new ArrayList<>();
        List<Item> items = itemNodeModelreturn.getItems();
        for (Item item : items) {
            commonItemModels.add(new CommonItemModel(item.getId(), item.getName(), item.getPrice(), item.getDiscount(), item.getWarranty(), item.getQty(),item.getImageUrl(), item.getBrand(), item.getSubCategory(),item.getItemtype(),item.getLongdes(),item.getShortdes()));
        }
        for (ItemModel itemModel1 : itemModel) {
            commonItemModels.add(new CommonItemModel(itemModel1.getId() + "", itemModel1.getName(), itemModel1.getPrice() + "", itemModel1.getDiscount() + "", itemModel1.getWarranty() + "", itemModel1.getQty() + "",itemModel1.getImageUrl(), itemModel1.getBrandid(), itemModel1.getSubcatid(),itemModel1.getItemtype(),itemModel1.getLongdes(),itemModel1.getShortdes()));
        }
        return commonItemModels;

    }


}
