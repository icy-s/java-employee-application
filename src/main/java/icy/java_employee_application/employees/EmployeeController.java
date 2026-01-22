package icy.java_employee_application.employees;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// контроллер служит здесь только для обработки REST api запросов, бизнес логика находится в EmployeeService

// аннотация ниже позволяет этому классу быть обработчиком HTTP запросов (REST API)
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    // аннотация ниже говорит спрингу использовать конкретно эту реализацию конструктора контроллера
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
	public List<Employee> helloWorld() {
		return employeeService.getAllEmployees();
	}
}
