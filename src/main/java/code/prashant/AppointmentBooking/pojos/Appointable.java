package code.prashant.AppointmentBooking.pojos;

import code.prashant.AppointmentBooking.exceptions.AppointmentValidationException;

public class Appointable {
    private long id;
    private String name;
    private String address;
    private String email;

    private String daysAvailable;

    private String timesAvailable;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(String daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public String getTimesAvailable() {
        return timesAvailable;
    }

    public void setTimesAvailable(String timesAvailable) {
        this.timesAvailable = timesAvailable;
    }

    public void validateOnCreate(){
        if(name==null||name.length()==0){
            throw new AppointmentValidationException("Name cannot be null");
        }
        if(id!=0){
            throw new AppointmentValidationException("You cannot decide id");
        }
    }

    public void validateOnUpdate(){
        if(name==null||name.length()==0){
            throw new AppointmentValidationException("Name cannot be null");
        }
        if(id==0){
            throw new AppointmentValidationException("On update, id must belong to existing appointable");
        }
    }
}