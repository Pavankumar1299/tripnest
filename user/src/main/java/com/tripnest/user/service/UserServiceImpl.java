package com.tripnest.user.service;

import com.tripnest.user.dto.AuthResponse;
import com.tripnest.user.dto.LoginRequest;
import com.tripnest.user.dto.UserRequest;
import com.tripnest.user.dto.UserResponse;
import com.tripnest.user.entity.AppUser;
import com.tripnest.user.exception.DuplicateResourceException;
import com.tripnest.user.exception.ResourceNotFoundException;
import com.tripnest.user.repository.UserRepository;
import com.tripnest.user.security.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserResponse register(UserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists.");
        }

        AppUser user = AppUser.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                // .password(request.getPassword())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        AppUser savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        AppUser user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invalid email or password."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResourceNotFoundException("Invalid email or password.");
        }

        String token = jwtService.generateToken(user.getEmail());

        return AuthResponse.builder()
                .token(token)
                .role(user.getRole())
                .build();
    }

    @Override
    public UserResponse getUserById(Long id) {

        AppUser user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id : " + id));

        return mapToResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {

        AppUser user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id : " + id));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        return mapToResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {

        AppUser user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id : " + id));

        userRepository.delete(user);
    }

    private UserResponse mapToResponse(AppUser user) {

        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}