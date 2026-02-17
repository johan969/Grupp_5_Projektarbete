package se.iths.johan.grupp_5_projektarbete.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.iths.johan.grupp_5_projektarbete.exception.RoleValidationException;
import se.iths.johan.grupp_5_projektarbete.model.Role;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoleValidatorTest {

    private RoleValidator roleValidator;

    private Role roleSuccess;
    private Role roleNull;
    private Role roleLength;

    @BeforeEach
    void setUp() {


        roleValidator = new RoleValidator();
        roleSuccess = new Role();
        roleNull = null;
        roleLength = new Role();

        roleSuccess.setTitle("RoleTitel");
        roleSuccess.setLevel("RoleLevel");
        roleSuccess.setDescription("RoleDescription");


        roleLength.setTitle("a".repeat(31));
        roleLength.setLevel("a".repeat(31));
        roleLength.setDescription("a".repeat(141));

    }

    @Test
    void validate_Role_Test() {
        //act + assert
        assertDoesNotThrow(() -> roleValidator.validate(roleSuccess));
    }

    @Test
    void validate_Role_NotNull_Test() {
        //act + assert
        assertThrows(RoleValidationException.class, () -> roleValidator.validate(roleNull));
    }

    @Test
    void validate_Role_Length_Test() {
        //act + assert
        assertThrows(RoleValidationException.class, () -> roleValidator.validate(roleLength));
    }


    @Test
    void validate_Role_Titel_Test() {
        //act + assert
        assertDoesNotThrow(() -> roleValidator.validateTitle(roleSuccess.getTitle()));
    }

    @Test
    void validate_Role_Titel_NotNull_Test() {
        //act + assert
        assertThrows(RoleValidationException.class, () -> roleValidator.validateTitle(null));
    }

    @Test
    void validate_Role_Titel_Length_Test() {
        //act + assert
        assertThrows(RoleValidationException.class, () -> roleValidator.validateTitle(roleLength.getTitle()));

    }


    @Test
    void validate_Role_Level_Test() {
        //act + assert
        assertDoesNotThrow(() -> roleValidator.validateLevel(roleSuccess.getLevel()));
    }

    @Test
    void validate_Role_Level_NotNull_Test() {
        //act + assert
        assertThrows(RoleValidationException.class, () -> roleValidator.validateLevel(null));
    }

    @Test
    void validate_Role_Level_Length_Test() {
        //act + assert
        assertThrows(RoleValidationException.class, () -> roleValidator.validateLevel(roleLength.getLevel()));

    }

    @Test
    void validate_Role_Description_Test() {
        //act + assert
        assertDoesNotThrow(() -> roleValidator.validateDescription(roleSuccess.getDescription()));
    }

    @Test
    void validate_Role_Description_NotNull_Test() {
        //act + assert
        assertThrows(RoleValidationException.class, () -> roleValidator.validateDescription(null));
    }

    @Test
    void validate_Role_Description_Length_Test() {
        //act + assert
        assertThrows(RoleValidationException.class, () -> roleValidator.validateDescription(roleLength.getDescription()));

    }

}
