package br.com.ffroliva.portfolio.service;

import br.com.ffroliva.portfolio.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
