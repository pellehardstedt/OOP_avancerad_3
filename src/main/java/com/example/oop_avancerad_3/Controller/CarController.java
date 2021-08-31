package com.example.oop_avancerad_3.Controller;

import com.example.oop_avancerad_3.Entity.Car;
import com.example.oop_avancerad_3.Entity.User;
import com.example.oop_avancerad_3.Service.CarService;
import com.example.oop_avancerad_3.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class CarController {
    @Autowired
    CarService carService;

    @Autowired
    UserService userService;

    @GetMapping("/addCar")
    public String addCar(@ModelAttribute("user") User user) {
        return "addcar";
    }

    @GetMapping("/searchCars")
    public String searchCars(@ModelAttribute("user") User user) {

        return "searchCars";
    }

    @PostMapping("/searchCars")
    public String searchTerm(@RequestParam("searchTerm") String searchTerm,
                             Model model) {
        System.out.println(searchTerm);
        Collection<Car> cars = carService.searchCars(searchTerm);
        model.addAttribute("cars", cars);
        return "searchCars";
    }

    @PostMapping("/saveCar")
    public String saveCar(
        @RequestParam("brand") String brand,
        @RequestParam("model") String model,
        @RequestParam("regplate") String regPlate,
        @RequestParam("cardesc") String carDesc,
        @RequestParam("type") String type,
        @RequestParam("numberpassengers") int numberPassengers,
        //@RequestParam("lat") String latitude,
        //@RequestParam("long") String longitude,
        @CookieValue("currentUserId") String currentUserId
    ){
        //@RequestParam("lat") String latitude,
        //@RequestParam("long") String longitude

        Car car = new Car();
        car.setRegPlate(regPlate);
        car.setBrand(brand);
        car.setModel(model);
        car.setCarDesc(carDesc);
        car.setType(type);
        car.setNumberPassengers(numberPassengers);

        User user = userService.getUserById(Long.parseLong(currentUserId));
        car.setOwner(user);

        carService.saveCar(car);
        return "redirect:/userLoggedIn/" + user.getUserIdString();
    }

}
