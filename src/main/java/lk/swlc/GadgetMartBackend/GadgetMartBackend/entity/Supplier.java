

package lk.swlc.GadgetMartBackend.GadgetMartBackend.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String suppliername;
    private String address;
    private String email;
    private String contactnumber;
    private String supplierapiurl;

}
