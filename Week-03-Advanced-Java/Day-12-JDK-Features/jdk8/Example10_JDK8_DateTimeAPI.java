/**
 * JDK 8 Date-Time API
 */
package jdk8;
import java.time.*;
import java.time.format.*;
public class Example10_JDK8_DateTimeAPI {
    public static void main(String[] args) {
        // LocalDate
        LocalDate today = LocalDate.now();
        LocalDate specificDate = LocalDate.of(2024, 12, 25);
        System.out.println("Today: " + today);
        System.out.println("Christmas: " + specificDate);

        // LocalTime
        LocalTime now = LocalTime.now();
        LocalTime specificTime = LocalTime.of(14, 30);
        System.out.println("\nCurrent time: " + now);
        System.out.println("Specific time: " + specificTime);

        // LocalDateTime
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("\nDateTime: " + dateTime);

        // Formatting
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatted = dateTime.format(formatter);
        System.out.println("Formatted: " + formatted);

        // Period and Duration
        Period period = Period.between(specificDate, today);
        System.out.println("\nDays until Christmas: " + period.getDays());

        Duration duration = Duration.between(specificTime, now);
        System.out.println("Hours since 14:30: " + duration.toHours());
    }
}
