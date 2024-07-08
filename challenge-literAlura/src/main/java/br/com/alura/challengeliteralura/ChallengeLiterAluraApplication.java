package br.com.alura.challengeliteralura;

import br.com.alura.challengeliteralura.principal.Principal;
import br.com.alura.challengeliteralura.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiterAluraApplication implements CommandLineRunner {

	@Autowired
	private AutorService service;
	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var principal = new Principal(service);
		principal.exibeMenu();
	}
}
