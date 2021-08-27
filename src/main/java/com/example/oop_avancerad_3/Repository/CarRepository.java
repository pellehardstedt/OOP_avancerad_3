package com.example.oop_avancerad_3.Repository;

import com.example.oop_avancerad_3.Entity.Car;
import com.example.oop_avancerad_3.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("SELECT c FROM Car c WHERE c.owner = :#{#user}")
    Collection<Car> findCarsByUser(@Param("user") User user);

    @Query(value = "SELECT * FROM cars WHERE" +
            "field1 LIKE '%val%' or" +
            "field2 LIKE '%val%' or" +
            "field3 LIKE '%val%' or" +
            "field4 LIKE '%val%' or" +
            "field5 LIKE '%val%'",
            nativeQuery = true)
    Collection<User> searchCars();
}
