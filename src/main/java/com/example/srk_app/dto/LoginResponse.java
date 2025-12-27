package com.example.srk_app.dto;

public class LoginResponse {

    // Fields returned to the Android app after successful login
    private Integer userId;       // unique user id
    private String userName;      // full name or display name
    private String mobileNumber;  // mobile phone number

    // --- Getters and setters ---

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
