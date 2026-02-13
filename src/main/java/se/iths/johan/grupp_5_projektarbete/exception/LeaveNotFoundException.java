package se.iths.johan.grupp_5_projektarbete.exception;

public class LeaveNotFoundException extends RuntimeException {
    public LeaveNotFoundException(Long id) {
        super("Leave with id " + id + " was not found");
    }
}
