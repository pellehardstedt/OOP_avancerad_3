package com.example.oop_avancerad_3;

import com.example.oop_avancerad_3.Entity.User;
import com.example.oop_avancerad_3.Repository.UserRepository;
import com.example.oop_avancerad_3.Service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class OopAvancerad3ApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void SaveAndLoginUser(){
        userService.deleteUser("dummy_user");

        User user = new User();
        user.setUserName("dummy_user");
        user.setPassword("1234");

        userService.saveUser(user);

        Boolean auth = userService.authUser("dummy_user", "1234");
        assert(auth==true);
        userService.deleteUser("dummy_user");
    }
}