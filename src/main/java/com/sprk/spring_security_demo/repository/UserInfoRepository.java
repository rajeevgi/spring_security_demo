package com.sprk.spring_security_demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprk.spring_security_demo.entity.UserInfo;


@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    // Custom Method to find user with username
    Optional<UserInfo> findByUsername(String username);
}
