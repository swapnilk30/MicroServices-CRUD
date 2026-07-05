package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@Slf4j
public class AuthController {


    @GetMapping
    public String getAuthControllerDetails(){
        log.info("getAuthControllerDetails");
        return "Auth Controller Details";
    }
}
