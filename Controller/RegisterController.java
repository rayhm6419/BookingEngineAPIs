package com.laioffer.booking.Controller;


import com.laioffer.booking.model.User;
import com.laioffer.booking.model.UserRole;
import com.laioffer.booking.service.RegisterService;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // -> respondBody 和 Controller define在一起了
public class RegisterController {

    private RegisterService registerService;

    public RegisterController(RegisterService registerService){
        this.registerService = registerService;
    }

    //RequestMapping
    //define出这个user是guest还是host，通过不同请求，存到数据库
    @PostMapping("/register/guest")
    public void addGuest(@RequestBody User user) {
        registerService.add(user, UserRole.ROLE_GUEST);
    }

    @PostMapping("/register/host")
    public void addHost(@RequestBody User user) {
        registerService.add(user, UserRole.ROLE_HOST);
    }

}
