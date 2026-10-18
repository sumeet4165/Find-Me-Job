package com.sumeet.job.job_portal_user_service.payload;

import com.sumeet.job.DTOs.Response.UserResponse;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String title;
    private String message;
    private UserResponse user;
}
