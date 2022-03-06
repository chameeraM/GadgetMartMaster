

package lk.swlc.GadgetMartBackend.GadgetMartBackend.model.orderdetail;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetailsSearchNodeModel {

    private String id;
    private String item;
    private String qty;
    private String price;
    private String order;
    private String createdAt;
    private String updatedAt;
    private String __v;
}
