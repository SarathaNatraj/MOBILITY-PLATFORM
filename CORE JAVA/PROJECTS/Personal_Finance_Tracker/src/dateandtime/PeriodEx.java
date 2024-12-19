package dateandtime;

import java.time.LocalDate;
import java.time.Period;

public class PeriodEx {
    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(2007, 1, 1);
        LocalDate endDate = LocalDate.now();
        
        Period period = Period.between(startDate, endDate);
        
        System.out.println("Period: " + period.getYears() + " years, " + period.getMonths() + " months, " + period.getDays() + " days");
    }
}
