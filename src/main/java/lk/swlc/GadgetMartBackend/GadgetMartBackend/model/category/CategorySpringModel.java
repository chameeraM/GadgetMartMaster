

package lk.swlc.GadgetMartBackend.GadgetMartBackend.model.category;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CategorySpringModel {

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<CategoryModel> brands = new ArrayList<CategoryModel>();
}
