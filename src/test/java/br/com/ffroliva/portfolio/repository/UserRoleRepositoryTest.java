package br.com.ffroliva.portfolio.repository;

import br.com.ffroliva.portfolio.model.Role;
import br.com.ffroliva.portfolio.model.User;
import br.com.ffroliva.portfolio.model.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
public class UserRoleRepositoryTest {
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired 
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Test
	public void findById(){
		List<UserRole> userRoles = Lists.newArrayList(userRoleRepository.findAll());
 		log.info("Quantity of UserRoles: " + userRoles.size());
 		
		UserRole userRole = userRoles.get(0);
		Optional<Role> role = roleRepository.findById(userRole.getId().getRole().getId());
		Optional<User> user = userRepository.findById(userRole.getId().getUser().getId());
		assertEquals(userRole.getId().getUser().getId(), user.get().getId());
		assertEquals(userRole.getId().getRole().getId(), role.get().getId());
	}
}
