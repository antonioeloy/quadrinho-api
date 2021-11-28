package br.com.zup.quadrinho.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuadrinhoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuadrinhoApiApplication.class, args);
	}

}
