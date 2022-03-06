

package lk.swlc.GadgetMartBackend.GadgetMartBackend.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userid;
    private String mainorderid;
    @Nullable
    private String apioneorderid;
    @Nullable
    private String apitwoorderid;
    @CreationTimestamp
    private Date createdAt;

    @CreationTimestamp
    private Date updatedAt;
}
