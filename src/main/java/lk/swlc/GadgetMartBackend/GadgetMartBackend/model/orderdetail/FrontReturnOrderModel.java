

package lk.swlc.GadgetMartBackend.GadgetMartBackend.model.orderdetail;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FrontReturnOrderModel {

    private long id;
    private String customer;

    private String contact;
    private String date;
    private double fullAmount;
    private String orderAddress;
    List<OrderDetailsModel> orderDetailsModels=new ArrayList<>();
}
