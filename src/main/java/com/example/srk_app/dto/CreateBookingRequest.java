package com.example.srk_app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CreateBookingRequest {

    private String username;
    private String phoneNumber;
    private String hotelName;
    private String city;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String roomSummary;
    private BigDecimal totalAmount;

    // IMPORTANT: list of rooms coming from Android
    private List<RoomItem> rooms;

    public CreateBookingRequest() {}

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getHotelName() { return hotelName; }
    public void setHotelName(String hotelName) { this.hotelName = hotelName; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

  //  public String getCheckInDate() { return checkInDate; }
  //  public void setCheckInDate(String checkInDate) { this.checkInDate = checkInDate; }


   // public String getCheckOutDate() { return checkOutDate; }
    //public void setCheckOutDate(String checkOutDate) { this.checkOutDate = checkOutDate; }

    public String getRoomSummary() { return roomSummary; }
    public void setRoomSummary(String roomSummary) { this.roomSummary = roomSummary; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public List<RoomItem> getRooms() { return rooms; }
    public void setRooms(List<RoomItem> rooms) { this.rooms = rooms; }
}
