

package lk.swlc.GadgetMartBackend.GadgetMartBackend.model.subcategory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SubCategoryNodeModel {

    private String message;

    @JsonProperty("data")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<SubCategory> subCategories = new ArrayList<SubCategory>();
}
