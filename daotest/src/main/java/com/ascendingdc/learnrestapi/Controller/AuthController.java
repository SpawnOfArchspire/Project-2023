package com.ascendingdc.learnrestapi.Controller;

import com.ascendingdc.learnrestapi.dao.jdbc.UserDao;
import com.ascendingdc.learnrestapi.dto.UserDto;
import com.ascendingdc.learnrestapi.service.UserService;
import com.ascendingdc.learnrestapi.service.impl.JWTService;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map> authenticate(@RequestBody UserDto userDto){
        String token;
        Map<String, String> resultMap = new HashMap<>();
        try{
            UserDto retrievedUserDto = userService.getUserByCredentials(userDto.getEmail(), userDto.getPassword());
            if(retrievedUserDto == null){
                resultMap.put("msg", "the input user email and password are incorrect");
                return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(resultMap);
            }

            token = jwtService.generateToken(retrievedUserDto);
            resultMap.put("token", token);
        } catch (Exception e){
            logger.error("Authenticated user field, error = {}", e.getMessage());
            String errorMsg = e.getMessage();
            resultMap.put("msg",errorMsg);
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body(resultMap);
        }
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(resultMap);
    }


}
