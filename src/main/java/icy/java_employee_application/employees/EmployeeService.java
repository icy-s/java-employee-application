package icy.java_employee_application.employees;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// аннотация ниже помечает этот класс как сервис, позволяя применить в контроллере аннотацию Autowired
@Service

// данный сервис задаёт бизнес логику

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    public List<Employee>getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee createEmployee(Employee employee) {
        if (employee.getId() != null) {
          throw new IllegalArgumentException("Id must be empty");
        }
        if (employeeRepository.findByEmail(employee.getEmail())
            .isPresent()) {
          throw new IllegalArgumentException("Email is already registered");
        }
        if (employee.getSalary() <= 5000) {
          throw new IllegalArgumentException("Salary can't be less than 5000");
        }
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
      if (employeeRepository.findById(id).isEmpty()) {
        throw new IllegalArgumentException("Employee not found by id=%s"
            .formatted(id));
      }
      employeeRepository.deleteById(id);
    }
}