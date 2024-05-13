package code.prashant.AppointmentBooking.exceptions;

public class SlotBookingException extends RuntimeException {
    public SlotBookingException(String description){
        super(description);
    }
}
