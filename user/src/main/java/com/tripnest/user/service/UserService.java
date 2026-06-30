package com.tripnest.user.service;

import com.tripnest.user.dto.AuthResponse;
import com.tripnest.user.dto.LoginRequest;
import com.tripnest.user.dto.UserRequest;
import com.tripnest.user.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse register(UserRequest request);

    AuthResponse login(LoginRequest request);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(Long id, UserRequest request);

    void deleteUser(Long id);

}