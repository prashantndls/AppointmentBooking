package code.prashant.AppointmentBooking.exceptions;

public class AppointmentValidationException extends RuntimeException {
    public AppointmentValidationException(String description){
        super(description);
    }
}
