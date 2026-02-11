package se.iths.johan.grupp_5_projektarbete.model;

import jakarta.persistence.*;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "department_name", nullable = false, unique = true)
    private String departmentName;
    @Column(name = "cost_center", nullable = false, unique = true)
    private int costCenter;
    private String location;
    @Column(name = "number_of_employees", nullable = false)
    private int numberOfEmployees;

    public Department() {
    }


    public Long getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(int costCenter) {
        this.costCenter = costCenter;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", costCenter=" + costCenter +
                ", location='" + location + '\'' +
                ", numberOfEmployees=" + numberOfEmployees +
                '}';
    }
}
