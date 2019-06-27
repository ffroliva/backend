package br.com.ffroliva.portfolio.service;

import br.com.ffroliva.portfolio.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    private static final String USERNAME = "ffroliva";
    private static final String FIRST_NAME = "Flavio";
    private static final String LAST_NAME = "Oliva";
    private static final String EMAIL = "ffroliva@gmail.com";
    private static final String PASSWORD = "ffroliva123";

    @Autowired
    private UserService userService;

    @Test
    public void saveUser(){
        User user = userService.save(User
                .of(
                        USERNAME,
                        FIRST_NAME,
                        LAST_NAME,
                        EMAIL,
                        PASSWORD));
        Assertions.assertNotNull(user.getId());
    }
}
