package dateandtime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeParse {
    public static void main(String[] args) {
    	
    	//dateString = "yyyy-MM-d"
        String dateString = "17/12/2024";
        
        // Define a custom format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        LocalDate date = LocalDate.parse(dateString,formatter);
        System.out.println("Parsed Date: " + date);
        
        
    }
}
