

package lk.swlc.GadgetMartBackend.GadgetMartBackend.model.brand;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class BrandModel {
    private long id;

    private String brandid;
    private String name;
    private String status;
}
