package com.codedgarcia.cinepol.mapper;

import com.codedgarcia.cinepol.dto.user.UserCreateRequest;
import com.codedgarcia.cinepol.dto.user.UserResponse;
import com.codedgarcia.cinepol.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toResponse(
            User user
    ){
        return new  UserResponse(
                user.getId(),
                user.getUsername()
        );
    }

    public User toEntity(
            UserCreateRequest request
    ){
        User user = new User();
        user.setUsername(request.username());
        return user;
    }
}
