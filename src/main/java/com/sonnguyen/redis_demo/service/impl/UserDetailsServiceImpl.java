package com.sonnguyen.redis_demo.service.impl;

import com.sonnguyen.redis_demo.exception.CommonException;
import com.sonnguyen.redis_demo.model.CustomUserDetails;
import com.sonnguyen.redis_demo.model.User;
import com.sonnguyen.redis_demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new CommonException("User not found: " + username, HttpStatus.UNAUTHORIZED);
        }
        return new CustomUserDetails(user);
    }
}

