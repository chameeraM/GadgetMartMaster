
package lk.swlc.GadgetMartBackend.GadgetMartBackend.model.item;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Item {

    private String name;
    private String id;
    private String price;
    private String discount;
    private String warranty;
    private String qty;
    private String imageUrl;
    private String itemtype;
    private String longdes;
    private String shortdes;
    private String Brand;
    private String SubCategory;
    private String createdAt;
    private String updatedAt;
    private String __v;
}
