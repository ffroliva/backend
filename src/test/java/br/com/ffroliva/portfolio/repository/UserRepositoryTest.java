package br.com.ffroliva.portfolio.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import br.com.ffroliva.portfolio.model.User;

@SpringBootTest
class UserRepositoryTest {

    private static final String USERNAME = "user";
    private static final String FIRST_NAME = "User";
    private static final String LAST_NAME = "Lastname";
    private static final String EMAIL = "user@user.com";
    private static final String PASSWORD = "password";
    private static final String FULL_NAME = FIRST_NAME + " " +LAST_NAME;

    private static final User USER = User.of(USERNAME, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, null);

    @Autowired
    private UserRepository userRepository;

    @Test
    void addUser() {
        User user = userRepository.save(USER);
        assertNotNull(user.getId());
        assertEquals(user.getEmail(), EMAIL);
        assertEquals(user.getFirstName(), FIRST_NAME);
        assertEquals(user.getFullName(), FULL_NAME);
        assertEquals(user.getLastName(), LAST_NAME);
        assertEquals(user.getPassword(), PASSWORD);
        assertEquals(user.getUsername(), USERNAME);
        assertNull(user.getUserRoles());
        assertNotNull(user.getCreatedAt());
        assertNotNull(user.getUpdatedAt());
    }

    @Test
    void addUserWithInvalidUsername() {
        User user = User.of(
                null,
                null,
                null,
                "ffroliva.gmail",
                "123456",
                Collections.emptyList()
        );
        assertThrows(TransactionSystemException.class, () -> userRepository.save(user));

    }

    @Test
    void findByUsername_ValidUsername() {
        userRepository.save(USER);
        final Optional<User> optionalUser = userRepository.findByUsername("user");
        assertTrue(optionalUser.isPresent());
    }

    @Test
    void findByUsername_InvalidUsername() {
        final Optional<User> optionalUser = userRepository.findByUsername("bla@bla.com");
        assertFalse(optionalUser.isPresent());
    }

    @Test
    void existsByUsername_Valid() {
        userRepository.save(USER);
        assertTrue(userRepository.existsByUsername("user"));
    }
}


