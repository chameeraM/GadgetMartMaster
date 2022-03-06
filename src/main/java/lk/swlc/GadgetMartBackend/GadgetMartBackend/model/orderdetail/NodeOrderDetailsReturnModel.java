
package lk.swlc.GadgetMartBackend.GadgetMartBackend.model.orderdetail;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class NodeOrderDetailsReturnModel {

    private String message;

    @JsonProperty("data")
    private NodeOrderOrderDetails nodeOrderOrderDetails;
}
