package br.com.ffroliva.portfolio.client;

import br.com.ffroliva.portfolio.api.CepApi;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "viacep", url = "${integration.viacep.url}")
public interface ViaCepClient extends CepApi {
}
