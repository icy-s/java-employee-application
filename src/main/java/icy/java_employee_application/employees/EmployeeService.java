package icy.java_employee_application.employees;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

// аннотация ниже помечает этот класс как сервис, позволяя применить в контроллере аннотацию Autowired
@Service

// данный сервис задаёт бизнес логику

public class EmployeeService {
    public List<Employee>getAllEmployees() {
        return List.of(
			new Employee(1L, "Mark", "mark@gmail.com", LocalDate.of(2000, 1, 10), 24, 10000),
			new Employee(1L, "Sean", "sean@gmail.com", LocalDate.of(2002, 3, 10), 22, 20000)
		);
    }
}