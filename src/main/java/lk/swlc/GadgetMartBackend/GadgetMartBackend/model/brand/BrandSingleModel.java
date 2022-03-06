

package lk.swlc.GadgetMartBackend.GadgetMartBackend.model.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@XmlRootElement
public class BrandSingleModel {

    private String message;


    @JsonProperty("data")
    private Brand brand;
}
