package com.sumeet.job.job_portal_user_service.controller;


import com.sumeet.job.Domains.UserRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Homecontroller {
//for test
    @GetMapping
    public String index(){
        return "Hello World" + UserRole.ROLE_JOB_SEEKER;
    }
}
