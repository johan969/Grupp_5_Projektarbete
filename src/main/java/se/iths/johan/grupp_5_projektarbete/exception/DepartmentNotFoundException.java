package se.iths.johan.grupp_5_projektarbete.exception;

public class DepartmentNotFoundException extends RuntimeException {
    
    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
