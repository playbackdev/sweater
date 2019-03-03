package com.shvetsovg.sweater.repos;

import com.shvetsovg.sweater.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long>{
    User findByUsername(String username);
}
