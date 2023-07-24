package com.ascendingdc.learnrestapi.dao.jdbc;

import com.ascendingdc.learnrestapi.entity.Role;
import com.ascendingdc.learnrestapi.entity.User;


import java.util.List;

public interface UserDao {
    User save(User user);
    User getUserByEmail(String email);
    User getUserById(Long id);
    User getUserByCredentials(String email, String password) ;
    User addRole(User user, Role role);
    boolean delete(User user);
    List<User> findAllUsers();
    User getUserByName(String username);
}
