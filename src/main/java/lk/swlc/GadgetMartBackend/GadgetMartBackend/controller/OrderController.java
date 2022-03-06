
package lk.swlc.GadgetMartBackend.GadgetMartBackend.controller;

import lk.swlc.GadgetMartBackend.GadgetMartBackend.api.OrderManagement;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.entity.Order;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.order.CommonOrderModel;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.order.NodeOrderReturnModel;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.order.OrderModel;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.order.OrderSearchSpringModel;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.orderdetail.FrontReturnOrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@CrossOrigin
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderManagement orderManagement;
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity addOrder(@RequestBody CommonOrderModel commonOrderModel) {
        Order order = orderManagement.addOrderAPI(commonOrderModel);
        if (order == null) {
            return new ResponseEntity(order, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(order, HttpStatus.OK);
        }

    }

    @PostMapping(value = "/check")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity check(@RequestBody CommonOrderModel commonOrderModel) {
        NodeOrderReturnModel checkfunction = orderManagement.checkfunction(commonOrderModel);
        if (checkfunction == null) {
            return new ResponseEntity(checkfunction, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(checkfunction, HttpStatus.OK);
        }

    }

    @GetMapping(value = "/{username}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity search(@PathVariable(value = "username") String username) {
        List<FrontReturnOrderModel> searchorder = orderManagement.searchorder(username);
        if (searchorder == null) {
            return new ResponseEntity(searchorder, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(searchorder, HttpStatus.OK);
        }

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getallOrader() {
        List<FrontReturnOrderModel> searchorder = orderManagement.getallOrders();
        if (searchorder == null) {
            return new ResponseEntity(searchorder, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(searchorder, HttpStatus.OK);
        }

    }
}
