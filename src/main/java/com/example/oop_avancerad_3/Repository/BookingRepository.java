package com.example.oop_avancerad_3.Repository;

import com.example.oop_avancerad_3.Entity.Booking;
import com.example.oop_avancerad_3.Entity.Car;
import com.example.oop_avancerad_3.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>  {
    @Query("SELECT b FROM Booking b WHERE b.renter = :#{#user}")
    Collection<Booking> findBookingsByRenter(@Param("user") User user);


}
