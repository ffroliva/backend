package br.com.ffroliva.portfolio.controller;

import br.com.ffroliva.portfolio.exception.ResourceNotFoundException;
import br.com.ffroliva.portfolio.model.User;
import br.com.ffroliva.portfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/private")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping(value="users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> findById(@PathVariable(name = "username") String username){
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        return new ResponseEntity<User>(user, OK);
    }

}
