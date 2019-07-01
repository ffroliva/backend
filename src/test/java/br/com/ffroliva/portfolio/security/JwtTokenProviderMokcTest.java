package br.com.ffroliva.portfolio.security;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JwtTokenProviderMokcTest {
	
	private static final String TOKEN = "token";
	
	@Mock
	JwtTokenProvider jwtTokenProvider;
	
	@Test
	void testSignatureException(){
		Mockito.when(jwtTokenProvider.validateToken(TOKEN))
		.thenThrow(SignatureException.class);
		Assertions.assertThrows(SignatureException.class, () -> jwtTokenProvider.validateToken(TOKEN));
	}
	
	@Test
	void testMalformedJwtException(){
		Mockito.when(jwtTokenProvider.validateToken(TOKEN))
		.thenThrow(SignatureException.class);
		Assertions.assertThrows(SignatureException.class, () -> jwtTokenProvider.validateToken(TOKEN));		
	}
	
	@Test
	void testExpiredJwtExpcetion(){
		Mockito.when(jwtTokenProvider.validateToken(TOKEN))
		.thenThrow(MalformedJwtException.class);
		Assertions.assertThrows(MalformedJwtException.class, () -> jwtTokenProvider.validateToken(TOKEN));
	}
	
	@Test
	void testUnsupportedJwtException(){
		Mockito.when(jwtTokenProvider.validateToken(TOKEN))
		.thenThrow(UnsupportedJwtException.class);
		Assertions.assertThrows(UnsupportedJwtException.class, () -> jwtTokenProvider.validateToken(TOKEN));
	}
	
	@Test
	void testIllegalArgumentException(){
		Mockito.when(jwtTokenProvider.validateToken(TOKEN))
		.thenThrow(IllegalArgumentException.class);
		Assertions.assertThrows(IllegalArgumentException.class, () -> jwtTokenProvider.validateToken(TOKEN));
	}

}
