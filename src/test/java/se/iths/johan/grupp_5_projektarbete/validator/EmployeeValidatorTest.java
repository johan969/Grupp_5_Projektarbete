package se.iths.johan.grupp_5_projektarbete.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.iths.johan.grupp_5_projektarbete.exception.EmployeeValidationException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeValidatorTest {

    private EmployeeValidator employeeValidator;

    @BeforeEach
    void setUp() {
        employeeValidator = new EmployeeValidator();
    }

    @Test
    @DisplayName("giltigt namn ska inte kasta exception")
    void validateEmployeeTest() {
        assertDoesNotThrow(() -> employeeValidator.validateEmployee("Asli"));
    }

    @Test
    @DisplayName("ogiltigt namn(null) ska kasta exception")
    void validateEmployeeNullTest() {
        assertThrows(EmployeeValidationException.class,
                () -> employeeValidator.validateEmployee(null));
    }

    @Test
    @DisplayName("tomt namn ska kastas")
    void validateEmployeeEmptyTest() {
        assertThrows(EmployeeValidationException.class,
                () -> employeeValidator.validateEmployee(" "));
    }

    @Test
    @DisplayName("giltig lön")
    void validateSalaryTest() {
        assertDoesNotThrow(() -> employeeValidator.validateSalary(30000));
    }

    @Test
    @DisplayName("ogiltig lön")
    void validateSalaryNegativeTest() {
        assertThrows(EmployeeValidationException.class,
                () -> employeeValidator.validateSalary(-1));
    }

    @Test
    @DisplayName("för hög lön")
    void validateSalaryTooHigh() {
        assertThrows(EmployeeValidationException.class,
                () -> employeeValidator.validateSalary(400000));
    }

    @Test
    @DisplayName("lyckad validering av anställningstyp")
    void validateEmploymentTypeTest() {
        assertDoesNotThrow(() -> employeeValidator.validateEmploymentType("Permanent"));
    }

    @Test
    @DisplayName("misslyckad validering av anställningstyp")
    void validateEmploymentTypeFailTest() {
        assertThrows(EmployeeValidationException.class,
                () -> employeeValidator.validateEmploymentType("Havsutter"));
    }

    @Test
    @DisplayName("validera anställnings %")
    void validateEmploymentPercentageTest() {
        assertDoesNotThrow(() -> employeeValidator.validateEmploymentPercentage(100));
    }

    @Test
    @DisplayName("misslyckad validering av anstälnnings %")
    void validateEmploymentPercentageFailTest() {
        assertThrows(EmployeeValidationException.class,
                () -> employeeValidator.validateEmploymentPercentage(-100));

        assertThrows(EmployeeValidationException.class,
                () -> employeeValidator.validateEmploymentPercentage(200));
    }

    @Test
    void validateEmployeeTypeTest() {
        assertDoesNotThrow(
                () -> employeeValidator.validateEmployeeType("Employee"));
    }

    @Test
    void validateEmployeeTypeFailTest() {
        assertThrows(EmployeeValidationException.class,
                () -> employeeValidator.validateEmployeeType("Pingvin"));
    }
}
