

package lk.swlc.GadgetMartBackend.GadgetMartBackend.repository;

import lk.swlc.GadgetMartBackend.GadgetMartBackend.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier,Long> {

}
