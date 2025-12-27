package com.example.srk_app.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // each booking belongs to one user
    @ManyToOne
    @JoinColumn(name = "user_id")   // FK column in bookings table
    private User user;

    private String bookingId;
    private String username;
    private String phoneNumber;
    private String hotelName;
    private String city;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String roomSummary;
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private java.util.List<BookingRooms> bookingRooms;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private LocalDateTime requestedAt;
    private LocalDateTime paidAt;
    private LocalDateTime confirmedAt;
    private LocalDateTime checkedInAt;
    private LocalDateTime completedAt;
    private LocalDateTime cancelledAt;

    public Booking() {
    }

    // --- getters & setters ---

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getBookingId() {
        return bookingId;
    }
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getRoomSummary() {
        return roomSummary;
    }
    public void setRoomSummary(String roomSummary) {
        this.roomSummary = roomSummary;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public java.util.List<BookingRooms> getBookingRooms() {
        return bookingRooms;
    }
    public void setBookingRooms(java.util.List<BookingRooms> bookingRooms) {
        this.bookingRooms = bookingRooms;
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

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public LocalDateTime getCancelledAt() {
        return cancelledAt;
    }
    public void setCancelledAt(LocalDateTime cancelledAt) {
        this.cancelledAt = cancelledAt;
    }
}
