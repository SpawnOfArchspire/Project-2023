package com.ascendingdc.learnrestapi.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloController {

    @GetMapping("/helloWorld")
    public String sayHello(){
        String helloWorldString = "if you can see this, the app is working now!";
        return helloWorldString;
    }
}
