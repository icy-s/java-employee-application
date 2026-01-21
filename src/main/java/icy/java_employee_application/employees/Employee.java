package icy.java_employee_application.employees;

import java.time.LocalDate;

public class Employee {
    // id обычно делаются как Long, чтобы до сохранения в бд id мог быть null
    private Long id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private Integer age;
    private Integer salary;

    public Employee(Long id, String name, String email, LocalDate birthDate, Integer age, Integer salary) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.birthDate = birthDate;
    this.age = age;
    this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSalary() {
        return salary;
    }

}