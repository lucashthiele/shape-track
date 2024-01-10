package com.lucashthiele.shapetrack.controller;

import com.lucashthiele.shapetrack.domain.user.CreateUserData;
import com.lucashthiele.shapetrack.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseEntity.BodyBuilder> create(@RequestBody CreateUserData data, UriComponentsBuilder uriBuilder){
        var createdUserId = userService.createUser(data);

        var uri = uriBuilder.path("/user/{id}").buildAndExpand(createdUserId).toUri();
        return ResponseEntity.created(uri).build();
    }
}
