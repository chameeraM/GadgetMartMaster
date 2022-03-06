

package lk.swlc.GadgetMartBackend.GadgetMartBackend.model.order;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class NodeOrderModelChange{
    private String id;
    private String customer;

    private String contact;
    private String date;
    private String fullAmount;
    private String orderAddress;
    private String createdAt;
    private String updatedAt;
    private String __v;
}
