package com.example.oop_avancerad_3.Service;

import com.example.oop_avancerad_3.Entity.Car;
import com.example.oop_avancerad_3.Entity.User;
import com.example.oop_avancerad_3.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    public void saveCar(Car car) {
        carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Collection<Car> findCarsByUser(User user) {
        return carRepository.findCarsByUser(user);
    }

    public Collection<Car> searchCars(String searchTerm) {
        System.out.println(carRepository.searchCars(searchTerm));
        return carRepository.searchCars(searchTerm);
    }
    public Car getCarById(Long id){
        return carRepository.getById(id);
    }
}
