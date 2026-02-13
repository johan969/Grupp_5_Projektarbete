package se.iths.johan.grupp_5_projektarbete.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.iths.johan.grupp_5_projektarbete.exception.DepartmentValidationException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DepartmentValidatorTest {

    private DepartmentValidator departmentValidator;

    @BeforeEach
    public void setUp() {
        departmentValidator = new DepartmentValidator();
    }

    @Test
    @DisplayName("Ska testa framgångsrik validering av avdelningsnamn")
    void testValidateDepartmentName() {
        assertDoesNotThrow(() -> departmentValidator.validateDepartmentName("IT")
        );
    }

    @Test
    @DisplayName("Ska testa att exception kastas vid ogiltigt (null) avdelningsnamn")
    void testValidateDepartmentNameNullThrowsException() {
        assertThrows(DepartmentValidationException.class,
                () -> departmentValidator.validateDepartmentName(null)
        );
    }

    @Test
    @DisplayName("Ska testa att exception kastas vid ogiltigt (blank) avdelningsnamn")
    void testValidateDepartmentNameBlankThrowsException() {
        assertThrows(DepartmentValidationException.class,
                () -> departmentValidator.validateDepartmentName(" ")
        );
    }

    @Test
    @DisplayName("Ska testa framgångsrik validering av kostnadsställe")
    void testValidateCostCenter() {
        assertDoesNotThrow(() -> departmentValidator.validateCostCenter(67890)
        );
    }

    @Test
    @DisplayName("Ska testa att exception kastas vid ogiltigt (för litet) kostnadsställe")
    void testValidateCostCenterTooSmallThrowsException() {
        assertThrows(DepartmentValidationException.class,
                () -> departmentValidator.validateCostCenter(9999)
        );
    }

    @Test
    @DisplayName("Ska testa att exception kastas vid ogiltigt (för stort) kostnadsställe")
    void testValidateCostCenterTooLargeThrowsException() {
        assertThrows(DepartmentValidationException.class,
                () -> departmentValidator.validateCostCenter(100000)
        );
    }

    @Test
    @DisplayName("Ska testa framgångsrik validering av plats")
    void testValidateLocation() {
        assertDoesNotThrow(() -> departmentValidator.validateLocation("Malmö")
        );
    }

    @Test
    @DisplayName("Ska testa att exception kastas vid ogiltig (null) plats")
    void testValidateLocationNullThrowsException() {
        assertThrows(DepartmentValidationException.class,
                () -> departmentValidator.validateLocation(null)
        );
    }

    @Test
    @DisplayName("Ska testa att exception kastas vid ogiltig (blank) plats")
    void testValidateLocationBlankThrowsException() {
        assertThrows(DepartmentValidationException.class,
                () -> departmentValidator.validateLocation(" ")
        );
    }

    @Test
    @DisplayName("Ska testa framgångsrik validering av antalet anställda")
    void testValidateNumberOfEmployees() {
        assertDoesNotThrow(() -> departmentValidator.validateNumberOfEmployees(25)
        );
    }

    @Test
    @DisplayName("Ska testa att exception kastas vid ogiltigt (<0) antal anställda")
    void testValidateNumberOfEmployeesThrowsException() {
        assertThrows(DepartmentValidationException.class,
                () -> departmentValidator.validateNumberOfEmployees(-10)
        );
    }
}