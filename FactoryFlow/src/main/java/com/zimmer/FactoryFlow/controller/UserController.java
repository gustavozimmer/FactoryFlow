package com.zimmer.FactoryFlow.controller;


import com.zimmer.FactoryFlow.dto.UserRequestDTO;
import com.zimmer.FactoryFlow.dto.UserResponseDTO;
import com.zimmer.FactoryFlow.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> userCreate(@RequestBody @Valid UserRequestDTO dto){
        var user = userService.createUser(dto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.id()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

}
