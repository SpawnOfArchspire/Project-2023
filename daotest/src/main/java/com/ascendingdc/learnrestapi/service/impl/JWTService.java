package com.ascendingdc.learnrestapi.service.impl;

import com.ascendingdc.learnrestapi.dto.UserDto;
import io.jsonwebtoken.Claims;

public interface JWTService {

    String generateToken(UserDto userDto);

    Claims decryptJwtToken(String token);

    boolean hasTokenExpired(String token);

    boolean validateAccessToken(String token);
}
