package com.sprk.spring_security_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sprk.spring_security_demo.entity.UserInfo;
import com.sprk.spring_security_demo.repository.UserInfoRepository;

@Service
public class UserInfoService {

    @Autowired 
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Post Mapping to assign role to user
    public String addRoleToUser(String role, int id) {
       
        UserInfo userInfo = userInfoRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not found!"));

        UserInfo userInfo2 = userInfoRepository.findByRoles(role).orElseThrow(() -> new RuntimeException("Role not found!"));

        userInfo.getRoles().split(",");

        userInfoRepository.save(userInfo);

        return " Role Assign to user Successfully....";
    }

    // Post Mapping to add user 
    public String saveUser(UserInfo userInfo) {

        String encodepassword = passwordEncoder.encode(userInfo.getPassword());

        // Default Role for every user who register theirself through browser.
        String role = "ROLE_USER";

        userInfo.setPassword(encodepassword);
        userInfo.setRoles(role);

        userInfoRepository.save(userInfo);

        return " User Added Successfully...";
    }

    

    
}
