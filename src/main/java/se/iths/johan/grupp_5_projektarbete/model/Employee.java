package se.iths.johan.grupp_5_projektarbete.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "monthly_salary", nullable = false)
    private int monthlySalary;

    @Column(name = "employment_type", nullable = false)
    private String employmentType;

    @Column(name = "employment_percentage", nullable = false)
    private int employmentPercentage;

    @Column(name = "employee_type", nullable = false)
    private String employeeType;

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(int monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public int getEmploymentPercentage() {
        return employmentPercentage;
    }

    public void setEmploymentPercentage(int employmentPercentage) {
        this.employmentPercentage = employmentPercentage;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    @Override
    public String toString() {
        return "Employee\n" +
                "id: " + id + "\n" +
                "employee name: " + name + "\n" +
                "monthly salary: " + monthlySalary + "\n" +
                "employment type: " + employmentType + "\n" +
                "employment percentage: " + employmentPercentage + "\n" +
                "employee type: " + employeeType;
    }
}