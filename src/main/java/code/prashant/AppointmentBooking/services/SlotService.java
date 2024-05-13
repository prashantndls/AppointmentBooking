package code.prashant.AppointmentBooking.services;

import code.prashant.AppointmentBooking.pojos.Slot;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public interface SlotService {
    List<Slot> getAvailableSlots(Long appointable_id);

    void createSlotsCron();

    Slot bookAppintment(Slot slot, String email);
}
