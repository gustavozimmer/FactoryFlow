package com.zimmer.FactoryFlow.controller;

import com.zimmer.FactoryFlow.dto.LoginResponseDTO;
import com.zimmer.FactoryFlow.dto.LoginResquestDTO;
import com.zimmer.FactoryFlow.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginResquestDTO dto) {
        var authToken = new UsernamePasswordAuthenticationToken(dto.edv(), dto.password());
        var authentication = authenticationManager.authenticate(authToken);
        var userDetails = (UserDetails) authentication.getPrincipal();
        var token = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
