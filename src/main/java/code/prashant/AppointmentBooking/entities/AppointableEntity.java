package code.prashant.AppointmentBooking.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@Table(name="appointables")
public class AppointableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="_id")
    private Long id;

    @Column(name="_name")
    private String name;

    @Column(name="_address")
    private String address;

    @Column(name="_email")
    private String email;

    @Column(name="_days_available")
    private String daysAvailable;

    @Column(name="_times_available")
    private String timesAvailable;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updatedAt;

}
