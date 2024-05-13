package code.prashant.AppointmentBooking.repositories;

import code.prashant.AppointmentBooking.entities.SlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SlotRepository extends JpaRepository<SlotEntity, Long> {

    @Query(value = "select * from slots where appointable_id=?1 and booked_to is null ORDER BY _id", nativeQuery = true)
    public Collection<SlotEntity> findSlotsByAppointableId(Long appointable_id);
}
