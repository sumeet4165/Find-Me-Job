package com.sumeet.job.job_portal_user_service.Security;



import com.sumeet.job.job_portal_user_service.Repository.UserRepo;
import com.sumeet.job.job_portal_user_service.modal.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException(
                    "User not found: " + username
            );
        }

        GrantedAuthority authority =
                new SimpleGrantedAuthority(user.getRole().toString());

        Collection<GrantedAuthority> grantedAuthorities =
                Collections.singletonList(authority);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                grantedAuthorities
        );
    }
}