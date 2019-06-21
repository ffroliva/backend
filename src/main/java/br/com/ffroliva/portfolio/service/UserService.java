package br.com.ffroliva.portfolio.service;

import br.com.ffroliva.portfolio.model.User;
import br.com.ffroliva.portfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }
}