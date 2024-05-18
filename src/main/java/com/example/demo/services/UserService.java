package com.example.demo.services;

import com.example.demo.controllers.request.CreateUserRequest;
import com.example.demo.controllers.response.UserResponse;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {

    private final ModelMapper mapper;
    private final UserRepository userRepository;


    public UserResponse createUser(CreateUserRequest createUserRequest) {
        User user = mapper.map(createUserRequest, User.class);
        User createdUser = userRepository.save(user);
        return mapper.map(createdUser, UserResponse.class);
    }

    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }
}
