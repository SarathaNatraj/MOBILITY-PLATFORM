package dateandtime;

import java.time.Instant;

public class InstantEx {
    public static void main(String[] args) {
        // Current instant (UTC)
        Instant now = Instant.now();
        
        System.out.println(System.currentTimeMillis());
        
        System.out.println("Current Instant: " + now);
        System.out.println(now.ofEpochSecond(0));
        System.out.println(now.ofEpochSecond(1000000));
    }
}
