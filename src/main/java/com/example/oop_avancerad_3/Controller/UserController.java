package com.example.oop_avancerad_3.Controller;

import com.example.oop_avancerad_3.Entity.User;
import com.example.oop_avancerad_3.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/loginUser")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpServletResponse response
    ){
        System.out.println("logging in...");
        User user = userService.getUserByUsername(username);

        if(user != null && userService.authUser(username, password)){
            Cookie cookie = new Cookie("currentUser", user.getUserIdString());
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);
            System.out.println("loggedin");
            return "redirect:/success";
        }
        System.out.println("login fail");
        return "redirect:/fail";
    }

    @GetMapping("/fail")
    public String failed(Model model){
        model.addAttribute("msg", "Wrong username or password.");
        return "signin";
    }
    @GetMapping("/success")
    public String success(@ModelAttribute("user") User user,
                          Model model){
        model.addAttribute("msg", "You are logged in!");
        return "signin";
    }
}
