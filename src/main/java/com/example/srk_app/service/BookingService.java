package com.example.srk_app.service;

import com.example.srk_app.dto.BookingSummaryResponse;
import com.example.srk_app.dto.CreateBookingRequest;
import com.example.srk_app.dto.RoomItem;
import com.example.srk_app.mapper.BookingMapper;
import com.example.srk_app.model.Booking;
import com.example.srk_app.model.BookingRooms;
import com.example.srk_app.model.BookingStatus;
import com.example.srk_app.model.User;
import com.example.srk_app.repository.BookingRepository;
import com.example.srk_app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository,
                          BookingMapper bookingMapper,
                          UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.userRepository = userRepository;
    }

    // ---------- CREATE BOOKING (SAVES ROOMS TOO) ----------
    public Booking createBooking(CreateBookingRequest req) {
        Booking booking = bookingMapper.toEntity(req);

        booking.setUsername(req.getUsername());
        booking.setPhoneNumber(req.getPhoneNumber());
        booking.setBookingId(generateBookingId());
        booking.setStatus(BookingStatus.REQUESTED);
        booking.setRequestedAt(LocalDateTime.now());

        // link booking to the logged-in user, found by phone number
        User user = userRepository.findByPhone(req.getPhoneNumber())
                .orElseThrow(() -> new RuntimeException("User not found for phone: " + req.getPhoneNumber()));
        booking.setUser(user);

        // save room details into bookingRooms
        List<BookingRooms> rooms = new ArrayList<>();
        if (req.getRooms() != null) {                     // CHANGED
            for (RoomItem r : req.getRooms()) {           // CHANGED
                BookingRooms br = new BookingRooms();
                br.setBooking(booking);
                br.setRoomType(r.getRoomType());
                br.setCount(r.getCount());
                br.setPrice(java.math.BigDecimal.valueOf(r.getPrice()));
                rooms.add(br);
            }
        }
        booking.setBookingRooms(rooms);

        return bookingRepository.save(booking); // cascade saves rooms too
    }

    private String generateBookingId() {
        return "HR" + System.currentTimeMillis();
    }

    public void markPaid(String bookingId) {
        Booking b = bookingRepository.findByBookingId(bookingId)
                .orElseThrow();
        b.setStatus(BookingStatus.PAID);
        b.setPaidAt(LocalDateTime.now());
        bookingRepository.save(b);
    }

    public void confirmByHotel(String bookingId) {
        Booking b = bookingRepository.findByBookingId(bookingId)
                .orElseThrow();
        b.setStatus(BookingStatus.CONFIRMED);
        b.setConfirmedAt(LocalDateTime.now());
        bookingRepository.save(b);
    }

    public void checkIn(String bookingId) {
        Booking b = bookingRepository.findByBookingId(bookingId)
                .orElseThrow();
        b.setStatus(BookingStatus.CHECKED_IN);
        b.setCheckedInAt(LocalDateTime.now());
        bookingRepository.save(b);
    }

    public Booking findByBookingId(String bookingId) {
        return bookingRepository.findByBookingId(bookingId)
                .orElse(null);
    }

    // TEMP: last 5 bookings (no user filter yet)
    // BookingService.java
    // BookingService.java
    public List<Booking> getLastFiveBookingsForUser(Long userId) {
        return bookingRepository.findTop5ByUserIdOrderByIdDesc(userId);
    }



    // ---------- SUMMARY ----------
    public BookingSummaryResponse getBookingSummary(String bookingId) {
        Booking booking = bookingRepository.findWithRoomsByBookingId(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found: " + bookingId));

        BookingSummaryResponse dto = new BookingSummaryResponse();

        dto.setGuestName(booking.getUsername());
        dto.setMobileNumber(booking.getPhoneNumber());
        dto.setBookingId(booking.getBookingId());

        if (booking.getRequestedAt() != null) {
            dto.setBookingDate(booking.getRequestedAt().toString());
        }
        dto.setCheckIn(booking.getCheckIn() != null ? booking.getCheckIn().toString() : null);
        dto.setCheckOut(booking.getCheckOut() != null ? booking.getCheckOut().toString() : null);

        List<RoomItem> roomItems = new ArrayList<>();
        StringBuilder summary = new StringBuilder();

        if (booking.getBookingRooms() != null) {
            for (BookingRooms br : booking.getBookingRooms()) {
                RoomItem item = new RoomItem();
                item.setRoomType(br.getRoomType());
                item.setCount(br.getCount());
                item.setPrice(br.getPrice() != null ? br.getPrice().doubleValue() : 0.0);
                roomItems.add(item);

                if (summary.length() > 0) summary.append(", ");
                summary.append(br.getRoomType()).append(" x").append(br.getCount());
            }
        }
        dto.setSelectedRooms(roomItems);
        dto.setRoomSummary(summary.toString());

        dto.setTotalAmount(
                booking.getTotalAmount() != null
                        ? booking.getTotalAmount().doubleValue()
                        : 0.0
        );
        return dto;

    }
    public void cancelBooking(String bookingId) {
        Booking b = bookingRepository.findByBookingId(bookingId)
                .orElseThrow();
        b.setStatus(BookingStatus.CANCELLED);
        b.setCancelledAt(LocalDateTime.now()); // add field in entity if you want
        bookingRepository.save(b);
    }
    public List<Booking> getCancelledBookingsForUser(Long userId) {
        return bookingRepository.findByUserIdAndStatusOrderByIdDesc(userId, BookingStatus.CANCELLED);
    }

}
