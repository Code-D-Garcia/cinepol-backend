package com.codedgarcia.cinepol.service.impl;

import com.codedgarcia.cinepol.dto.user.UserCreateRequest;
import com.codedgarcia.cinepol.dto.user.UserResponse;
import com.codedgarcia.cinepol.mapper.UserMapper;
import com.codedgarcia.cinepol.model.User;
import com.codedgarcia.cinepol.repository.IUserRepository;
import com.codedgarcia.cinepol.service.IUserService;
import com.codedgarcia.cinepol.shared.exception.DuplicateResourceException;
import com.codedgarcia.cinepol.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {

        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public UserResponse createUser(UserCreateRequest request) {

        if(userRepository.existsByUsernameIgnoreCase(request.username())){
            throw new DuplicateResourceException("Usuario existente");
        }

        User user = userMapper.toEntity(request);
        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }
}
