package com.example.srk_app.repository;

import com.example.srk_app.model.Booking;
import com.example.srk_app.model.BookingRooms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRoomsRepository extends JpaRepository<BookingRooms, Long> {
    List<BookingRooms> findByBooking(Booking booking);
}
