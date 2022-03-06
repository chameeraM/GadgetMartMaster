

package lk.swlc.GadgetMartBackend.GadgetMartBackend.model.orderdetail;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.model.order.NodeOrderModelChange;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class NodeOrderOrderDetails {

    @JsonProperty("order")
    private NodeOrderModelChange nodeOrderModelChange;

    @JsonProperty("orderDetails")
    private List<OrderDetailsSearchNodeModel> orderDetailsSearchNodeModels=new ArrayList<>();

}
