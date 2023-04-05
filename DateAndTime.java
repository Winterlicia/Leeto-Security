//To get the date and time in java (using the java.time package).
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class DateAndTime {

    public String getDate_Time(String password) throws Exception {
        LocalDateTime currentDate_Time = LocalDateTime.now();
        //Desired Date/Time Format:
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy h:mm a");            //Format the current date and time
        String formattedDate = currentDate_Time.format(formatter);
        return formattedDate;
    }

}