package br.com.ffroliva.portfolio.controller;


import static br.com.ffroliva.portfolio.config.properties.MessageProperty.EMAIL_ALREADY_IN_USE;
import static br.com.ffroliva.portfolio.config.properties.MessageProperty.USERNAME_ALREADY_TAKEN;
import static br.com.ffroliva.portfolio.config.properties.MessageProperty.USER_REGISTERED_SUCCESSFULLY;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.context.WebApplicationContext;

import br.com.ffroliva.portfolio.PortfolioBackendApplication;
import br.com.ffroliva.portfolio.payload.LoginRequest;
import br.com.ffroliva.portfolio.payload.SignupRequest;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK, 
		classes = { PortfolioBackendApplication.class })
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class AuthenticationControllerTest {
	
    @Autowired
    private WebApplicationContext webAppContextSetup;

    @BeforeEach
    public void before() {
        RestAssuredMockMvc.webAppContextSetup(webAppContextSetup);
    }
	
	@Test
	public void signup_with_valid_username_and_password(){
        given()
        	.contentType(JSON)
			.body(SignupRequest.of("Ed", "Oliva", "edoliva", "test@gmail.com", "123456"))
		.post("/api/auth/signup")
			.then()
				.statusCode(CREATED.value())
			.assertThat()
				.body("success", equalTo(true)) 
				.body("message", equalTo(USER_REGISTERED_SUCCESSFULLY.message()));
	}
	
	@Test
	public void signup_with_already_taken_username(){
        given()
        	.contentType(JSON)
			.body(SignupRequest.of("Flavio", "Oliva", "ffroliva", "test@gmail.com", "123456"))
		.post("/api/auth/signup")
			.then()
				.statusCode(BAD_REQUEST.value())
			.assertThat()
				.body("success", equalTo(false)) 
				.body("message", equalTo(USERNAME_ALREADY_TAKEN.message()));
	}

	@Test
	public void signup_with_email_already_in_use(){
        given()
        	.contentType(JSON)
			.body(SignupRequest.of("Flavio", "Oliva", "newuser", "ffroliva@gmail.com", "123456"))
		.post("/api/auth/signup")
			.then()
				.statusCode(BAD_REQUEST.value())
			.assertThat()
				.body("success", equalTo(false)) 
				.body("message", equalTo(EMAIL_ALREADY_IN_USE.message()));
	}

	// TODO: Review Global Exception Handler to make this test pass.
	@Disabled
	@Test
	public void signup_with_email_with_invalid_min_size(){
        given()
        	.contentType(JSON)
			.body(SignupRequest.of("Flavio", "Oliva", "newuser", "test@gmail.com", "123"))
		.post("/api/auth/signup")
			.then()
				.statusCode(BAD_REQUEST.value())
			.assertThat()
				.body("success", equalTo(false)) 
				.body("message", equalTo(EMAIL_ALREADY_IN_USE.message()));
	}
	
	@Test
	public void signin_with_invalid_user_and_password(){
        given()
        	.contentType(JSON)
			.body(LoginRequest.of("ffroliva", "123456"))
		.post("/api/auth/signin")
			.then()
				.statusCode(HttpStatus.OK.value())
			.assertThat()
				.body("accessToken", notNullValue()); 
	}
	
	@Test
	public void signin_with_wrong_username_and_password(){
        given()
    	.contentType(ContentType.JSON)
		.body(LoginRequest.of("wronguser", "wrongpass"))
	.post("/api/auth/signin")
		.then()
			.statusCode(HttpStatus.UNAUTHORIZED.value()); 		
	}
	
}
