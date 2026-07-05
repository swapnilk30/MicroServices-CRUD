package com.example.controller;

import io.jsonwebtoken.Claims;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @GetMapping("/public")
    public String publicMessage() {
        return "This is a public message.";
    }

    @GetMapping("/private")
    public String privateMessage() {
        return "This is a private message.";
    }

    //ROLE_ADMIN
    //@PreAuthorize("hasRole('ADMIN')")
   // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    //ROLE_USER


}
