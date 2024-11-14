package com.akofood.server.service;

import com.akofood.server.dto.req.UserRequest;
import com.akofood.server.dto.res.UserResponse;
import com.akofood.server.entity.User;
import com.akofood.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository UserRepository;


    @Autowired
    private UserRepository userRepository;

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id).map(this::convertToResponse);
    }

    public UserResponse createUser(UserRequest request) {
        User user = convertToEntity(request);
        User savedUser = userRepository.save(user);
        return convertToResponse(savedUser);
    }

    public UserResponse updateUser(Long id, UserRequest request) {
        User user = userRepository.findById(id).orElseThrow();
        user.setNickname(request.getNickname());
        User updatedUser = userRepository.save(user);
        return convertToResponse(updatedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserResponse convertToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setNickname(user.getNickname());
//        response.setCreatedAt(user.getCreatedAt());
//        response.setUpdatedAt(user.getUpdatedAt());
        return response;
    }

    private User convertToEntity(UserRequest request) {
        User user = new User();
        user.setNickname(request.getNickname());
        return user;
    }

    public User autoRegister() {
        User user = User.builder()
                .username(UUID.randomUUID().toString())
                .email("example@example.com")
                .address("서울특별시 서초구 역삼동")
                .build();

        return userRepository.save(user);
    }
}