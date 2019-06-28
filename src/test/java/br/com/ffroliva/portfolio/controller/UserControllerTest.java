package br.com.ffroliva.portfolio.controller;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.context.WebApplicationContext;

import br.com.ffroliva.portfolio.PortfolioBackendApplication;
import br.com.ffroliva.portfolio.payload.LoginRequest;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = { PortfolioBackendApplication.class })
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class UserControllerTest extends BaseTestController {

	@Autowired
	private WebApplicationContext webAppContextSetup;

	@BeforeEach
	public void before() {
		RestAssuredMockMvc.webAppContextSetup(webAppContextSetup);
	}
	public String getAuthorization(String usernameOrEmail, String password) {
		MockMvcResponse response = given()
				.contentType(JSON)
				.body(LoginRequest.of(usernameOrEmail, password))
				.post("/api/auth/signin");
		String accessToken = response.getBody().jsonPath().get("accessToken");
		String authorization = "Bearer " + accessToken;
		log.info(authorization);
		return authorization;
	}

	@Test
	public void find_user_by_username_authenticated() {
		given()
			.contentType(JSON)
			.header("Authorization", this.getAuthorization("ffroliva", "123456"))
			.get("/api/private/users/ffroliva")
		.then()
			.statusCode(HttpStatus.OK.value())
		.assertThat()
			//.body("id", notNullValue())
			.body("firstName", equalTo("Flavio"))
			.body("lastName", equalTo("Oliva"))
			.body("email", equalTo("ffroliva@gmail.com"))
			.body("username", equalTo("ffroliva"))
			.body("password", notNullValue());
	}

	@Test
	public void find_user_by_username_without_authentication() {
		given().contentType(JSON).get("/api/private/users/ffroliva").then()
				.statusCode(UNAUTHORIZED.value());
	}

	@Test
	public void find_user_with_unknown_username() {
		given()
			.contentType(JSON)
			.header("Authorization", this.getAuthorization("ffroliva", "123456"))
			.get("/api/private/users/unknownuser")
		.then()
			.statusCode(NOT_FOUND.value());

	}
}
