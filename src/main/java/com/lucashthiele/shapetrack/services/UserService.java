package com.lucashthiele.shapetrack.services;

import com.lucashthiele.shapetrack.domain.user.UserDTO;
import com.lucashthiele.shapetrack.domain.user.User;
import com.lucashthiele.shapetrack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Long createUser(UserDTO data){
        var user = new User(null, data.username(), encryptPassword(data.password()));
        userRepository.save(user);
        return user.getId();
    }

    private String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
