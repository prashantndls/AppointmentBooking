package code.prashant.AppointmentBooking.pojos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
public class Slot {
    private Long id;
    private Appointable appointable;
    private Date from;
    private Date to;

    private String bookedTo;
}
