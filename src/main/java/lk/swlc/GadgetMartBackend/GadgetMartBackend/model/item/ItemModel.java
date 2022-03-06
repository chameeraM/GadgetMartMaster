
package lk.swlc.GadgetMartBackend.GadgetMartBackend.model.item;

import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ItemModel {
    private long id;
    private String itemid;
    private String brandid;
    private String subcatid;
    private String name;
    private String status;
    private String imageUrl;
    private float price;
    private double discount;
    private int warranty;
    private int qty;
    private String itemtype;
    private String longdes;
    private String shortdes;
}
