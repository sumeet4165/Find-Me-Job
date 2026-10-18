package com.sumeet.job.job_portal_user_service.Mappers;


import com.sumeet.job.DTOs.Response.UserResponse;
import com.sumeet.job.job_portal_user_service.modal.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static UserResponse toUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFullName(user.getFullName());
        userResponse.setLastLogin(user.getLastLoginTime());
        userResponse.setProfileImage(user.getProfileImage());
        userResponse.setPhone(user.getPhone());
        userResponse.setStatus(user.getUserStatus());
        userResponse.setRole(user.getRole());
        userResponse.setCreatedAt(user.getCreateTime());

        return userResponse;



    }
}
