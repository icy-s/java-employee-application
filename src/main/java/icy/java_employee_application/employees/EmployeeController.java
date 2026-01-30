package icy.java_employee_application.employees;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping
    public Employee creatEmployee(
        @RequestBody Employee employee
    ) {
        return employeeService.createEmployee(employee);
    }

    // передаётся ид работника, который будет удалён
    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(
        @PathVariable("employeeId") Long id
    ) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/{employeeId}")
    public void updateEmployee(
        @PathVariable("employeeId") Long id,
        @RequestParam(value = "email", required = false) String email,
        @RequestParam(value = "salary", required = false) Integer salary
    ) {
        employeeService.updateEmployee(id, email, salary);
    }
}
