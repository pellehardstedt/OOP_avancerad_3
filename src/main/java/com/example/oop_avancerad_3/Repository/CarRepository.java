package com.example.oop_avancerad_3.Repository;

import com.example.oop_avancerad_3.Entity.Car;
import com.example.oop_avancerad_3.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("SELECT c FROM Car c WHERE c.owner = :#{#user}")
    Collection<Car> findCarsByUser(@Param("user") User user);

    @Query(value = "SELECT c FROM Car c WHERE c.brand " +
            "LIKE CONCAT('%',:searchTerm,'%') or c.model " +
            "LIKE CONCAT('%',:searchTerm,'%') or c.type " +
            "LIKE CONCAT('%',:searchTerm,'%') or c.regPlate " +
            "LIKE CONCAT('%',:searchTerm,'%') or c.carDesc " +
            "LIKE CONCAT('%',:searchTerm,'%')")
    Collection<Car> searchCars(@Param("searchTerm") String searchTerm);
}