package code.prashant.AppointmentBooking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(classes = {AppointmentBookingApplication.class})
class AppointmentBookingApplicationTests {

	@Autowired
	ApplicationContext applicationContext;
	@Test
	void contextLoads() {
		Assertions.assertNotNull(applicationContext);
	}

}
