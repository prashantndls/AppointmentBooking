package code.prashant.AppointmentBooking.transofmers.impls;


import code.prashant.AppointmentBooking.entities.SlotEntity;
import code.prashant.AppointmentBooking.pojos.Slot;
import code.prashant.AppointmentBooking.transofmers.AppointableTransformer;
import code.prashant.AppointmentBooking.transofmers.SlotTransformer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SlotTransformerImpl implements SlotTransformer {

    private final AppointableTransformer appointableTransformer;
    //Builder design pattern to be used
    @Override
    public Slot entityToSlotPojo(SlotEntity slotEntity){
        Slot slot = new Slot();
        slot.setId(slotEntity.getId());
        slot.setAppointable(appointableTransformer.appointableEntityToPojo(slotEntity.getAppointableEntity()));
        slot.setFrom(slotEntity.getFrom());
        slot.setTo(slotEntity.getTo());
        slot.setBookedTo(slotEntity.getBookedTo());
        return slot;
    }

    @Override
    public SlotEntity pojoToSlotEntity(Slot slot) {
        SlotEntity slotEntity = new SlotEntity();
        slotEntity.setId(slot.getId());
        slotEntity.setAppointableEntity(appointableTransformer.pojoToAppointableEntity(slot.getAppointable()));
        slotEntity.setFrom(slot.getFrom());
        slotEntity.setTo(slot.getTo());
        slotEntity.setBookedTo(slot.getBookedTo());
        return slotEntity;
    }
}
