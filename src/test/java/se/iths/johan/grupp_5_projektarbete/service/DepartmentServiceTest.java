package se.iths.johan.grupp_5_projektarbete.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.johan.grupp_5_projektarbete.exception.DepartmentNotFoundException;
import se.iths.johan.grupp_5_projektarbete.model.Department;
import se.iths.johan.grupp_5_projektarbete.repository.DepartmentRepository;
import se.iths.johan.grupp_5_projektarbete.validator.DepartmentValidator;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    // mockar = fejk objekt
    @Mock
    DepartmentValidator departmentValidator;
    @Mock
    DepartmentRepository departmentRepository;

    // riktig service som mockar injiceras i
    @InjectMocks
    DepartmentService departmentService;

    // riktig department
    Department department;

    // arrange
    @BeforeEach
    void setUp() {
        department = new Department();
        department.setDepartmentName("IT");
        department.setCostCenter(12345);
        department.setLocation("Stockholm");
        department.setNumberOfEmployees(30);
    }


    @Test
    @DisplayName("Ska testa att en lista med alla avdelningar hämtas")
    void testGetAllDepartmentsWithSuccess() {
        List<Department> departments = List.of(department);

        // när metoden körs ska istället listan departments returneras
        when(departmentRepository.findAll()).thenReturn(departments);

        // act - service-metoden testas
        List<Department> result = departmentService.getAllDepartments();

        // assert
        assertEquals(1, result.size());
        // kontrollerar att findAll anropats 1 gång
        verify(departmentRepository, Mockito.times(1)).findAll();

    }

    @Test
    @DisplayName("Ska testa att avdelning valideras och skapas/sparas")
    void testCreateDepartmentWithSuccess() {
        // när metoden körs ska istället department-objektet returneras
        when(departmentRepository.save(department)).thenReturn(department);

        // act - service-metoden testas
        Department result = departmentService.createDepartment(department);

        // verifierar validatorn (att metoderna anropas), kollar så att service använder metoderna
        verify(departmentValidator, Mockito.times(1)).validateDepartmentName("IT");
        verify(departmentValidator, Mockito.times(1)).validateCostCenter(12345);
        verify(departmentValidator, Mockito.times(1)).validateLocation("Stockholm");
        verify(departmentValidator, Mockito.times(1)).validateNumberOfEmployees(30);

        // kontrollerar att save anropats 1 gång
        verify(departmentRepository, Mockito.times(1)).save(department);

        // assert - kontrollerar resultat
        assertEquals(department, result);
    }

    @Test
    @DisplayName("Ska testa att en avdelning hämtas")
    void testGetDepartmentWithSuccess() {

        // när metoden körs ska istället department-objektet returneras
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        // act - service-metoden testas
        Department result = departmentService.getDepartment(1L);

        // assert - kontrollerar resultat
        assertEquals(department, result);

    }

    @Test
    @DisplayName("Ska testa att en avdelning uppdateras")
    void testUpdateDepartmentWithSuccess() {

        // arrange - objekt med uppdaterade värden
        Department updated = new Department();
        updated.setDepartmentName("Finance");
        updated.setCostCenter(67890);
        updated.setLocation("Malmö");
        updated.setNumberOfEmployees(20);

        // när metoderna körs ska department-objektet returneras
        when(departmentRepository.findById(1L))
                .thenReturn(Optional.of(department));

        when(departmentRepository.save(department))
                .thenReturn(department);

        // act - service-metoden testas
        Department result = departmentService.updateDepartment(1L, updated);

        // assert - kollar två olika fält, om de har uppdaterats och att t ex metoden
        // department.setDepartmentName(updated.getDepartmentName()); fungerade
        assertEquals("Finance", result.getDepartmentName());
        assertEquals(67890, result.getCostCenter());

        verify(departmentValidator).validateDepartmentName("Finance");
        verify(departmentValidator).validateCostCenter(67890);
        verify(departmentValidator).validateLocation("Malmö");
        verify(departmentValidator).validateNumberOfEmployees(20);

        // kontrollerar att save anropats 1 gång
        verify(departmentRepository, Mockito.times(1)).save(department);
    }

    @Test
    @DisplayName("Ska testa att en avdelning tas bort")
    void testDeleteDepartmentWithSuccess() {

        // när metoden körs ska istället department-objektet returneras
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        // act - service-metoden testas
        departmentService.deleteDepartment(1L);

        // kontrollerar att deleteById anropats 1 gång
        verify(departmentRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Ska testa att korrekt exception kastas när id saknas vid hämtning")
    void testGetDepartmentWithNoId() {

        // när metoden körs ska istället inget objekt returneras
        when(departmentRepository.findById(1L)).thenReturn(Optional.empty());

        // act och assert - att rätt exception kastas
        assertThrows(DepartmentNotFoundException.class, () -> departmentService.getDepartment(1L)
        );
    }


    @Test
    @DisplayName("Ska testa att korrekt exception kastas när id saknas vid uppdatering")
    void testUpdateWithNoId() {

        // när metoden körs ska istället inget objekt returneras
        when(departmentRepository.findById(1L)).thenReturn(Optional.empty());

        // skapar ett department-objekt så att service-metoden kan köras
        Department updated = new Department();

        // act och assert - att rätt exception kastas
        assertThrows(DepartmentNotFoundException.class, () -> departmentService.updateDepartment(1L, updated)
        );
    }

    @Test
    @DisplayName("Ska testa att korrekt exception kastas när id saknas vid borttagning")
    void testDeleteWithNoId() {

        // när metoden körs ska istället inget objekt returneras
        when(departmentRepository.findById(1L)).thenReturn(Optional.empty());

        // act och assert
        assertThrows(DepartmentNotFoundException.class, () -> departmentService.deleteDepartment(1L));
    }
}