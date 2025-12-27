package com.example.srk_app.dto;

public class SignupRequest {

    private String name;
    private String email;
    private String password;   // 4-digit PIN
    private String aadhaar;
    private String address;
    private String phone;

    // Default constructor
    public SignupRequest() {
    }

    // Parameterized constructor
    public SignupRequest(String name, String email, String password, String aadhaar, String address, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.aadhaar = aadhaar;
        this.address = address;
        this.phone = phone;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        this.aadhaar = aadhaar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
