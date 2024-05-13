package code.prashant.AppointmentBooking.transofmers.impls;


import code.prashant.AppointmentBooking.entities.AppointableEntity;
import code.prashant.AppointmentBooking.entities.SlotEntity;
import code.prashant.AppointmentBooking.pojos.Appointable;
import code.prashant.AppointmentBooking.pojos.Slot;
import code.prashant.AppointmentBooking.transofmers.AppointableTransformer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppointableTransformerImpl implements AppointableTransformer {

    //Builder design pattern to be used

    @Override
    public Appointable appointableEntityToPojo(AppointableEntity appointableEntity){
        Appointable appointable = new Appointable();
        appointable.setId(appointableEntity.getId());
        appointable.setName(appointableEntity.getName());
        appointable.setAddress(appointableEntity.getAddress());
        appointable.setEmail(appointableEntity.getEmail());
        appointable.setDaysAvailable(appointableEntity.getDaysAvailable());
        appointable.setTimesAvailable(appointableEntity.getTimesAvailable());
        return appointable;
    }

    //Builder design pattern to be used

    @Override
    public AppointableEntity pojoToAppointableEntity(Appointable appointable){
        AppointableEntity appointableEntity = new AppointableEntity();
        appointableEntity.setId(appointable.getId());
        appointableEntity.setName(appointable.getName());
        appointableEntity.setAddress(appointable.getAddress());
        appointableEntity.setEmail(appointable.getEmail());
        appointableEntity.setDaysAvailable(appointable.getDaysAvailable());
        appointableEntity.setTimesAvailable(appointable.getTimesAvailable());
        return appointableEntity;
    }
}
