package br.com.ffroliva.portfolio.payload;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class LoginRequest {
    @NotBlank
    private final String usernameOrEmail;

    @NotBlank
    private final String password;

}
