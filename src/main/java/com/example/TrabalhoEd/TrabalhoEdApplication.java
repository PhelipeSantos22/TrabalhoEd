package com.example.TrabalhoEd;

import com.example.TrabalhoEd.service.CsvService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrabalhoEdApplication {
	public static void main(String[] args) {
		SpringApplication.run(TrabalhoEdApplication.class, args);
		CsvService csvService = new CsvService();
		csvService.verificarOuCriarArquivos();
	}
}
