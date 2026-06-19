package com.codedgarcia.cinepol.service;
import com.codedgarcia.cinepol.dto.user.UserCreateRequest;
import com.codedgarcia.cinepol.dto.user.UserResponse;

import java.util.List;
import java.util.UUID;

public interface IUserService {

UserResponse getUserById(UUID id);
List<UserResponse> getUsers();
UserResponse createUser(
        UserCreateRequest request);
}
