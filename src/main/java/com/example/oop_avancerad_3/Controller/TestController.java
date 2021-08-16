package com.example.oop_avancerad_3.Controller;

import com.example.oop_avancerad_3.Entity.User;
import com.example.oop_avancerad_3.Repository.UserRepository;
import com.example.oop_avancerad_3.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @Autowired
    UserService userService;
    @GetMapping("/")
    public String index() {
        return "test";
    }
    @GetMapping("/makeNewUser")
    public String test() {
        User user = new User();
        user.setUserName("nr1user");
        user.setFirstName("Per");
        user.setPassword("qwerty");
        userService.saveUser(user);

        return "test";
    }
}
