

package lk.swlc.GadgetMartBackend.GadgetMartBackend.model.order;

import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.orderdetail.OrderDetailsModel;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommonOrderModel {

    private long id;
    private long userid;
    private String customer;

    private String contact;
    private String date;
    private double fullAmount;
    private String orderAddress;
    private List<OrderDetailsModel> orderDetailsModels=new ArrayList<>();

    public CommonOrderModel(long userid,String customer, String contact, double fullAmount, String orderAddress, List<OrderDetailsModel> orderDetailsModels) {
        this.userid = userid;
        this.customer = customer;
        this.contact = contact;
        this.fullAmount = fullAmount;
        this.orderAddress = orderAddress;
        this.orderDetailsModels = orderDetailsModels;
    }

}
