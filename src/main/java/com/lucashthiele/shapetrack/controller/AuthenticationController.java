package com.lucashthiele.shapetrack.controller;

import com.lucashthiele.shapetrack.domain.authentication.AuthenticationData;
import com.lucashthiele.shapetrack.domain.authentication.JWTTokenData;
import com.lucashthiele.shapetrack.domain.user.User;
import com.lucashthiele.shapetrack.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<JWTTokenData> auth(@RequestBody AuthenticationData data) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authentication = manager.authenticate(authenticationToken);

        var token = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new JWTTokenData(token));
    }
}
