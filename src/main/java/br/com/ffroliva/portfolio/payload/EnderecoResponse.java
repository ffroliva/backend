package br.com.ffroliva.portfolio.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class EnderecoResponse  implements Serializable {

    @NotNull
    @Size(max = 8)
    private String cep;

    @NotNull
    private String logradouro;

    @NotNull
    @Size(max = 36)
    private String complemento;

    @NotNull
    @Size(max = 250)
    private String bairro;

    @NotNull
    @Size(max = 72)
    private String localidade;

    @NotNull
    @Size(max = 2)
    private String uf;

    private String unidade;

    @NotNull
    @Size(max = 7)
    private String ibge;

}
