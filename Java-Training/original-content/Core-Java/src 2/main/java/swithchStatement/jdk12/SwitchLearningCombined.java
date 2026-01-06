package swithchStatement.jdk12;

public class SwitchLearningCombined {

    public static void main(String[] args) {

        SwitchLearningCombined switchLearningCombined = new SwitchLearningCombined();
        System.out.println(switchLearningCombined.getDay("Sunday"));

    }

    public String getDay(String day) {
        return switch (day) {
            case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" -> "Week day";
            case "Saturday", "Sunday" -> "Weekend";
            default -> "Unknown";
        };
    }
}
