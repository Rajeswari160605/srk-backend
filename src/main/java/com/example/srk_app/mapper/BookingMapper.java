package com.example.srk_app.mapper;

import com.example.srk_app.dto.BookingDetailsDTO;
import com.example.srk_app.dto.CreateBookingRequest;
import com.example.srk_app.model.Booking;
import com.example.srk_app.model.BookingStatus;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    // map request from app → entity for saving
    public Booking toEntity(CreateBookingRequest req) {
        Booking b = new Booking();

        b.setUsername(req.getUsername());
        b.setPhoneNumber(req.getPhoneNumber());
        b.setHotelName(req.getHotelName());
        b.setCity(req.getCity());

        b.setCheckIn(req.getCheckIn());
        b.setCheckOut(req.getCheckOut());

        b.setRoomSummary(req.getRoomSummary());
        b.setTotalAmount(req.getTotalAmount());

        // initial status (can be overridden in service if needed)
        if (b.getStatus() == null) {
            b.setStatus(BookingStatus.REQUESTED);
        }

        return b;
    }

    // map entity → DTO for returning to app
    public BookingDetailsDTO toDetailsDto(Booking b) {
        BookingDetailsDTO dto = new BookingDetailsDTO();

        dto.setHotelName(b.getHotelName());
        dto.setCity(b.getCity());
        dto.setGuestName(b.getUsername());
        dto.setMobileNumber(b.getPhoneNumber());   // make sure this field exists in DTO
        dto.setBookingId(b.getBookingId());


        dto.setRoomAndGuests(b.getRoomSummary());
        dto.setTotalAmount(b.getTotalAmount());
        dto.setStatus(b.getStatus());

        dto.setRequestedAt(b.getRequestedAt());
        dto.setPaidAt(b.getPaidAt());
        dto.setConfirmedAt(b.getConfirmedAt());
        dto.setCheckedInAt(b.getCheckedInAt());

        if (b.getCheckIn() != null && b.getCheckOut() != null) {
            dto.setStayPeriod(b.getCheckIn().toString() + " - " + b.getCheckOut().toString());
        }

        return dto;
    }
}
