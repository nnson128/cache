package com.sonnguyen.redis_demo.service;

import com.sonnguyen.redis_demo.dto.out.AuthResponse;

public interface AuthService {
    AuthResponse login(String username, String password);
    void register(String username, String password);
}
