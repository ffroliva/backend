package br.com.ffroliva.portfolio.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.ffroliva.portfolio.payload.EnderecoResponse;

@Api(tags = "ViaCep")
public interface CepApi {

    @ApiOperation(value = "Obter dados de cep por número cep")
    @GetMapping(value = "/{cep}/json", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<EnderecoResponse> obterPorCep(@PathVariable(name = "cep") String numero);

}
