package com.example.oop_avancerad_3.Service;

import com.example.oop_avancerad_3.Entity.Booking;
import com.example.oop_avancerad_3.Entity.User;
import com.example.oop_avancerad_3.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    public void saveBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    public Collection<Booking> findAllBookingsByRenter(User renter){
        return bookingRepository.findBookingsByRenter(renter);
    }
}
