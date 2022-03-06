

package lk.swlc.GadgetMartBackend.GadgetMartBackend.model.order;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderModel {

    private long id;
    private String customer;

    private String contact;
    private String date;
    private double fullAmount;
    private String orderAddress;
}
