package com.onlinetutoring.usersservice.controller;

import com.onlinetutoring.usersservice.domain.dto.user.UserCreateUpdateDto;
import com.onlinetutoring.usersservice.domain.dto.user.UserReadDto;
import com.onlinetutoring.usersservice.mapper.UserMapper;
import com.onlinetutoring.usersservice.service.impl.ServiceUserImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final ServiceUserImpl serviceUser;
    private final UserMapper userMapper;

    // ----------------- GET ALL -----------------
    @GetMapping
    public ResponseEntity<List<UserReadDto>> getAllUsers() {
        List<UserReadDto> users = serviceUser.getAll()
                .stream()
                .map(userMapper::toReadDto)
                .toList();
        return ResponseEntity.ok(users);
    }

    // ----------------- GET BY ID -----------------
    @GetMapping("/{id}")
    public ResponseEntity<UserReadDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userMapper.toReadDto(serviceUser.getById(id)));
    }

    // ----------------- CREATE -----------------
    @PostMapping
    public ResponseEntity<UserReadDto> createUser(@Valid @RequestBody UserCreateUpdateDto dto) {
        return ResponseEntity.ok(
                userMapper.toReadDto(serviceUser.create(userMapper.toEntity(dto))));
    }

    // ----------------- UPDATE -----------------
    @PutMapping("/{id}")
    public ResponseEntity<UserReadDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserCreateUpdateDto dto) {
        var existingUser = serviceUser.getById(id);
        var updatedUser = userMapper.partialUpdate(dto, existingUser);
        return ResponseEntity.ok(userMapper.toReadDto(serviceUser.update(updatedUser)));
    }

    // ----------------- DELETE -----------------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            serviceUser.delete(id);
            return ResponseEntity.ok("User with ID " + id + " has been successfully deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
