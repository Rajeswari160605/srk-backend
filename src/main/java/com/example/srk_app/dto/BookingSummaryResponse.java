package com.example.srk_app.dto;

public class BookingSummaryResponse {

    private String guestName;
    private String mobileNumber;
    private String bookingId;
    private String bookingDate;
    private String checkIn;
    private String checkOut;
    private java.util.List<RoomItem> selectedRooms;
    private Double totalAmount;
    private String roomSummary;
    private String status;   // NEW

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRoomSummary() {
        return roomSummary;
    }

    public void setRoomSummary(String roomSummary) {
        this.roomSummary = roomSummary;
    }


    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public java.util.List<RoomItem> getSelectedRooms() {
        return selectedRooms;
    }

    public void setSelectedRooms(java.util.List<RoomItem> selectedRooms) {
        this.selectedRooms = selectedRooms;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
