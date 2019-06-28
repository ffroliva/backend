package br.com.ffroliva.portfolio;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.ffroliva.portfolio.model.Role;
import br.com.ffroliva.portfolio.model.User;
import br.com.ffroliva.portfolio.model.UserRole;
import br.com.ffroliva.portfolio.model.enums.RoleName;
import br.com.ffroliva.portfolio.repository.RoleRepository;
import br.com.ffroliva.portfolio.repository.UserRepository;
import br.com.ffroliva.portfolio.repository.UserRoleRepository;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
@EntityScan(basePackageClasses = {
		PortfolioBackendApplication.class,
		Jsr310JpaConverters.class
})
@EnableFeignClients
public class PortfolioBackendApplication implements CommandLineRunner {
	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(PortfolioBackendApplication.class, args);
	}

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("BRT"));
	}


	@Override
	public void run(String... args) throws Exception {

		log.info("Initializing App...");
		
		Role roleUser = roleRepository.save(Role.of(RoleName.ROLE_USER));
		roleRepository.save(Role.of(RoleName.ROLE_ADMIN));
		String encodedPassword = passwordEncoder.encode("123456");
		final User user = userRepository.save(User.of(
				"ffroliva",
				"Flavio",
				"Oliva",
				"ffroliva@gmail.com",
				encodedPassword));
		userRoleRepository.save(UserRole.of(user,roleUser));

	}
}
