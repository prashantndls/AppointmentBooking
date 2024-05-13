package code.prashant.AppointmentBooking.services.impls;

import code.prashant.AppointmentBooking.entities.SlotEntity;
import code.prashant.AppointmentBooking.exceptions.SlotBookingException;
import code.prashant.AppointmentBooking.pojos.Appointable;
import code.prashant.AppointmentBooking.pojos.Slot;
import code.prashant.AppointmentBooking.repositories.SlotRepository;
import code.prashant.AppointmentBooking.services.AppointableService;
import code.prashant.AppointmentBooking.services.SlotService;
import code.prashant.AppointmentBooking.transofmers.AppointableTransformer;
import code.prashant.AppointmentBooking.transofmers.SlotTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SlotServiceImpl implements SlotService {

    @Autowired
    private  SlotRepository slotRepository;

    @Autowired
    private SlotTransformer slotTransformer;

    @Autowired
    private AppointableService appointableService;

    @Autowired
    private AppointableTransformer appointableTransformer;


    private ConcurrentHashMap<Long, Long> locks = new ConcurrentHashMap<>();

    @Override
    public List<Slot> getAvailableSlots(Long appointable_id) {
        Collection<SlotEntity> slotsPage =  slotRepository.findSlotsByAppointableId(appointable_id);
        return slotsPage.stream().map((slotEntity -> slotTransformer.entityToSlotPojo(slotEntity))).collect(Collectors.toList());
    }
    @Scheduled(cron = "0 0 0 * * ?")
    @Override
    public void createSlotsCron(){

        log.info("Running cronjob");
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        String dayOfWeekStr = dayOfWeek.toString().toLowerCase().substring(0,3);
        String dayOfWeekInDb = dayOfWeekStr.substring(0,1).toUpperCase() + dayOfWeekStr.substring(1);
        int pageNum=0;
        int pageSize=50;

        List<Appointable> appointableList = appointableService.getAppointables(pageNum++, pageSize);
        while(appointableList.size()!=0){
            appointableList.stream().forEach((appointable) -> {
                String daysAvailable = appointable.getDaysAvailable();
                List<String> currentDayTimes = Arrays.stream(daysAvailable.split(",")).filter((dayInfo)-> {return dayInfo.contains(dayOfWeekInDb);}).map((dayInfo)->{return dayInfo.substring(4).strip();}).collect(Collectors.toList());
                currentDayTimes.stream().forEach((timing)->{
                    List<SlotEntity> slotEntities = new ArrayList<>();
                    List<String> timings = Arrays.stream(timing.split("-")).toList();
                    String[] from = timings.get(0).split(":");
                    String[] to = timings.get(1).split(":");
                    int fromH = Integer.parseInt(from[0]);
                    int fromM = Integer.parseInt(from[1]);
                    int toH = Integer.parseInt(to[0]);
                    int toM = Integer.parseInt(to[1]);

                    if(toM==0){
                        toM=45;
                        toH=toH-1;
                    }
                    while(!dayLimitReached(fromH, fromM, toH, toM)){
                        LocalDateTime localDateTimeFrom = LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), fromH, fromM);
                        int slotToM = fromM+15;
                        int slotToH = fromH;
                        if(slotToM > 59){
                            slotToM = slotToM%60;
                            slotToH = (slotToH+1)%24;
                        }
                        LocalDateTime localDateTimeTo = LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), slotToH, slotToM);

                        SlotEntity slotEntity = new SlotEntity();
                        slotEntity.setFrom(localDateToDate(localDateTimeFrom));
                        slotEntity.setTo(localDateToDate(localDateTimeTo));
                        slotEntity.setAppointableEntity(appointableTransformer.pojoToAppointableEntity(appointable));
                        slotEntity.setBookedTo(null);
                        slotEntities.add(slotEntity);
                        fromH = slotToH;
                        fromM = slotToM;
                    }

                    if(slotEntities.size()>0)
                        slotRepository.saveAll(slotEntities);
                });
            });
            appointableList = appointableService.getAppointables(pageNum++, pageSize);
        }

    }

    @Override
    public Slot bookAppintment(Slot slot, String email) {
        Optional<SlotEntity> slotFromDb = slotRepository.findById(slot.getId());
        if(slotFromDb.isPresent())
            slot = slotTransformer.entityToSlotPojo(slotFromDb.get());
        if(slot.getBookedTo()!=null){
            throw new SlotBookingException("This Slot is already Booked");
        }
        slot.setBookedTo(email);
        SlotEntity slotEntity = null;
        locks.putIfAbsent(slot.getId(), slot.getId());
        synchronized (locks.get(slot.getId())) {
            slotEntity = slotRepository.save(slotTransformer.pojoToSlotEntity(slot));
            locks.remove(slot.getId());
        }
        if(slotEntity!=null){
            return slotTransformer.entityToSlotPojo(slotEntity);
        }
        throw new SlotBookingException("Slot could not be booked");
    }

    private boolean dayLimitReached(int currentH, int currentM, int toH, int toM){

        if(currentH < toH){
            return false;
        }
        if(currentH == toH && currentM <toM){
            return false;
        }
        return true;
    }

    private Date localDateToDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
