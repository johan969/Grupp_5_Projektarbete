package se.iths.johan.grupp_5_projektarbete.validator;

import org.springframework.stereotype.Component;
import se.iths.johan.grupp_5_projektarbete.exception.EmployeeValidationException;

import java.util.List;

@Component
public class EmployeeValidator {
    private static final List<String> employmentType = List.of(
            "Permanent", "Fixed term", "Part"
    );
    private static final List<String> employeeType = List.of(
            "Employee", "Consult", "Leased"
    );

    public void validateEmployee(String name) {
        if (name == null || name.isBlank()) {
            throw new EmployeeValidationException("Employee name is empty");
        }
    }

    public void validateSalary(int salary) {
        int min = 0, max = 200000;
        if (salary < min || salary > max) {
            throw new EmployeeValidationException("Employee monthly salary is invalid");
        }
    }

    //validate för anställningsform
    public void validateEmploymentType(String employment) {
        if (!employmentType.contains(employment)) {
            throw new EmployeeValidationException("There is no such employment type");
        }
    }

    public void validateEmploymentPercentage(int percentage) {
        int min = 0, max = 100;
        if (percentage < min || percentage > max) {
            throw new EmployeeValidationException("Employment percentage must be between 0 and 100");
        }
    }

    //validering för anställnings titel
    public void validateEmployeeType(String typeOfEmployee) {
        if (!employeeType.contains(typeOfEmployee)) {
            throw new EmployeeValidationException("There is no such employee type");
        }
    }
}
