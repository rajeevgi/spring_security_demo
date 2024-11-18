package com.sprk.spring_security_demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/welcome")
    public String welcomePage(){
        return "Welcome to spring security page.";
    }

    @GetMapping({"/home","/"})
    public String homepage(){
        return "Spring security homepage.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showAdmin(){
        return "Admin Page of Spring Security.";
    }

    @GetMapping("/User")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String showUser(){
        return "User Page Of Spring Security.";
    }

    @GetMapping("/Manager")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public String showManager(){
        return "Manager Page Of Spring Security.";
    }
}
