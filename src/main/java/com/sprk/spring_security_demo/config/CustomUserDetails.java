package com.sprk.spring_security_demo.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sprk.spring_security_demo.entity.UserInfo;

public class CustomUserDetails implements UserDetails {

    // Fields
    private List<GrantedAuthority> authorities;

    private String username;

    private String password;

    // Constructor
    public CustomUserDetails(UserInfo userInfo){
        this.username = userInfo.getUsername();
        this.password = userInfo.getPassword();

        String roles = userInfo.getRoles();

        String[] rolesArr = roles.split(",");

        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String role : rolesArr){
            authorities.add(new SimpleGrantedAuthority(role.trim()));
        }

        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
