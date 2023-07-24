package com.ascendingdc.learnrestapi.repository;

import com.ascendingdc.learnrestapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
