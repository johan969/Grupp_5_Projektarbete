package se.iths.johan.grupp_5_projektarbete.validator;

import org.springframework.stereotype.Component;
import se.iths.johan.grupp_5_projektarbete.exception.RoleValidationException;
import se.iths.johan.grupp_5_projektarbete.model.Role;

@Component
public class RoleValidator {

    public void validate(Role role) {
        if (role == null) {
            throw new RoleValidationException("Role cannot be null");
        }
        validateTitle(role.getTitle());
        validateLevel(role.getLevel());
        validateDescription(role.getDescription());
        // Behöver inte validera isManger
    }

    public void validateTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new RoleValidationException("Title cannot be null or empty");
        }
        if (title.length() > 30) {
            throw new RoleValidationException("Title length can not exceed 30 characters");
        }
    }

    public void validateLevel(String level) {
        if (level == null || level.isEmpty()) {
            throw new RoleValidationException("Level cannot be null or empty");
        }
        if (level.length() > 30) {
            throw new RoleValidationException("Level length can not exceed 30 characters");
        }
    }

    public void validateDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new RoleValidationException("Description cannot be null or empty");
        }
        if (description.length() > 140) {
            throw new RoleValidationException("Description length can not exceed 140 characters");
        }

    }

    //Behöver inte validera isManger boolean för den kan bara var true eller false


}
