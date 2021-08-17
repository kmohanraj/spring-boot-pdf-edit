package com.example.pdfedit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableTask
public class PdfEditApplication {

	@Bean
	public CommandLineRunner commandLineRunner() {
		return new PdfEditCommandLineRunner();
	}

	public static void main(String[] args) {
		SpringApplication.run(PdfEditApplication.class, args);
	}

	public static class PdfEditCommandLineRunner implements CommandLineRunner {
		@Override
		public void run(String... strings) throws Exception {
			System.out.println("-----------------Task Application------------------");
		}
	}

}
