package se.iths.johan.grupp_5_projektarbete.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.iths.johan.grupp_5_projektarbete.exception.LeaveValidationException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LeaveValidatorTest {

    private LeaveValidator leaveValidator;

    @BeforeEach
    void setUp() {
        leaveValidator = new LeaveValidator();
    }

    @Test
    @DisplayName("giltigt employeeName ska inte kasta exception")
    void validateEmployeeNameValid() {
        assertDoesNotThrow(() -> leaveValidator.validateEmployeeName("Sara"));
    }

    @Test
    @DisplayName("employeeName null ska kasta exception")
    void validateEmployeeNameNull() {
        assertThrows(LeaveValidationException.class,
                () -> leaveValidator.validateEmployeeName(null));
    }

    @Test
    @DisplayName("employeeName blank ska kasta exception")
    void validateEmployeeNameBlank() {
        assertThrows(LeaveValidationException.class,
                () -> leaveValidator.validateEmployeeName(" "));
    }

    @Test
    @DisplayName("employeeName för långt ska kasta exception")
    void validateEmployeeNameTooLong() {
        String tooLong = "a".repeat(256);
        assertThrows(LeaveValidationException.class,
                () -> leaveValidator.validateEmployeeName(tooLong));
    }

    @Test
    @DisplayName("startDate giltigt ska inte kasta exception")
    void validateStartDateValid() {
        assertDoesNotThrow(() -> leaveValidator.validateStartDate(LocalDate.now()));
    }

    @Test
    @DisplayName("startDate null ska kasta exception")
    void validateStartDateNull() {
        assertThrows(LeaveValidationException.class,
                () -> leaveValidator.validateStartDate(null));
    }

    @Test
    @DisplayName("endDate efter startDate ska inte kasta exception")
    void validateEndDateValidOrder() {
        LocalDate start = LocalDate.of(2026, 2, 1);
        LocalDate end = LocalDate.of(2026, 2, 10);

        assertDoesNotThrow(() -> leaveValidator.validateEndDate(start, end));
    }

    @Test
    @DisplayName("endDate före startDate ska kasta exception")
    void validateEndDateInvalidOrder() {
        LocalDate start = LocalDate.of(2026, 2, 10);
        LocalDate end = LocalDate.of(2026, 2, 1);

        assertThrows(LeaveValidationException.class,
                () -> leaveValidator.validateEndDate(start, end));
    }

    @Test
    @DisplayName("endDate null ska inte kasta exception")
    void validateEndDateNull() {
        LocalDate start = LocalDate.of(2026, 2, 1);

        assertDoesNotThrow(() -> leaveValidator.validateEndDate(start, null));
    }
}
