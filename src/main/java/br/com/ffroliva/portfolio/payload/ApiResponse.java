package br.com.ffroliva.portfolio.payload;

import br.com.ffroliva.portfolio.config.properties.IMessageProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class ApiResponse {
    private final Boolean success;
    private final String message;
}
