package com.sprk.spring_security_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprk.spring_security_demo.entity.UserInfo;
import com.sprk.spring_security_demo.service.UserInfoService;

@RestController
public class DemoController {

    @Autowired
    private UserInfoService userInfoService;

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

    @PostMapping("/register")
    public String saveUser(@RequestBody UserInfo userInfo){
        return userInfoService.saveUser(userInfo);
    }

    @PostMapping("/add_role/{role}/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addRoleToUser(@PathVariable String role, @PathVariable int id){
        return userInfoService.addRoleToUser(role, id);
    }
}
