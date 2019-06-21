package br.com.ffroliva.portfolio;

import br.com.ffroliva.portfolio.client.ViaCepClient;
import br.com.ffroliva.portfolio.payload.EnderecoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.http.ResponseEntity;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
@EntityScan(basePackageClasses = {
		PortfolioBackendApplication.class,
		Jsr310JpaConverters.class
})
@EnableFeignClients
public class PortfolioBackendApplication implements CommandLineRunner {

	private final ViaCepClient viaCepClient;

	public static void main(String[] args) {
		SpringApplication.run(PortfolioBackendApplication.class, args);
	}

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("BRT"));
	}


	@Override
	public void run(String... args) throws Exception {
		//final ResponseEntity<EnderecoResponse> enderecoResponseEntity = viaCepClient.obterPorCep("70295010");
		//log.debug(enderecoResponseEntity.getBody().toString());
	}
}
