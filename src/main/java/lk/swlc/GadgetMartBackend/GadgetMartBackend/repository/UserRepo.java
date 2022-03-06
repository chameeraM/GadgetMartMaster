package lk.swlc.GadgetMartBackend.GadgetMartBackend.repository;

import lk.swlc.GadgetMartBackend.GadgetMartBackend.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<ApplicationUser, Long> {

    @Query("SELECT u FROM ApplicationUser u WHERE u.userName=?1")
    ApplicationUser findByUsername(String username);

}
