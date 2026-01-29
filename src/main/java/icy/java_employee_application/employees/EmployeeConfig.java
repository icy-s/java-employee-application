package icy.java_employee_application.employees;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// эта аннотация означает, что этот класс используется как конфигурация spring boot и здесь описываются bean, которые необходимо создать
@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner commandLineRunner(
        EmployeeRepository employeeRepository
    ) {
        return (args) -> {
            var employeeList = List.of(
                // id больше не надо прописывать вручную, они будут генерироваться через sequence сами
			    new Employee(null, "Mark", "mark@gmail.com", LocalDate.of(2000, 1, 10), 10000),
			    new Employee(null, "Sean", "sean@gmail.com", LocalDate.of(2002, 3, 10), 20000)
		    );
            employeeRepository.saveAll(employeeList);
        };
    }
}