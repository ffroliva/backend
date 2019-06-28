package br.com.ffroliva.portfolio.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;

import br.com.ffroliva.portfolio.exception.ResourceNotFoundException;

@SpringBootTest
class CustomGrantedAuthorityServiceTest {

    @Autowired
    private GrantedAuthorityService service;

    @Test
    void loadByUsername(){
        final List<GrantedAuthority> authorities = service.loadByUsername("ffroliva");
        Assertions.assertFalse(authorities.isEmpty());
    }

    @Test
    void loadByUsername_ResourceNotFound(){
        Assertions.assertThrows(ResourceNotFoundException.class, ()-> service.loadByUsername("user"));
    }

}
