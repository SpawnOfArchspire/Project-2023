package com.ascendingdc.learnrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ascendingdc.learnrestapi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    User findByName(String name);
}
