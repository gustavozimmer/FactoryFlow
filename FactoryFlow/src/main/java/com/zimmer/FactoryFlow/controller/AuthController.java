package com.zimmer.FactoryFlow.controller;

import com.zimmer.FactoryFlow.dto.LoginResquestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginResquestDTO dto) {
        var authToken = new UsernamePasswordAuthenticationToken(dto.edv(), dto.password());
        authenticationManager.authenticate(authToken);

        return ResponseEntity.ok("Login realizado com sucesso!");
    }
}
