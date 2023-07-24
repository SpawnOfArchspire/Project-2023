//package com.ascendingdc.learnrestapi.Controller;
//
//import com.ascendingdc.learnrestapi.dto.UserDto;
//import com.ascendingdc.learnrestapi.service.UserService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class UserController {
//
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/users")
//    public ResponseEntity<List<UserDto>> getAllUsers() {
//        List<UserDto> userDtos = userService.getAll();
//        return new ResponseEntity<>(userDtos, HttpStatus.OK);
//    }
//
//    @GetMapping("/users/one")
//    public List<UserDto> getAllUserSet() {
//        List<UserDto> userDtos = userService.getAll();
//        return userDtos;
//    }
//}
