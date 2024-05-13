package code.prashant.AppointmentBooking.exceptions;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler({AppointmentValidationException.class})
    public ResponseEntity<String>  appointmentValidationException(AppointmentValidationException ex, HttpServletRequest request){
        return new ResponseEntity<>(ex.toString(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(Exception ex, HttpServletRequest req){
        return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<String> handleDIException(DataIntegrityViolationException ex, HttpServletRequest req){
        return new ResponseEntity<>("Please check the data you are trying", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
