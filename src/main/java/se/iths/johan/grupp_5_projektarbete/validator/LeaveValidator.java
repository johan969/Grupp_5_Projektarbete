package se.iths.johan.grupp_5_projektarbete.validator;

import org.springframework.stereotype.Component;
import se.iths.johan.grupp_5_projektarbete.exception.LeaveValidationException;

import java.time.LocalDate;

@Component
public class LeaveValidator {

    public void validateEmployeeName(String employeeName) {
        if (employeeName == null || employeeName.trim().isEmpty()) {
            throw new LeaveValidationException("employeeName must not be empty");
        }
    }

    public void validateStartDate(LocalDate startDate) {
        if (startDate == null) {
            throw new LeaveValidationException("startDate must not be null");
        }
    }

    public void validateEndDate(LocalDate startDate, LocalDate endDate) {
        if (endDate != null && startDate != null && endDate.isBefore(startDate)) {
            throw new LeaveValidationException("endDate must be after startDate");
        }
    }

    public void validateApproved(boolean approved) {
        // boolean är alltid true/false
    }
}
