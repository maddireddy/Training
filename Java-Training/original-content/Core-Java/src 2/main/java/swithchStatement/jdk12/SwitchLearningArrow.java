package swithchStatement.jdk12;

public class SwitchLearningArrow {

    public static void main(String[] args) {

        SwitchLearningArrow switchLearningArrow = new SwitchLearningArrow();
        System.out.println(switchLearningArrow.getDay("Monday"));

    }

    public String getDay(String day) {
        return switch (day) {
            case "Monday" -> "Week day";
            case "Tuesday" -> "Week day";
            case "Wednesday" -> "Week day";
            case "Thursday" -> "Week day";
            case "Friday" -> "Week day";
            case "Saturday" -> "Weekend";
            case "Sunday" -> "Weekend";
            default -> "Unknown";
        };
    }
}
