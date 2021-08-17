package com.example.oop_avancerad_3;

import com.example.oop_avancerad_3.Entity.Car;
import com.example.oop_avancerad_3.Entity.User;
import com.example.oop_avancerad_3.Repository.CarRepository;
import com.example.oop_avancerad_3.Repository.UserRepository;
import com.example.oop_avancerad_3.Service.CarService;
import com.example.oop_avancerad_3.Service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
class OopAvancerad3ApplicationTests {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    void SaveAndLoginUser(){
        userService.deleteUser("dummy_user");

        User user = new User();
        user.setUserName("dummy_user");
        user.setPassword("1234");

        userService.saveUser(user);

        Boolean auth = userService.authUser("dummy_user", "1234");
        assert(auth==true);
    }

    @Autowired
    CarService carService;
    @Autowired
    CarRepository carRepository;

    @Test
    void createCar(){
        Car car = new Car();
        car.setRegPlate("ABC123");
        car.setType("Combi");
        car.setCarDesc("Ford Focus");
        Optional<User> optional = userRepository.findById(Long.valueOf(2));
        optional.ifPresent(user -> {
            car.setOwner(user);
        });

        carService.saveCar(car);
        System.out.println(car.toString());
        assert(true);
    }

}