package it.fabrick.meteo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class MeteoApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MeteoApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", getPort()));
		app.run(args);
	}

	static String getPort() {
		String port = System.getenv("PORT");
		if (port == null) {
			port = "8080";
		}
		return port;
	}
}
