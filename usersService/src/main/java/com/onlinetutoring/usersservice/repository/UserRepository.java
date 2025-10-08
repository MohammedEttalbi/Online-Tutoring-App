package com.onlinetutoring.usersservice.repository;

import com.onlinetutoring.usersservice.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}