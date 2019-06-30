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
        assertEquals(roles.get().size(), 1);
        Role role = roles.get().get(0);
        assertEquals(role.getId(), 1);
        assertEquals(role.getName(), RoleName.ROLE_USER);
    }
}
