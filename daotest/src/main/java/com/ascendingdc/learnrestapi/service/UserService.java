package com.ascendingdc.learnrestapi.service;

import com.ascendingdc.learnrestapi.dto.UserDto;


import java.util.List;
import java.util.Set;

public interface UserService {

    UserDto save(UserDto userDto);

    UserDto getUserByEmail(String email);
    UserDto getUserById(Long userid);

    UserDto getUserByCredentials(String email, String password);

    List<UserDto> getAllUsers();


}
