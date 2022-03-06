

package lk.swlc.GadgetMartBackend.GadgetMartBackend.model.subcategory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@XmlRootElement
public class SubCategorySingleModel {

    private String message;


    @JsonProperty("data")
    private SubCategory subCategory;
}
