package br.com.ffroliva.portfolio.repository;

import br.com.ffroliva.portfolio.model.Role;
import br.com.ffroliva.portfolio.model.enums.RoleName;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void findRole() {
        log.info("Find roles...");
        final Optional<Role> role = roleRepository.findByName(RoleName.ROLE_USER);
        assertTrue(role.isPresent());
    }

    @Test
    void loadRolesByUsername() {
        final Optional<List<Role>> roles = roleRepository.loadRolesByUsername("ffroliva");
        assertTrue(roles.isPresent());
        assertEquals(1, roles.get().size());
        Role role = roles.get().get(0);
        assertEquals(1, role.getId());
        assertEquals(RoleName.ROLE_USER, role.getName());
    }
}
