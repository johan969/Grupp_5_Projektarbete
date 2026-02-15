package se.iths.johan.grupp_5_projektarbete.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.johan.grupp_5_projektarbete.exception.EmployeeNotFoundException;
import se.iths.johan.grupp_5_projektarbete.exception.EmployeeValidationException;
import se.iths.johan.grupp_5_projektarbete.model.Employee;
import se.iths.johan.grupp_5_projektarbete.repository.EmployeeRepository;
import se.iths.johan.grupp_5_projektarbete.validator.EmployeeValidator;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    EmployeeRepository employeeRepository;
    @Mock
    EmployeeValidator employeeValidator;

    @InjectMocks
    EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setName("Asli");
        employee.setEmployeeType("Employee");
        employee.setEmploymentType("Permanent");
        employee.setEmploymentPercentage(100);
        employee.setMonthlySalary(30000);
    }

    @Test
    @DisplayName("Test för att hämta alla anställda")
    void getAllEmployeesTest() {
        List<Employee> employees = List.of(employee);
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Asli", result.get(0).getName());
        verify(employeeRepository).findAll();
    }

    @Test
    @DisplayName("Test för att hämta en anställd baserat på id")
    void getEmployeeByIdTest() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Employee result = employeeService.getEmployeeById(1L);
        assertNotNull(result);
        assertEquals("Asli", result.getName());
        verify(employeeRepository).findById(1L);
    }

    @Test
    @DisplayName("Skapa/validera employee")
    void createEmployeeTest() {
        //returnera samma employee när den sparar
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeService.createEmployee(employee);

        //verifierar att validatorn testar korrekt
        verify(employeeValidator).validateEmployee(employee.getName());
        verify(employeeValidator).validateSalary(employee.getMonthlySalary());
        verify(employeeValidator).validateEmploymentType(employee.getEmploymentType());
        verify(employeeValidator).validateEmploymentPercentage(employee.getEmploymentPercentage());
        verify(employeeValidator).validateEmployeeType(employee.getEmployeeType());

        //verifierar sparning efter validering
        verify(employeeRepository).save(employee);
        assertEquals(employee, result);
    }

    //uppdatera/validera en employee
    @Test
    void updateEmployeeTest() {
        Employee updated = new Employee();
        updated.setName("Sandra");
        updated.setEmployeeType("Employee");
        updated.setEmploymentType("Permanent");
        updated.setEmploymentPercentage(100);
        updated.setMonthlySalary(30000);

        when(employeeRepository.findById(0L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(employee)).thenReturn(employee);
        Employee result = employeeService.updateEmployee(0L, updated);

        verify(employeeRepository).findById(0L);

        verify(employeeValidator).validateEmployee(updated.getName());
        verify(employeeValidator).validateSalary(updated.getMonthlySalary());
        verify(employeeValidator).validateEmploymentType(updated.getEmploymentType());
        verify(employeeValidator).validateEmploymentPercentage(updated.getEmploymentPercentage());
        verify(employeeValidator).validateEmployeeType(updated.getEmployeeType());

        verify(employeeRepository).save(employee);
        assertEquals("Sandra", result.getName());
    }

    @Test
    void deleteEmployeeTest() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(1L);
        verify(employeeRepository).findById(1L);
    }

    //----------------- testar exceptions ------------------//
    @Test
    @DisplayName("Exception när man hämtar en anställd")
    void getEmployeeByIdExceptionTest() {
        //säger att det inte finns någon employee med id 2
        when(employeeRepository.findById(2L)).thenReturn(Optional.empty());
        //kastar då exception
        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.getEmployeeById(2L));
    }

    //validatorn ska kasta exception
    @Test
    void createEmployeeExceptionTest() {
        doThrow(new EmployeeValidationException("Employee can't be created"))
                .when(employeeValidator).validateEmployee(employee.getName());

        assertThrows(EmployeeValidationException.class,
                () -> employeeService.createEmployee(employee));
    }

    @Test
    void updateEmployeeExceptionTest() {
        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        doThrow(new EmployeeValidationException("Employee can't be updated"))
                .when(employeeValidator).validateEmployee(employee.getName());

        assertThrows(EmployeeValidationException.class,
                () -> employeeService.updateEmployee(1L, employee));
    }

    @Test
    void deleteEmployeeExceptionTest() {
        when(employeeRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.deleteEmployee(2L));
    }

}
