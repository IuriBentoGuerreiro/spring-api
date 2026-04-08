package com.iuri.spring_api.repository;

import com.iuri.spring_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
