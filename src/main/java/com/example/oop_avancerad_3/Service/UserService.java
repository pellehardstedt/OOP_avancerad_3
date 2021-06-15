package com.example.oop_avancerad_3.Service;

import com.example.oop_avancerad_3.Entity.User;
import com.example.oop_avancerad_3.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public void saveUser(User user){

        userRepository.save(user);
    }
}
