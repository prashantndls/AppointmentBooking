package code.prashant.AppointmentBooking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@Table(name="slots")
public class SlotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="appointable_id")
    private AppointableEntity appointableEntity;

    @Column(name="from_time")
    private Date from;

    @Column(name="to_time")
    private Date to;

    @Column(name="booked_to")
    private String bookedTo;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updatedAt;
}
