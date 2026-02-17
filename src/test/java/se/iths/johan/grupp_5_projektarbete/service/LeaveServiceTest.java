package se.iths.johan.grupp_5_projektarbete.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.johan.grupp_5_projektarbete.exception.LeaveNotFoundException;
import se.iths.johan.grupp_5_projektarbete.exception.LeaveValidationException;
import se.iths.johan.grupp_5_projektarbete.model.Leave;
import se.iths.johan.grupp_5_projektarbete.repository.LeaveRepository;
import se.iths.johan.grupp_5_projektarbete.validator.LeaveValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LeaveServiceTest {

    @Mock
    LeaveRepository leaveRepository;

    @Mock
    LeaveValidator leaveValidator;

    @InjectMocks
    LeaveService leaveService;

    Leave leave;

    @BeforeEach
    void setUp() {
        leave = new Leave();
        leave.setEmployeeName("Sara");
        leave.setStartDate(LocalDate.of(2026, 2, 1));
        leave.setEndDate(LocalDate.of(2026, 2, 10));
        leave.setApproved(false);
    }

    @Test
    @DisplayName("Ska testa att alla leave hämtas")
    void testGetAllWithSuccess() {
        when(leaveRepository.findAll()).thenReturn(List.of(leave));

        List<Leave> result = leaveService.getAll();

        assertEquals(1, result.size());
        verify(leaveRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Ska testa att en leave hämtas via id")
    void testGetByIdWithSuccess() {
        when(leaveRepository.findById(1L)).thenReturn(Optional.of(leave));

        Leave result = leaveService.getById(1L);

        assertEquals(leave, result);
        verify(leaveRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    @DisplayName("Ska testa att exception kastas när leave inte finns vid hämtning")
    void testGetByIdWithNoId() {
        when(leaveRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(LeaveNotFoundException.class, () -> leaveService.getById(1L));
        verify(leaveRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    @DisplayName("Ska testa att leave valideras och skapas/sparas")
    void testCreateWithSuccess() {
        when(leaveRepository.save(leave)).thenReturn(leave);

        Leave result = leaveService.create(leave);

        verify(leaveValidator, Mockito.times(1)).validateEmployeeName("Sara");
        verify(leaveValidator, Mockito.times(1)).validateStartDate(leave.getStartDate());
        verify(leaveValidator, Mockito.times(1)).validateEndDate(leave.getStartDate(), leave.getEndDate());
        verify(leaveRepository, Mockito.times(1)).save(leave);

        assertEquals(leave, result);
    }

    @Test
    @DisplayName("Ska testa att validation-exception kastas vid create")
    void testCreateWhenValidationExceptionThrows() {
        doThrow(new LeaveValidationException("employeeName must not be blank"))
                .when(leaveValidator).validateEmployeeName(leave.getEmployeeName());

        assertThrows(LeaveValidationException.class, () -> leaveService.create(leave));

        verify(leaveRepository, never()).save(any());
    }

    @Test
    @DisplayName("Ska testa att en leave uppdateras")
    void testUpdateWithSuccess() {
        Leave updated = new Leave();
        updated.setEmployeeName("Johan");
        updated.setStartDate(LocalDate.of(2026, 3, 1));
        updated.setEndDate(LocalDate.of(2026, 3, 5));
        updated.setApproved(true);

        when(leaveRepository.findById(1L)).thenReturn(Optional.of(leave));
        when(leaveRepository.save(leave)).thenReturn(leave);

        Leave result = leaveService.update(1L, updated);

        assertEquals("Johan", result.getEmployeeName());
        assertEquals(LocalDate.of(2026, 3, 1), result.getStartDate());
        assertEquals(LocalDate.of(2026, 3, 5), result.getEndDate());
        assertTrue(result.isApproved());

        verify(leaveValidator).validateEmployeeName("Johan");
        verify(leaveValidator).validateStartDate(updated.getStartDate());
        verify(leaveValidator).validateEndDate(updated.getStartDate(), updated.getEndDate());
        verify(leaveRepository, Mockito.times(1)).save(leave);
    }

    @Test
    @DisplayName("Ska testa att exception kastas när leave inte finns vid uppdatering")
    void testUpdateWithNoId() {
        when(leaveRepository.findById(1L)).thenReturn(Optional.empty());

        Leave updated = new Leave();

        assertThrows(LeaveNotFoundException.class, () -> leaveService.update(1L, updated));
        verify(leaveRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    @DisplayName("Ska testa att validation-exception kastas vid update")
    void testUpdateWhenValidationExceptionThrows() {
        when(leaveRepository.findById(1L)).thenReturn(Optional.of(leave));

        doThrow(new LeaveValidationException("employeeName must not be blank"))
                .when(leaveValidator).validateEmployeeName(leave.getEmployeeName());

        assertThrows(LeaveValidationException.class, () -> leaveService.update(1L, leave));

        verify(leaveRepository, never()).save(any());
    }

    @Test
    @DisplayName("Ska testa att en leave tas bort")
    void testDeleteWithSuccess() {
        when(leaveRepository.findById(1L)).thenReturn(Optional.of(leave));

        leaveService.delete(1L);

        verify(leaveRepository, Mockito.times(1)).delete(leave);
    }

    @Test
    @DisplayName("Ska testa att exception kastas när leave inte finns vid borttagning")
    void testDeleteWithNoId() {
        when(leaveRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(LeaveNotFoundException.class, () -> leaveService.delete(1L));
        verify(leaveRepository, Mockito.times(1)).findById(1L);
    }
}
