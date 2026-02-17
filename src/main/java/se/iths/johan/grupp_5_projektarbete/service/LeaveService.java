package se.iths.johan.grupp_5_projektarbete.service;

import org.springframework.stereotype.Service;
import se.iths.johan.grupp_5_projektarbete.exception.LeaveNotFoundException;
import se.iths.johan.grupp_5_projektarbete.model.Leave;
import se.iths.johan.grupp_5_projektarbete.repository.LeaveRepository;
import se.iths.johan.grupp_5_projektarbete.validator.LeaveValidator;

import java.util.List;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final LeaveValidator leaveValidator;

    public LeaveService(LeaveRepository leaveRepository, LeaveValidator leaveValidator) {
        this.leaveRepository = leaveRepository;
        this.leaveValidator = leaveValidator;
    }

    public List<Leave> getAll() {
        return leaveRepository.findAll();
    }

    public Leave getById(Long id) {
        return leaveRepository.findById(id)
                .orElseThrow(() -> new LeaveNotFoundException("Leave with id " + id + " was not found"));
    }

    public Leave create(Leave leave) {
        validateLeave(leave);
        return leaveRepository.save(leave);
    }

    public Leave update(Long id, Leave updated) {
        Leave existing = leaveRepository.findById(id)
                .orElseThrow(() -> new LeaveNotFoundException("Leave with id " + id + " was not found"));

        validateLeave(updated);

        existing.setEmployeeName(updated.getEmployeeName());
        existing.setStartDate(updated.getStartDate());
        existing.setEndDate(updated.getEndDate());
        existing.setApproved(updated.isApproved());

        return leaveRepository.save(existing);
    }

    public void delete(Long id) {
        Leave existing = leaveRepository.findById(id)
                .orElseThrow(() -> new LeaveNotFoundException("Leave with id " + id + " was not found"));

        leaveRepository.delete(existing);
    }

    private void validateLeave(Leave leave) {
        leaveValidator.validateEmployeeName(leave.getEmployeeName());
        leaveValidator.validateStartDate(leave.getStartDate());
        leaveValidator.validateEndDate(leave.getStartDate(), leave.getEndDate());
    }
}
