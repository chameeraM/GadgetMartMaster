

package lk.swlc.GadgetMartBackend.GadgetMartBackend.model.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.orderdetail.OrderDetailsSearchModel;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderSearchSpringModel {

    private long id;
    private String customer;

    private String contact;
    private String date;
    private double fullAmount;
    private String orderAddress;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<OrderDetailsSearchModel> orderDetailsModels=new ArrayList<>();
}
