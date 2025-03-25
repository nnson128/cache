package com.sonnguyen.redis_demo.repository;

import com.sonnguyen.redis_demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}

