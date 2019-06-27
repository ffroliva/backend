package br.com.ffroliva.portfolio.repository;

import br.com.ffroliva.portfolio.model.Role;
import br.com.ffroliva.portfolio.model.enums.RoleName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void findRolesByUsername() {
        final Optional<List<Role>> roles = roleRepository.findRolesByUsername("ffroliva");
        roles.get().stream().forEach(r -> log.debug(r.toString()));
        Assertions.assertTrue(roles.isPresent());
    }

    @Test
    public void findRolesByUsername_invalidUser() {
        final Optional<List<Role>> roles = roleRepository.findRolesByUsername("invalid_user");
        Assertions.assertFalse(roles.isPresent());
    }

    @Test
    public void findRole() {
        final Optional<Role> role = roleRepository.findByName(RoleName.ROLE_USER);
        Assertions.assertTrue(role.isPresent());
    }

    @Test
    public void loadRolesByUsername() {
        final Optional<List<Role>> roles = roleRepository.loadRolesByUsername("ffroliva");
        roles.get().stream().forEach(r -> log.debug(r.toString()));
        Assertions.assertTrue(roles.isPresent());
    }
}
