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

    // GET (alla)
    public List<Leave> getAll() {
        return leaveRepository.findAll();
    }

    // GET (en)
    public Leave getById(Long id) {
        return leaveRepository.findById(id)
                .orElseThrow(() -> new LeaveNotFoundException(id));
    }

    // POST (skapa)
    public Leave create(Leave leave) {
        validate(leave);
        return leaveRepository.save(leave);
    }

    // PUT (uppdatera)
    public Leave update(Long id, Leave updated) {
        Leave existing = leaveRepository.findById(id)
                .orElseThrow(() -> new LeaveNotFoundException(id));

        validate(updated);

        existing.setEmployeeName(updated.getEmployeeName());
        existing.setStartDate(updated.getStartDate());
        existing.setEndDate(updated.getEndDate());
        existing.setApproved(updated.isApproved());

        return leaveRepository.save(existing);
    }

    // DELETE (ta bort)
    public void delete(Long id) {
        Leave existing = leaveRepository.findById(id)
                .orElseThrow(() -> new LeaveNotFoundException(id));

        leaveRepository.delete(existing);
    }

    private void validate(Leave leave) {
        leaveValidator.validateEmployeeName(leave.getEmployeeName());
        leaveValidator.validateStartDate(leave.getStartDate());
        leaveValidator.validateEndDate(leave.getStartDate(), leave.getEndDate());
        leaveValidator.validateApproved(leave.isApproved());
    }
}
