

package lk.swlc.GadgetMartBackend.GadgetMartBackend.model.order;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class NodeOrderReturnModel {


    private String message;

    @JsonProperty("data")
    private NodeOrderModel nodeOrderModels;

}
