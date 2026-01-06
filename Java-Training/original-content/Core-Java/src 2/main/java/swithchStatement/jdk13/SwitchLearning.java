package swithchStatement.jdk13;

/*
This word was replaced by “yield” later in Java 13.

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
                yield weekday;
            case "Tuesday":
                yield weekday;
            case "Wednesday":
                yield weekday;
            case "Thursday":
                yield weekday;
            case "Friday":
                yield weekday;
            case "Saturday":
                yield weekend;
            case "Sunday":
                yield weekend;
            default:
                yield "Unknown";
        };
    }
}
