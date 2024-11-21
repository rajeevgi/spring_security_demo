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

    // Put Mapping to assign role to user
    public String addRoleToUser(int id, String role) {
       
        UserInfo userInfo = userInfoRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not found!"));

        String existingRoleString = userInfo.getRoles();

        StringBuilder existingRoles = new StringBuilder(userInfo.getRoles());

        if(!existingRoleString.contains(role)){
            existingRoles.append(",").append(role);
        }else{
            return "Role Already exists.";
        }

        userInfo.setRoles(existingRoles.toString());

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

    // Put mapping to remove Role from user.
    public String deleteRoleFromUser(int id, String role) {
        
        UserInfo userInfo = userInfoRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found!"));

        String existingRoleString = userInfo.getRoles();   

        StringBuilder existingRoles = new StringBuilder(userInfo.getRoles());

        if(existingRoleString.contains(role)){
            int startIndex = existingRoleString.indexOf(role) == 0 ? existingRoleString.indexOf(role) : existingRoleString.indexOf(role) - 1;

            int lengthAdd = existingRoleString.indexOf(role) + role.length();

            int endIndex = existingRoleString.indexOf(role) == 0 ? (lengthAdd+1) : lengthAdd;

            existingRoles.delete(startIndex, endIndex);
            
        }else{
            return " Role not found...";
        }

        userInfo.setRoles(existingRoles.toString());

        userInfoRepository.save(userInfo);

        return " Role deleted from user Successfully....";
    }

}
