package se.iths.johan.grupp_5_projektarbete.validator;

import org.springframework.stereotype.Component;
import se.iths.johan.grupp_5_projektarbete.exception.LeaveValidationException;

import java.time.LocalDate;

@Component
public class LeaveValidator {

    public void validateEmployeeName(String employeeName) {
        if (employeeName == null || employeeName.isBlank()) {
            throw new LeaveValidationException("employeeName must not be blank");
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
    
}
