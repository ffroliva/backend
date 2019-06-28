package br.com.ffroliva.portfolio.payload;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class LoginRequest {
    @NotBlank
    private final String usernameOrEmail;

    @NotBlank
    private final String password;

}
