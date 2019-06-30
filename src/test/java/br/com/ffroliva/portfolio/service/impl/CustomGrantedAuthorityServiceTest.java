package br.com.ffroliva.portfolio.service.impl;

import br.com.ffroliva.portfolio.exception.ResourceNotFoundException;
import br.com.ffroliva.portfolio.model.Role;
import br.com.ffroliva.portfolio.model.enums.RoleName;
import br.com.ffroliva.portfolio.repository.RoleRepository;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CustomGrantedAuthorityServiceTest {

    private static final String USERNAME = "ffroliva";

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    CustomGrantedAuthorityService customGrantedAuthorityService;

    @Test
    void loadByUsername() {
        Mockito.when(roleRepository.loadRolesByUsername(USERNAME))
                .thenReturn(Optional.of(Lists.newArrayList(Role.of(RoleName.ROLE_USER))));
        List<GrantedAuthority> authorities = customGrantedAuthorityService.loadByUsername(USERNAME);
        Assertions.assertEquals(1, authorities.size());
    }

    @Test
    void loadByUsername_resource_not_found() {
        Mockito.when(roleRepository.loadRolesByUsername(USERNAME))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> customGrantedAuthorityService.loadByUsername(USERNAME));
    }

}
