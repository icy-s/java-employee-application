package icy.java_employee_application;
import icy.java_employee_application.employees.Employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.time.LocalDate;

// аннотация ниже позволяет этому классу быть обработчиком HTTP запросов (REST API)
@RestController
@SpringBootApplication
public class JavaEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaEmployeeApplication.class, args);
	}

	// метод ниже является обработчиком для GET запросов
	@GetMapping
	public List<Employee> helloWorld() {
		return List.of(
			new Employee(1L, "Mark", "mark@gmail.com", LocalDate.of(2000, 1, 10), 24, 10000),
			new Employee(1L, "Sean", "sean@gmail.com", LocalDate.of(2002, 3, 10), 22, 20000)
		);
	}
}
