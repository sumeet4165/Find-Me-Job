package com.sumeet.job.job_portal_user_service.Service;


import com.sumeet.job.DTOs.Request.LoginRequest;
import com.sumeet.job.DTOs.Request.SighnupRequest;
import com.sumeet.job.job_portal_user_service.payload.AuthResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    AuthResponse Sighnup (SighnupRequest req) throws Exception;
    AuthResponse login (LoginRequest req) throws Exception;

}
