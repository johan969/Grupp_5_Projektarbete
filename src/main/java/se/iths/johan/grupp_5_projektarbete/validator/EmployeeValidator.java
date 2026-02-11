package se.iths.johan.grupp_5_projektarbete.validator;

import org.springframework.stereotype.Component;
import se.iths.johan.grupp_5_projektarbete.exception.EmployeeValidationException;
import se.iths.johan.grupp_5_projektarbete.model.Employee;

import java.util.List;

@Component
public class EmployeeValidator {
    private static final List<String> employmentType = List.of(
            "Permanent", "Fixed", "Part"
    );
    private static final List<String> employeeType = List.of(
            "Employee", "Consult", "Leased"
    );

    public void validateEmployee(Employee employee) {
        if (employee.getName() == null || employee.getName().isBlank()) {
            throw new EmployeeValidationException("Employee name is empty");
        }
    }

    public void validateSalary(Employee employee) {
        if (employee.getMonthlySalary() <= 0) {
            throw new EmployeeValidationException("Employee monthly salary is invalid");
        }
    }

    //validate för anställningsform
    public void validateEmploymentType(Employee employee) {
        if (!employmentType.contains(employee.getEmploymentType())) {
            throw new EmployeeValidationException("There is no such employment type");
        }
    }

    public void validateEmploymentPercentage(Employee employee) {
        if (employee.getEmploymentPercentage() < 0 || employee.getEmploymentPercentage() > 100) {
            throw new EmployeeValidationException("Employment percentage must be between 0 and 100");
        }
    }

    //validering för anställnings titel
    public void validateEmployeeType(Employee employee) {
        if (!employeeType.contains(employee.getEmployeeType())) {
            throw new EmployeeValidationException("There is no such employee type");
        }
    }
}
