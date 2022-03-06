

package lk.swlc.GadgetMartBackend.GadgetMartBackend.model.subcategory;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SubCategorySpringModel {

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<SubCategoryModel> subCategoryModels = new ArrayList<SubCategoryModel>();
}
