
package lk.swlc.GadgetMartBackend.GadgetMartBackend.model.order;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddOrderModel {

    private long userid;
    private String username;
    private CommonOrderModel commonOrderModel;

}
