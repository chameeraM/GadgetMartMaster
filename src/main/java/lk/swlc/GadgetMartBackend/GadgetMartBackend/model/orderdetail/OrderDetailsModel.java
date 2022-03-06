

package lk.swlc.GadgetMartBackend.GadgetMartBackend.model.orderdetail;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderDetailsModel {

    private String id;
    private String item;
    private int qty;
    private double price;
    private String itemtype;

    public OrderDetailsModel(String item, int qty,String itemtype) {
        this.item = item;
        this.qty = qty;
        this.itemtype=itemtype;

    }
}
