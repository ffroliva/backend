package br.com.ffroliva.portfolio.api;

import br.com.ffroliva.portfolio.payload.EnderecoResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@Slf4j
@ExtendWith(MockitoExtension.class)
class CepApiTest {
    private static final String CEP_216_A = "70295010";
    private static final String LOGRADOURO = "SQS 216 BLOCO A";
    private static final String COMPLEMENTO = "";
    private static final String BAIRRO = "ASA SUL";
    private static final String LOCALIDADE = "";
    private static final String UF = "DF";
    private static final String UNIDADE = "";
    private static final String IBGE = "";

    @Mock
    private CepApi cepApi;

    @Test
    void obterCep() {
        Mockito.when(cepApi.obterPorCep(CEP_216_A))
                .thenReturn(ResponseEntity
                        .ok(EnderecoResponse
                                .of(CEP_216_A,
                                        LOGRADOURO,
                                        COMPLEMENTO,
                                        BAIRRO,
                                        LOCALIDADE,
                                        UF,
                                        UNIDADE,
                                        IBGE)));
        final ResponseEntity<EnderecoResponse> response = cepApi.obterPorCep(CEP_216_A);

        Assertions.assertEquals(response.getBody().getCep(), CEP_216_A);
        Assertions.assertEquals(response.getBody().getBairro(), BAIRRO);
        Assertions.assertEquals(response.getBody().getComplemento(), COMPLEMENTO);
        Assertions.assertEquals(response.getBody().getIbge(), IBGE);
        Assertions.assertEquals(response.getBody().getLocalidade(), LOCALIDADE);
        Assertions.assertEquals(response.getBody().getUf(), UF);
        Assertions.assertEquals(response.getBody().getLogradouro(), LOGRADOURO);
        Assertions.assertEquals(response.getBody().getUnidade(), UNIDADE);

    }
}
