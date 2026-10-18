package com.sumeet.job.job_portal_user_service.controller;


import com.sumeet.job.DTOs.Request.LoginRequest;
import com.sumeet.job.DTOs.Request.SighnupRequest;
import com.sumeet.job.job_portal_user_service.Service.AuthService;
import com.sumeet.job.job_portal_user_service.payload.AuthResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody @Valid SighnupRequest signupRequest) throws Exception {
        return ResponseEntity.ok(authService.Sighnup(signupRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest loginRequest) throws Exception {
        return ResponseEntity.ok(authService.login(loginRequest));
    }


}
