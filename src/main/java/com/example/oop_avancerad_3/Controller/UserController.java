package com.example.oop_avancerad_3.Controller;

import com.example.oop_avancerad_3.Entity.Booking;
import com.example.oop_avancerad_3.Entity.Car;
import com.example.oop_avancerad_3.Entity.User;
import com.example.oop_avancerad_3.Service.BookingService;
import com.example.oop_avancerad_3.Service.CarService;
import com.example.oop_avancerad_3.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CarService carService;

    @Autowired
    BookingService bookingService;

    @PostMapping("/saveUser")
    public String saveUserParam(@RequestParam("username") String username,
                                @RequestParam("email") String email,
                                @RequestParam("password") String password,
                                @RequestParam("passwordTwo") String passwordTwo,
                                HttpServletResponse response){
        if(password.equals(passwordTwo)){
            User user = new User();
            user.setUserName(username);
            user.setEmail(email);
            user.setPassword(password);
            userService.saveUser(user);
            Cookie cookie = new Cookie("currentUserId", user.getUserIdString());
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);
            return "redirect:/success";
        }
        return "redirect:/failed";
    }

    @PostMapping("/loginUser")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpServletResponse response){
        User user = userService.getUserByUsername(username);
        System.out.println(user.toString());

        if(user != null && userService.authUser(username, password)){
            Cookie cookie = new Cookie("currentUser", user.getUserIdString());
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);
            System.out.println("loggedin");
            return "redirect:/userLoggedIn/" + user.getUserIdString();

        }
        System.out.println("login fail");
        return "redirect:/fail";
    }

    @GetMapping("/fail")
    public String failed(Model model){
        model.addAttribute("errorsigninmsg", "Wrong username or password.");
        return "signin";
    }

    @GetMapping("/wrongProfile")
    public String failProfile(Model model){
        model.addAttribute("errorsigninmsg", "You tried to access someone elses dashboard.");
        return "signin";
    }
    @GetMapping("/success")
    public String success(@ModelAttribute("user") User user,
                          Model model){
        model.addAttribute("msg", "You are logged in!");
        return "signin";
    }

    @GetMapping("/signup")
    public String signUpView(@ModelAttribute("user") User user) {
        return "signup";
    }

    @GetMapping("/userLoggedIn/{id}")
    public String userLoggedIn(@PathVariable("id") Long id,
                               Model model,
                               @CookieValue("currentUserId") String currentUserId){

        /*
        this code should check if the current user is allowed on this url

        if(Long.valueOf(currentUserId) != id){
            System.out.println("get out of here");
            return "redirect:/wrongProfile";
        }
        */
        System.out.println("current logged in user:");
        System.out.println(currentUserId);
        System.out.println("current dashboard:");
        System.out.println(id);

        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        //this should be in Car Controller
        Collection<Car> cars = carService.findCarsByUser(user);
        model.addAttribute("cars", cars);

        //this should be in Booking Controller
        Collection<Booking> bookings = bookingService.findAllBookingsByRenter(user);
        model.addAttribute("bookings", bookings);

        return "profile";
    }


    @GetMapping("/profile")
    public String profile(@ModelAttribute("user") User user,
                          Model model){
        return "profile";
    }
}