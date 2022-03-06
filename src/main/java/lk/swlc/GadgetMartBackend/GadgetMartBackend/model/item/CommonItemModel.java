

package lk.swlc.GadgetMartBackend.GadgetMartBackend.model.item;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommonItemModel {

    private String id;
    private String name;
    private String price;
    private String discount;
    private String warranty;
    private String qty;
    private String imageUrl;
    private String Brand;
    private String SubCategory;
    private String itemtype;
    private String longdes;
    private String shortdes;
}
