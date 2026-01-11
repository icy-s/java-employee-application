package icy.java_employee_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// аннотация ниже позволяет этому классу быть обработчиком HTTP запросов (REST API)
@RestController
@SpringBootApplication
public class JavaEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaEmployeeApplication.class, args);
	}

	// метод ниже является обработчиком для GET запросов
	@GetMapping
	public List<String> helloWorld() {
		return List.of(
			"Hello World!",
			"Hello World!");
	}

}
