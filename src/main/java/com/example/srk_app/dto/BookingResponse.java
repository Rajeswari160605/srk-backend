package com.example.srk_app.dto;
import java.math.BigDecimal;

public class BookingResponse {
    private BigDecimal totalAmount;
    private String bookingId;
    private String status;
    public void setStatus(String status) { this.status = status; }
// generated booking id returned after confirmation

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
}
