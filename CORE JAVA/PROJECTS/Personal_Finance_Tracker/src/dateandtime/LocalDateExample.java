package dateandtime;

import java.time.LocalDate;
import java.util.Date;

public class LocalDateExample {
    public static void main(String[] args) {
        // Current date
        LocalDate today = LocalDate.now();
        
        // Specific date
        LocalDate specificDate = LocalDate.of(1996, 12, 17);

        System.out.println("Today's Date: " + today);
        System.out.println("Specific Date: " + specificDate);
        
        LocalDate futureDate = today.plusDays(10);
        System.out.println(" Future Date : "+futureDate);
        
        LocalDate futureDate1 = specificDate.minusDays(10);
        System.out.println(" Older Date : "+futureDate1);
        
        Date d = new Date();
        System.out.println(" Date obj : "+d);
        
    }
}