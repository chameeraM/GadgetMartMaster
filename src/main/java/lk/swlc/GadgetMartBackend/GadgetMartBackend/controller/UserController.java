package lk.swlc.GadgetMartBackend.GadgetMartBackend.controller;

import lk.swlc.GadgetMartBackend.GadgetMartBackend.entity.ApplicationUser;
import lk.swlc.GadgetMartBackend.GadgetMartBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity addUser(@RequestBody ApplicationUser user) {
        ApplicationUser userResponse = userService.add(user);
        if (userResponse == null) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(userResponse, HttpStatus.CREATED);
        }
    }

    @PostMapping("/check-user")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity checkuser(@RequestBody ApplicationUser user) {
        ApplicationUser userResponse = userService.checkuser(user);
        if (userResponse == null) {
            return new ResponseEntity(0, HttpStatus.OK);
        } else {
            return new ResponseEntity(1, HttpStatus.OK);
        }
    }


    @GetMapping("/test")
    public ResponseEntity test() {
        return new ResponseEntity(null, HttpStatus.I_AM_A_TEAPOT);
    }

}
