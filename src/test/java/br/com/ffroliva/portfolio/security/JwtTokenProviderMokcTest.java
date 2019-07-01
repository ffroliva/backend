package br.com.ffroliva.portfolio.security;

import br.com.ffroliva.portfolio.model.Role;
import br.com.ffroliva.portfolio.model.User;
import br.com.ffroliva.portfolio.model.UserPrincipal;
import br.com.ffroliva.portfolio.model.UserRole;
import br.com.ffroliva.portfolio.model.enums.RoleName;
import com.google.common.collect.Lists;
import io.jsonwebtoken.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class JwtTokenProviderMokcTest {
	
	private static final String TOKEN = "token";

	@Mock
	JwtParser jwtParser;

	@Mock
	Authentication authentication;

	@InjectMocks
	JwtTokenProvider jwtTokenProvider;

	@Test
	void generateToken(){
		Assertions.assertNotNull(generateToken(10000));
	}

	private String generateToken(int i) {
		ReflectionTestUtils.setField(jwtTokenProvider, "jwtSecret", "secret");
		ReflectionTestUtils.setField(jwtTokenProvider, "jwtExpirationInMs", i);
		when(authentication.getPrincipal())
				.thenReturn(this.getUserPrincipal());
		return jwtTokenProvider.generateToken(authentication);
	}

	@Test
	void testSignatureException() {
		String token = generateToken(1000);
		Assertions.assertFalse(jwtTokenProvider.validateToken(token+"fds"));

	}
	
	@Test
	void testMalformedJwtException(){
		String token = generateToken(1000) ;
		Assertions.assertFalse(jwtTokenProvider.validateToken("fsdssd"));
	}
	
	@Test
	void testExpiredJwtExpcetion(){
		generateToken(1);
		String token = jwtTokenProvider.generateToken(authentication);
		Assertions.assertFalse(jwtTokenProvider.validateToken(token));
	}
	
	@Test
	void testUnsupportedJwtException(){
		Assertions.assertFalse(jwtTokenProvider.validateToken(""));
	}
	
	@Test
	void testIllegalArgumentException(){
		Assertions.assertFalse(jwtTokenProvider.validateToken("malformedjwt"));
	}


	@Test
	void testValidToken() {
		String token = generateToken(1000);
		Assertions.assertTrue(jwtTokenProvider.validateToken(this.generateToken(1000)));

	}

	private UserPrincipal getUserPrincipal(){
		return UserPrincipal
				.create(User
						.of(1,
								"ffroliva",
								"Flavio",
								"Oliva",
								"ffroliva@gmail.com",
								"123456",
								Lists.newArrayList(UserRole.of(null,
										Role.of(RoleName.ROLE_USER)))));
	}

}
