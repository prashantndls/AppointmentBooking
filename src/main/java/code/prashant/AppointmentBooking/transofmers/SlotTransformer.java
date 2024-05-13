package code.prashant.AppointmentBooking.transofmers;

import code.prashant.AppointmentBooking.entities.SlotEntity;
import code.prashant.AppointmentBooking.pojos.Slot;

public interface SlotTransformer {
    //Builder design pattern to be used
    Slot entityToSlotPojo(SlotEntity slotEntity);
    SlotEntity pojoToSlotEntity(Slot slot);
}
