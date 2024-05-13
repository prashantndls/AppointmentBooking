package code.prashant.AppointmentBooking.transofmers;

import code.prashant.AppointmentBooking.entities.AppointableEntity;
import code.prashant.AppointmentBooking.pojos.Appointable;

public interface AppointableTransformer {
    Appointable appointableEntityToPojo(AppointableEntity appointableEntity);

    AppointableEntity pojoToAppointableEntity(Appointable appointable);
}
