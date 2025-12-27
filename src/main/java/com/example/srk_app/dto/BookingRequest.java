package com.example.srk_app.dto;

import java.math.BigDecimal;
import java.util.List;

public class BookingRequest {

    private Integer userId;
    private String username;
    private String phoneNumber;
    private String checkInDate;
    private String checkOutDate;
    private List<RoomSelection> rooms;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCheckInDate() {
        return checkInDate ;
    }

    public void setCheckInDate(String checkIn) {
        this.checkInDate  = checkIn;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOut) {
        this.checkOutDate = checkOut;
    }

    public List<RoomSelection> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomSelection> rooms) {
        this.rooms = rooms;
    }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public static class RoomSelection {
        private String roomType;
        private int count;
        private BigDecimal price;

        public String getRoomType() {
            return roomType;
        }

        public void setRoomType(String roomType) {
            this.roomType = roomType;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }
}
