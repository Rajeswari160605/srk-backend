package com.example.srk_app.controller;

import com.example.srk_app.dto.LoginRequest;
import com.example.srk_app.dto.LoginResponse;
import com.example.srk_app.model.User;
import com.example.srk_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Login with PIN or fingerprint (no OTP)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> userOpt = userRepository.findByPin(request.getPin());

        if (userOpt.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid PIN"));
        }

        User user = userOpt.get();

        if ("PIN".equalsIgnoreCase(request.getLoginType())
                || "FINGERPRINT".equalsIgnoreCase(request.getLoginType())) {

            // Build LoginResponse that Android expects
            LoginResponse resp = new LoginResponse();
            resp.setUserId(user.getId());
            resp.setUserName(user.getName());          // adjust getter if needed
            resp.setMobileNumber(user.getPhone());    // adjust getter if needed

            // Return 200 with JSON body: { "userId": ..., "userName": "...", "mobileNumber": "..." }
            return ResponseEntity.ok(resp);
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Invalid login type"));
        }
    }

    // Additional user-related endpoints here ...
}
