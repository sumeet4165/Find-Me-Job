package com.sumeet.job.job_portal_user_service.Repository;

import com.sumeet.job.job_portal_user_service.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String email);
    boolean existsByEmail(String email);

}
