package code.prashant.AppointmentBooking.controllers;

import code.prashant.AppointmentBooking.services.AppointableService;
import code.prashant.AppointmentBooking.pojos.Appointable;
import code.prashant.AppointmentBooking.services.SlotService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/appointables")
@AllArgsConstructor
public class AppointablesController {

    private final AppointableService appointableService;
    private final SlotService slotService;
    @GetMapping("/")
    public List<Appointable> getAppointables(@RequestParam(required = true) int pageNum, @RequestParam(required = true) int pageSize){
        //Temporary workaround to create slots at will
        slotService.createSlotsCron();
        return appointableService.getAppointables(pageNum, pageSize);
    }

    @PostMapping("/")
    public Appointable createAppointable(@RequestBody Appointable appointable){
        return appointableService.createAppointable(appointable);
    }

    @PutMapping("/")
    public Appointable updateAppointable(@RequestBody Appointable appointable){
        return appointableService.updateAppointable(appointable);
    }
}
