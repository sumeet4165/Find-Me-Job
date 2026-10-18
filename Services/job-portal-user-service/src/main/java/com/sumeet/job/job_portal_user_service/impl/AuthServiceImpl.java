package com.sumeet.job.job_portal_user_service.impl;

import com.sumeet.job.DTOs.Request.LoginRequest;
import com.sumeet.job.DTOs.Request.SighnupRequest;
import com.sumeet.job.Domains.UserRole;
import com.sumeet.job.Domains.UserStatus;
import com.sumeet.job.job_portal_user_service.Mappers.UserMapper;
import com.sumeet.job.job_portal_user_service.Repository.UserRepo;
import com.sumeet.job.job_portal_user_service.Security.CustomUserDetailsService;
import com.sumeet.job.job_portal_user_service.Security.JwtProvider;
import com.sumeet.job.job_portal_user_service.Service.AuthService;
import com.sumeet.job.job_portal_user_service.modal.User;
import com.sumeet.job.job_portal_user_service.payload.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public AuthResponse Sighnup(SighnupRequest req) throws Exception {

//         check if already
        if (userRepo.existsByEmail(req.getEmail())) {
            throw new Exception("Email already resistered : " + req.getEmail());
        }

        if (req.getRole().equals(UserRole.ROLE_ADMIN)) {
            throw new Exception(" cannot self resgiter as a Admin  " + req.getEmail());
        }

        User user = User.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .role(req.getRole())
                .phone(req.getPhone())
                .userStatus(UserStatus.ACTIVE)
                .lastLoginTime(LocalDateTime.now())
                .build();

        User saved = userRepo.save(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(saved.getEmail(), saved.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt=jwtProvider.generateToken(authentication,saved.getId());


        AuthResponse authResponse = new AuthResponse();
        authResponse.setTitle("Welcome " + saved.getFullName());
        authResponse.setMessage("Registered Succesfully " + saved.getFullName());
        authResponse.setJwt(jwt);
        authResponse.setUser(UserMapper.toUserResponse(saved));

        return authResponse;
    }









    @Override
    public AuthResponse login(LoginRequest req) throws Exception {
        Authentication authentication = authenticate(
                req.getEmail(),
                req.getPassword()
        );

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        User user = userRepo.findByEmail(req.getEmail());

        String jwt = jwtProvider.generateToken(authentication, user.getId());

        user.setLastLoginTime(LocalDateTime.now());
        userRepo.save(user);

        AuthResponse res = new AuthResponse();
        res.setTitle("welcome " + user.getFullName());
        res.setMessage("Login Successfully");
        res.setJwt(jwt);
        res.setUser(UserMapper.toUserResponse(user));

        return res;
    }

    private Authentication authenticate(String email, String password)
            throws Exception {

        UserDetails userDetails =
                customUserDetailsService.loadUserByUsername(email);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new Exception("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }
}
