

package lk.swlc.GadgetMartBackend.GadgetMartBackend.model.subcategory;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SubCategoryModel {

    private long id;

    private String category;
    private String name;
    private String status;
}
