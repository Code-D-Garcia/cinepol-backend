package com.codedgarcia.cinepol.controller;


import com.codedgarcia.cinepol.dto.user.UserCreateRequest;
import com.codedgarcia.cinepol.dto.user.UserResponse;
import com.codedgarcia.cinepol.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> postUser(@RequestBody UserCreateRequest request){
        UserResponse response = userService.createUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById( @PathVariable UUID id) {
        UserResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getUsers () {
        return ResponseEntity.ok(userService.getUsers());
    }
}
