package com.example.srk_app.repository;

import com.example.srk_app.model.Booking;
import com.example.srk_app.model.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByBookingId(String bookingId);

    @Query("select b from Booking b left join fetch b.bookingRooms where b.bookingId = :bookingId")
    Optional<Booking> findWithRoomsByBookingId(@Param("bookingId") String bookingId);

    @Query("select b from Booking b where b.user.id = :userId order by b.id desc")
    List<Booking> findLastFiveByUserId(@Param("userId") Long userId);
    @Query("select b from Booking b order by b.id desc")
    List<Booking> findAllOrderByIdDesc();
    List<Booking> findByUserIdAndStatusOrderByIdDesc(Long userId, BookingStatus status);


}
