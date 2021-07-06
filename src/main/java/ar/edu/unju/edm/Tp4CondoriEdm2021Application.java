package ar.edu.unju.edm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.service.IClienteService;

@SpringBootApplication
public class Tp4CondoriEdm2021Application implements CommandLineRunner{

	@Autowired
	@Qualifier("impmysql")
	IClienteService clienteService;
	
	@Autowired
	Cliente cliente;
	public static void main(String[] args) {
		SpringApplication.run(Tp4CondoriEdm2021Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
	}
}
