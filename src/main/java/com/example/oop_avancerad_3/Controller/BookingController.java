package com.example.oop_avancerad_3.Controller;

import com.example.oop_avancerad_3.Entity.Booking;
import com.example.oop_avancerad_3.Entity.Car;
import com.example.oop_avancerad_3.Entity.User;
import com.example.oop_avancerad_3.Service.BookingService;
import com.example.oop_avancerad_3.Service.CarService;
import com.example.oop_avancerad_3.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookingController {
    @Autowired
    BookingService bookingService;
    @Autowired
    UserService userService;
    @Autowired
    CarService carService;

    @PostMapping("/booking/{id}")
    public String saveBooking(@PathVariable("id") Long carId,
                              @CookieValue("currentUserId") String currentUserId){

        //getting user from model instead. cookie quick fix
        User user = userService.getUserById(Long.parseLong(currentUserId));

        Booking booking = new Booking();
        Car car = carService.getCarById(carId);
        booking.setCar(car);
        booking.setRenter(user);
        bookingService.saveBooking(booking);

        return "profile";
    }
}