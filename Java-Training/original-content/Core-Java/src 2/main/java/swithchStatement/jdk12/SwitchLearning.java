package swithchStatement.jdk12;

/*
Java 12 further enhanced the switch statement and introduced switch expressions as a preview feature.

It introduced a flurry of new features:
1. You can return values from a switch block and hence switch statements became switch expressions
2. You can have multiple values in a case label
3. You can return value from a switch expression through the arrow operator or through the “break” keyword
*/

public class SwitchLearning {

    public static void main(String[] args) {

        SwitchLearning switchLearning = new SwitchLearning();
        System.out.println(switchLearning.getDay("Monday"));

    }

    public String getDay(String day) {

        String weekday = "Weekday";
        String weekend = "Weekend";

        return switch (day) {
            case "Monday":
                yield weekday; // Ideally its break in JDK12
            case "Tuesday":
                yield weekday; // Ideally its break in JDK12
            case "Wednesday":
                yield weekday; // Ideally its break in JDK12
            case "Thursday":
                yield weekday; // Ideally its break in JDK12
            case "Friday":
                yield weekday; // Ideally its break in JDK12
            case "Saturday":
                yield weekend; // Ideally its break in JDK12
            case "Sunday":
                yield weekend; // Ideally its break in JDK12
            default:
                yield "Unknown"; // Ideally its break in JDK12
        };
    }
}
