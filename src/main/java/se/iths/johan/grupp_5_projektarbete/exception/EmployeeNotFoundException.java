package se.iths.johan.grupp_5_projektarbete.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("Employee with id: " + id + " not found");
    }
}
