package com.zimmer.FactoryFlow.controller;


import com.zimmer.FactoryFlow.dto.UserRequestDTO;
import com.zimmer.FactoryFlow.dto.UserResponseDTO;
import com.zimmer.FactoryFlow.dto.UserUpdateDTO;
import com.zimmer.FactoryFlow.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserResponseDTO> getAllUsers(){

        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id") Long id){
        var user = userService.getUserById(id);

        return ResponseEntity.ok(user);

    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> userCreate(@RequestBody @Valid UserRequestDTO dto){
        var user = userService.createUser(dto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.id()).toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUserById(@PathVariable("id") Long id, @RequestBody @Valid UserRequestDTO dto){
        var user = userService.updateUserById(id, dto);

        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> partialUpdateUserById(@PathVariable("id") Long id, @RequestBody @Valid UserUpdateDTO dto){
        var user = userService.partialUpdateUserById(id, dto);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id){
        userService.deleteUserById(id);

        return ResponseEntity.noContent().build();

    }
}
