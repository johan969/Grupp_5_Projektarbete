package se.iths.johan.grupp_5_projektarbete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.johan.grupp_5_projektarbete.model.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
}
