package com.patientservice.PatientService.controller;

import com.patientservice.PatientService.dto.LoginRequest;
import com.patientservice.PatientService.dto.LoginResponse;
import com.patientservice.PatientService.entity.AppUser;
import com.patientservice.PatientService.repository.UserRepository;
import com.patientservice.PatientService.security.JwtUtil;
import com.patientservice.PatientService.service.TokenBlacklistService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final TokenBlacklistService tokenBlacklistService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        System.out.println(request);
        AppUser user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        System.out.println(user);

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
        System.out.println(token);
        return new LoginResponse(token);
    }
    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            tokenBlacklistService.blackListToken(header.substring(7), 3600000);
        }
    }

}
