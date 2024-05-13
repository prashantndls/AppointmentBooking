package code.prashant.AppointmentBooking.services.impls;

import code.prashant.AppointmentBooking.entities.AppointableEntity;
import code.prashant.AppointmentBooking.repositories.AppointableRepository;
import code.prashant.AppointmentBooking.services.AppointableService;
import code.prashant.AppointmentBooking.pojos.Appointable;
import code.prashant.AppointmentBooking.transofmers.AppointableTransformer;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class AppointableServiceImpl implements AppointableService {

    private final AppointableRepository appointableRepository;
    private final AppointableTransformer appointableTransformer;
    @Override
    public List<Appointable> getAppointables(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        Page<AppointableEntity> appointablePage =  appointableRepository.findAll(pageable);
        return appointablePage.stream().map((appointableEntity -> appointableTransformer.appointableEntityToPojo(appointableEntity))).collect(Collectors.toList());
    }

    @Override
    public Appointable createAppointable(Appointable appointable) {
        appointable.validateOnCreate();
        AppointableEntity appointableEntity = appointableTransformer.pojoToAppointableEntity(appointable);
        appointableEntity = appointableRepository.save(appointableEntity);
        return appointableTransformer.appointableEntityToPojo(appointableEntity);
    }

    @Override
    public Appointable updateAppointable(Appointable appointable) {
        appointable.validateOnUpdate();
        AppointableEntity appointableEntity = appointableTransformer.pojoToAppointableEntity(appointable);
        appointableEntity = appointableRepository.save(appointableEntity);
        return appointableTransformer.appointableEntityToPojo(appointableEntity);
    }
}
