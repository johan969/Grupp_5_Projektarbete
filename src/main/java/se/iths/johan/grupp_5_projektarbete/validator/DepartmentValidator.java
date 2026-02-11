package se.iths.johan.grupp_5_projektarbete.validator;

import org.springframework.stereotype.Component;
import se.iths.johan.grupp_5_projektarbete.exception.DepartmentValidationException;

@Component
public class DepartmentValidator {

    // valideringsmetoder för attributen i Department-klassen

    public void validateDepartmentName(String name) {
        if (name == null || name.isBlank()) {
            throw new DepartmentValidationException("Department cannot be null or empty");
        }
    }

    public void validateCostCenter(int costCenter) {
        int min = 10000, max = 99999;
        if (costCenter < min || costCenter > max) {
            throw new DepartmentValidationException("Cost center must be a " +
                    "5-digit number between " + min + " and " + max + ".");
        }
    }

    public void validateLocation(String location) {
        if (location == null || location.isBlank()) {
            throw new DepartmentValidationException("Location cannot be null or empty");
        }
    }

    public void validateNumberOfEmployees(int numberOfEmployees) {
        if (numberOfEmployees < 0) {
            throw new DepartmentValidationException("Number of employees cannot be negative");
        }
    }
}
