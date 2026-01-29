package icy.java_employee_application.employees;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

// аннотация ниже объявляет сущность, которая будет сохраняться в базу данных
@Entity

// определение названия таблицы в базе данных
@Table(name = "employee")

public class Employee {
    // обозначаем какая строчка у нас будет primary key с помощью Id
    @Id
    @SequenceGenerator(
        name = "employee_sequence",
        sequenceName = "employee_sequence",
        allocationSize = 1
    )
    // как база данных будет генерировать Id (для этого будет использоваться написанный выше sequence)
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "employee_sequence"
    )
    
    // id обычно делаются как Long, чтобы до сохранения в бд id мог быть null
    private Long id;
    private String name;
    private String email;
    private LocalDate birthDate;

    // аннотация дает возможность не записывать поле 'age' в базу данных, так как оно будет высчитываться из birthDate
    @Transient
    private Integer age;

    private Integer salary;
    
    // чтобы hibernate заработал, нужно создать пустой конструктор
    public Employee() {
    }

    public Employee(Long id, String name, String email, LocalDate birthDate, Integer salary) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.birthDate = birthDate;
    this.age = Period.between(
        birthDate,
        LocalDate.now()
    ).getYears();
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
        if (age == null) {
            this.age = Period.between(
                birthDate,
                LocalDate.now()
            ).getYears();
        }
        return age;
    }

    public Integer getSalary() {
        return salary;
    }

}