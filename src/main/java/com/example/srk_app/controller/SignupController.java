package com.example.srk_app.controller;

import com.example.srk_app.model.User;
import com.example.srk_app.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")  // Enable for your frontend origin in production
public class SignupController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User userRequest) {
        try {
            if (userRepository.existsByEmail(userRequest.getEmail())) {
                return ResponseEntity.badRequest().body(
                        java.util.Map.of("status", "error", "message", "Email already registered")
                );
            }

            // Here you should hash the password before saving in production

            User user = new User();
            user.setEmail(userRequest.getEmail());
            user.setName(userRequest.getName());
            user.setPin(userRequest.getPin());
            user.setAadhaar(userRequest.getAadhaar());
            user.setAddress(userRequest.getAddress());
            user.setPhone(userRequest.getPhone());

            User savedUser = userRepository.save(user);

            // Return the saved user including generated userId
            return ResponseEntity.ok(savedUser);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    java.util.Map.of("status", "error", "message", "Internal server error")
            );
        }
    }
}
