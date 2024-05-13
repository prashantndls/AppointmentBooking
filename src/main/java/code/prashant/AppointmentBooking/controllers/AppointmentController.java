package code.prashant.AppointmentBooking.controllers;

import code.prashant.AppointmentBooking.pojos.Appointment;
import code.prashant.AppointmentBooking.pojos.Slot;
import code.prashant.AppointmentBooking.services.SlotService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentController {

    private final SlotService slotService;

    @GetMapping("/")
    public List<Slot> getAvailableSlots(@RequestParam(required = true) long appointable_id){
        return slotService.getAvailableSlots(appointable_id);
    }
    @PostMapping("/")
    public Slot createAppointment(@RequestBody Appointment appointment){
        return slotService.bookAppintment(appointment.getSlot(), appointment.getEmail());
    }
}
