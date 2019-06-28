package br.com.ffroliva.portfolio.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.ffroliva.portfolio.model.Role;
import br.com.ffroliva.portfolio.model.User;
import br.com.ffroliva.portfolio.model.UserRole;
import br.com.ffroliva.portfolio.model.enums.RoleName;
import br.com.ffroliva.portfolio.model.id.UserRoleId;

@Slf4j
@SpringBootTest
public class UserRoleRepositoryTest {
	
	private static final String USERNAME = "ffroliva";
	
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
