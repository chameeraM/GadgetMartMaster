package lk.swlc.GadgetMartBackend.GadgetMartBackend.service;

import lk.swlc.GadgetMartBackend.GadgetMartBackend.entity.ApplicationUser;

public interface UserService {

    public ApplicationUser add(ApplicationUser user);
    public ApplicationUser checkuser(ApplicationUser user);

}
