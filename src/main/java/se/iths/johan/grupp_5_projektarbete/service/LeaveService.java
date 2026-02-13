package se.iths.johan.grupp_5_projektarbete.service;

import org.springframework.stereotype.Service;
import se.iths.johan.grupp_5_projektarbete.exception.LeaveNotFoundException;
import se.iths.johan.grupp_5_projektarbete.model.Leave;
import se.iths.johan.grupp_5_projektarbete.repository.LeaveRepository;

import java.util.List;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;

    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
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
        return leaveRepository.save(leave);
    }

    // PUT (uppdatera)
    public Leave update(Long id, Leave updated) {
        Leave existing = leaveRepository.findById(id)
                .orElseThrow(() -> new LeaveNotFoundException(id));

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
}
