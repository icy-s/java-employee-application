package icy.java_employee_application.employees;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends
        // наследует репозиторий от самого spring boot (с типом сущности и типом идентификатора в скобках)
        JpaRepository<Employee, Long>{

    Optional<Employee> findByEmail(String email);
}