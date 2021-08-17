package com.example.oop_avancerad_3.Service;

import com.example.oop_avancerad_3.Entity.Car;
import com.example.oop_avancerad_3.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    public void saveCar(Car car) {
        carRepository.save(car);
    }
}
