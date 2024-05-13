package code.prashant.AppointmentBooking.services;

import code.prashant.AppointmentBooking.pojos.Appointable;

import java.util.List;

public interface AppointableService {
    List<Appointable> getAppointables(int page_num, int page_size);
    Appointable createAppointable(Appointable appointable);
    Appointable updateAppointable(Appointable appointable);

}
