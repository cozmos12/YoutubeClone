package com.example.demo.controller;

import com.example.demo.service.UserRegistrationService;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRegistrationService userRegistrationService;
    private final UserService userService;

    public UserController(UserRegistrationService userRegistrationService, UserService userService) {
        this.userRegistrationService = userRegistrationService;
        this.userService = userService;
    }

    @GetMapping("/register/{token}")
    @ResponseStatus(HttpStatus.OK)

    public String register(@PathVariable String token){

    return  userRegistrationService.register(token);
    }

    @PostMapping("/subscribe/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean subscribe(@PathVariable String userId){
        userService.subscribe(userId);
        return true;
    }

    @PostMapping("/unSubscribe/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean unSubscribe(@PathVariable String userId){
        userService.unSubscribe(userId);
        return true;
    }

    @GetMapping("/{userId}/history")
    @ResponseStatus(HttpStatus.OK)
    public Set<String> getHistory(@PathVariable String userId){
       return userService.userHistory(userId);
    }




}
