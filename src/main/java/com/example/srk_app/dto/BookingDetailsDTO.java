package com.example.srk_app.dto;

import com.example.srk_app.model.BookingStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class BookingDetailsDTO {

    private String hotelName;
    private String city;
    private String guestName;
    private String bookingId;
    private String stayPeriod;          // "24 Dec - 25 Dec 2025"
    private String roomAndGuests;
    private BigDecimal totalAmount;
    private BookingStatus status;
    private LocalDateTime requestedAt;
    private LocalDateTime paidAt;
    private LocalDateTime confirmedAt;
    private LocalDateTime checkedInAt;

    private String mobileNumber;

    public BookingDetailsDTO() {
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getStayPeriod() {
        return stayPeriod;
    }

    public void setStayPeriod(String stayPeriod) {
        this.stayPeriod = stayPeriod;
    }

    public String getRoomAndGuests() {
        return roomAndGuests;
    }

    public void setRoomAndGuests(String roomAndGuests) {
        this.roomAndGuests = roomAndGuests;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

    public LocalDateTime getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public LocalDateTime getCheckedInAt() {
        return checkedInAt;
    }

    public void setCheckedInAt(LocalDateTime checkedInAt) {
        this.checkedInAt = checkedInAt;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
