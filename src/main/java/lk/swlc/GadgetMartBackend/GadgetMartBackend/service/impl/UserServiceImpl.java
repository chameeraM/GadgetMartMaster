package lk.swlc.GadgetMartBackend.GadgetMartBackend.service.impl;

import lk.swlc.GadgetMartBackend.GadgetMartBackend.entity.ApplicationUser;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.repository.UserRepo;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public ApplicationUser add(ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public ApplicationUser checkuser(ApplicationUser user) {
        return userRepo.findByUsername(user.getUserName());
    }
}
