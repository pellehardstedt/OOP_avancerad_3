package com.example.oop_avancerad_3.Controller;

import com.example.oop_avancerad_3.Entity.Car;
import com.example.oop_avancerad_3.Entity.User;
import com.example.oop_avancerad_3.Repository.UserRepository;
import com.example.oop_avancerad_3.Service.CarService;
import com.example.oop_avancerad_3.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;
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

    @Autowired
    CarService carService;


    @GetMapping("/makeNewCar")
    public String testCar(){
        User user = userRepository.findByUsername("nr1user");
        Car car = new Car(user, "abc123", "ford focus", "combi", 4, "123", "456");
        carService.saveCar(car);
        return "test";
    }

    @GetMapping("/makeNewCar2")
    public String testCar2(){
        User user = userRepository.findByUsername("nr1user");
        Car car = new Car();
        car.setOwner(user);
        car.setRegPlate("qwe123");
        car.setNumberPassengers(2);
        car.setLatitude("123");
        car.setLongitude("456");
        car.setType("SUV");
        car.setCarDesc("nice car");
        carService.saveCar(car);
        return "test";
    }
}