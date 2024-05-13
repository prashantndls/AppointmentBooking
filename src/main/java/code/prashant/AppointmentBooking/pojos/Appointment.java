package code.prashant.AppointmentBooking.pojos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Appointment {
    private Slot slot;
    private String email;
}
