package br.com.ffroliva.portfolio.repository;

import br.com.ffroliva.portfolio.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    private static final User USER = User.of("user@user.com", "123456", Collections.emptyList());
    private static final User ADMIN = User.of("admin@admin.com", "123456", Collections.emptyList());

    @Autowired
    private UserRepository userRepository;

    @Test
    void addUser(){
        User user = userRepository.save(USER);
        assertEquals(1, user.getId());
    }
    @Test
    void addUserWithInvalidUsername(){
        User user = User.of(
                "ffroliva.gmail",
                "123456",
                Collections.emptyList()
        );
        assertThrows(TransactionSystemException.class, () -> userRepository.save(user));

    }

    @Test
    void findByEmailWithValidUsername(){
        userRepository.save(USER);
        userRepository.save(ADMIN);
        final Optional<User> optionalUser = userRepository.findByUsername("user@user.com");
        assertTrue(optionalUser.isPresent());
    }

    @Test
    void findByEmailWithInvalidUsername(){
        final Optional<User> optionalUser = userRepository.findByUsername("bla@bla.com");
        assertFalse(optionalUser.isPresent());
    }
}


