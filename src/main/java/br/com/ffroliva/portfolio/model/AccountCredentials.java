package br.com.ffroliva.portfolio.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor (staticName = "of")
public class AccountCredentials {

    private final String username;
    private final String password;
}
