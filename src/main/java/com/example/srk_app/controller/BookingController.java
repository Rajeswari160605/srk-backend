package com.example.srk_app.controller;

import com.example.srk_app.dto.*;
import com.example.srk_app.mapper.BookingMapper;
import com.example.srk_app.model.Booking;
import com.example.srk_app.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    public BookingController(BookingService bookingService,
                             BookingMapper bookingMapper) {
        this.bookingService = bookingService;
        this.bookingMapper = bookingMapper;
    }

    // create booking (original backend flow)
  /*  @PostMapping
    public ResponseEntity<BookingDetailsDTO> createBooking(
            @RequestBody CreateBookingRequest request) {

        Booking booking = bookingService.createBooking(request);
        BookingDetailsDTO dto = bookingMapper.toDetailsDto(booking);
        return ResponseEntity.ok(dto);
    }*/

    // get booking details (used by Booking Status screen)
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDetailsDTO> getBooking(@PathVariable String bookingId) {

        Booking booking = bookingService.findByBookingId(bookingId);  // may return null

        if (booking == null) {                 // handle not found -> 404
            return ResponseEntity.notFound().build();
        }

        BookingDetailsDTO dto = bookingMapper.toDetailsDto(booking);
        return ResponseEntity.ok(dto);
    }

    // booking summary for confirmation screen (per-room count & price)
    @GetMapping("/{bookingId}/summary")
    public ResponseEntity<BookingSummaryResponse> getSummary(@PathVariable String bookingId) {
        BookingSummaryResponse response = bookingService.getBookingSummary(bookingId);
        return ResponseEntity.ok(response);
    }

    // mark paid (call from payment callback)
    @PostMapping("/{bookingId}/paid")
    public ResponseEntity<Void> markPaid(@PathVariable String bookingId) {
        bookingService.markPaid(bookingId);
        return ResponseEntity.ok().build();
    }

    // confirm + create booking from Android BookingRequest
    @PostMapping("/confirm")
    public ResponseEntity<BookingResponse> confirm(@RequestBody BookingRequest request) {
        System.out.println("CONFIRM API: username=" + request.getUsername()
                + ", phone=" + request.getPhoneNumber()
                + ", userId=" + request.getUserId());

        CreateBookingRequest createReq = new CreateBookingRequest();
        createReq.setUsername(request.getUsername());
        createReq.setPhoneNumber(request.getPhoneNumber());

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate checkIn  = LocalDate.parse(request.getCheckInDate(), fmt);
        LocalDate checkOut = LocalDate.parse(request.getCheckOutDate(), fmt);
        createReq.setCheckIn(checkIn);      // LocalDate
        createReq.setCheckOut(checkOut);    // LocalDate

        //createReq.setCheckInDate(checkIn.toString());     // or formatted "dd-MM-yyyy"
       // createReq.setCheckOutDate(checkOut.toString());

        // COPY ROOMS INTO CreateBookingRequest
        List<RoomItem> roomItems = request.getRooms().stream()
                .map(r -> {
                    RoomItem item = new RoomItem();
                    item.setRoomType(r.getRoomType());
                    item.setCount(r.getCount());
                    item.setPrice(r.getPrice().doubleValue());
                    return item;
                })
                .collect(Collectors.toList());
        createReq.setRooms(roomItems);


        String roomSummary = buildRoomSummary(request.getRooms());
        createReq.setRoomSummary(roomSummary);

        BigDecimal total = computeTotal(request.getRooms());
        createReq.setTotalAmount(total);

        Booking booking = bookingService.createBooking(createReq);

        BookingResponse response = new BookingResponse();
        response.setBookingId(booking.getBookingId());
        response.setTotalAmount(booking.getTotalAmount());
        response.setStatus(booking.getStatus().name());

        return ResponseEntity.ok(response);
    }

    // check‑in
    @PostMapping("/{bookingId}/checkin")
    public ResponseEntity<Void> checkIn(@PathVariable String bookingId) {
        bookingService.checkIn(bookingId);
        return ResponseEntity.ok().build();
    }

    // ===================== NEW API FOR MY BOOKINGS SCREEN =====================

    // last 5 bookings of a user (for MyBookingsActivity)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingItemDto>> getLastFiveBookings(@PathVariable Long userId) {
        List<Booking> bookings = bookingService.getLastFiveBookingsForUser(userId);
        List<BookingItemDto> bookingItems = bookings.stream()
                .map(this::convertToBookingItemDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookingItems);
    }
    @PostMapping("/{bookingId}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable String bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}/cancelled")
    public ResponseEntity<List<BookingItemDto>> getCancelledBookings(@PathVariable Long userId) {
        List<Booking> bookings = bookingService.getCancelledBookingsForUser(userId);
        List<BookingItemDto> items = bookings.stream()
                .map(this::convertToBookingItemDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(items);
    }


    private BookingItemDto convertToBookingItemDto(Booking booking) {
        BookingItemDto dto = new BookingItemDto();

        // numeric DB id
        if (booking.getId() != null) {
            dto.setId(booking.getId().intValue());
        }

        // public booking id like "HR..."
        dto.setBookingId(booking.getBookingId());

        // checkInDate string for list (Android maps with @SerializedName("checkIn"))
        if (booking.getCheckIn() != null) {
            dto.setCheckInDate(booking.getCheckIn().toString());
        } else {
            dto.setCheckInDate("");
        }

        // status
        dto.setStatus(booking.getStatus() != null ? booking.getStatus().name() : "REQUESTED");

        // totalPrice / totalAmount for list row
        if (booking.getTotalAmount() != null) {
            dto.setTotalPrice(booking.getTotalAmount().doubleValue());
        } else {
            dto.setTotalPrice(0.0);
        }

        // optional: if you later add hotelName / roomType, set them here too

        return dto;
    }

    // ==========================================================================

    // helper to build summary like "Family Room AC x1, Double Room AC x2"
    private String buildRoomSummary(List<BookingRequest.RoomSelection> rooms) {
        if (rooms == null || rooms.isEmpty()) {
            return "";
        }
        return rooms.stream()
                .map(r -> r.getRoomType() + " x" + r.getCount())
                .collect(Collectors.joining(", "));
    }

    // helper to compute total from price * count
    private BigDecimal computeTotal(List<BookingRequest.RoomSelection> rooms) {
        if (rooms == null) {
            return BigDecimal.ZERO;
        }
        return rooms.stream()
                .map(r -> r.getPrice().multiply(BigDecimal.valueOf(r.getCount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
