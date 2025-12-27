package com.example.srk_app.dto;

public class LoginRequest {
    private String loginType;  // "PIN" or "FINGERPRINT"
    private String pin;        // required only if loginType == "PIN"

    public String getLoginType() {
        return loginType;
    }
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getPin() {
        return pin;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }
}
