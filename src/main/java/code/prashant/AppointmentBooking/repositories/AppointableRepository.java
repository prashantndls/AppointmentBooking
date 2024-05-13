package code.prashant.AppointmentBooking.repositories;

import code.prashant.AppointmentBooking.entities.AppointableEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AppointableRepository extends JpaRepository<AppointableEntity, Long> {
}
