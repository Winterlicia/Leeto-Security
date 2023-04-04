//To get the date and time in java (using the java.time package).
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class DateAndTime {
    
    public DateAndTime(String inputPassword) {
        String password = inputPassword;
    }

    public static void getDate_Time(String password) throws Exception {
        LocalDateTime currentDate_Time = LocalDateTime.now();
        //Desired Date/Time Format:
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");            //Format the current date and time
        String formattedDate = currentDate_Time.format(formatter);
        System.out.println("Your password " + password + " was created on " + formattedDate);
    }

}