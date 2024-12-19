package dateandtime;

import java.time.ZonedDateTime;
import java.time.ZoneId;

public class ZonedDateTimeEx {
    public static void main(String[] args) {
        // Current date and time with time zone
        ZonedDateTime zonedNow = ZonedDateTime.now();
        
        // Specific date and time with a specific time zone
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2024, 12, 17, 14, 30, 45, 0, ZoneId.of("America/New_York"));

        System.out.println("Zoned Date and Time (Current): " + zonedNow);
        System.out.println("Zoned Date and Time (Specific): " + zonedDateTime);
    }
}