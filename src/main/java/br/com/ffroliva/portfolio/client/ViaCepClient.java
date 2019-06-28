package br.com.ffroliva.portfolio.client;

import org.springframework.cloud.openfeign.FeignClient;

import br.com.ffroliva.portfolio.api.CepApi;


@FeignClient(name = "viacep", url = "${integration.viacep.url}")
public interface ViaCepClient extends CepApi {
}
