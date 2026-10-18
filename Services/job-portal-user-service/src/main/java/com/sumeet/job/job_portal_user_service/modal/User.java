package com.sumeet.job.job_portal_user_service.modal;


import com.sumeet.job.Domains.UserRole;
import com.sumeet.job.Domains.UserStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false,unique = true)

    private String email;
    @Column(nullable = false)
    private String password;


    private  String phone;

    private String profileImage;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role=UserRole.ROLE_JOB_SEEKER;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus userStatus=UserStatus.ACTIVE;


    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateTime;


    private LocalDateTime lastLoginTime;


//   opttoonals

    private LocalDateTime suspendTime;

    private LocalDateTime deletedTime;




}
