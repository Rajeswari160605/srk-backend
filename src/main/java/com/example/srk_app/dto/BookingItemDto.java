
package com.example.srk_app.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingItemDto {
    private Integer id;
    @JsonProperty("bookingId")
    private String bookingId;
    @JsonProperty("checkIn")
    private String checkInDate;   // e.g. "2025-12-10"
    private String status;
    private double totalPrice;
    // "Hotel confirmed", "Pending", etc.

    // getters/setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }


    public String getCheckInDate() { return checkInDate; }
    public void setCheckInDate(String checkInDate) { this.checkInDate = checkInDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }


}