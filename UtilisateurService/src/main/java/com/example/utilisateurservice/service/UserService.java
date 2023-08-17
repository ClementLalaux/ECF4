package com.example.utilisateurservice.service;

import com.example.utilisateurservice.entity.User;
import com.example.utilisateurservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String name, String email){
        User user = User.builder().name(name).email(email).build();
        userRepository.save(user);
        return user;
    }

    public User getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        throw new RuntimeException("Not found");
    }
}
