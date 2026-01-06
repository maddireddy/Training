package stringLearning.jdk21;

import java.text.MessageFormat;

import static java.lang.StringTemplate.RAW;

/*
String Templates as a preview feature in Java 21.
 This enhancement aims to simplify Java programming by allowing the combination of literal text with
 embedded expressions and template processors. It is extremely useful for strings that include runtime-computed
 values or are composed of user-provided values for systems like databases.

With this, Java developers can now enhance the language's string literals and text blocks with string templates.
This new feature aims to simplify writing Java programs, improve the readability of expressions that mix text and expressions,
and enhance the security of Java programs, especially those that compose strings from user-provided values.

*/
public class StringLearning {


    public static void main(String[] args) {
        String name = "Siva";
        String info = STR."My name is \{name}";
        System.out.println(info);
        assert info.equals("My name is Siva"); // true

        int x = 10;
        int y = 20;
        String result = STR."\{x} + \{y} = \{x + y}";
        System.out.println(result);

        StringTemplate st = RAW."\{x} + \{y} = \{x + y}";
        String resultAnother = STR.process(st);

        System.out.println(st.fragments());
        System.out.println(st.interpolate());

        System.out.println(resultAnother);
    }

    String composeUsingPlus(String feelsLike, String temperature, String unit) {
        return "Today's weather is " + feelsLike +
                ", with a temperature of " + temperature + " degrees " + unit;
    }

    String composeUsingStringBuilder(String feelsLike, String temperature, String unit) {
        return new StringBuilder()
                .append("Today's weather is ")
                .append(feelsLike)
                .append(", with a temperature of ")
                .append(temperature)
                .append(" degrees ")
                .append(unit)
                .toString();
    }

    /*
    Java provides us with the capability to separate the static part of the String and the parameters,
    such as the temperature and unit, with the String.format() or the formatted() methods
     */

    String composeUsingFormatters(String feelsLike, String temperature, String unit) {
        return String.format("Today's weather is %s, with a temperature of %s degrees %s",
                feelsLike, temperature, unit);
    }

    /*
    Java provides a MessageFormat class of the Java.text package that helps in the composition of text
    messages with placeholders for dynamic data.

    Localisation and Internationalisation heavily use this. We can use MessageFormat.format() in plain String composition
     */

    String composeUsingMessageFormatter(String feelsLike, String temperature, String unit) {
        return MessageFormat.format("Today''s weather is {0}, with a temperature of {1} degrees {2}",
                feelsLike, temperature, unit);
    }

    String interpolationUsingSTRProcessor(String feelsLike, String temperature, String unit) {
        return STR
                ."Today's weather is \{feelsLike}, with a temperature of \{temperature} degrees \{unit}";
    }

    String interpolationOfJSONBlock(String feelsLike, String temperature, String unit) {
        return STR
                ."""
      {
        "feelsLike": "\{feelsLike}",
        "temperature": "\{temperature}",
        "unit": "\{unit}"
      }
      """;
    }

    String interpolationWithExpressions() {
        return STR
                ."Today's weather is \{getFeelsLike()}, with a temperature of \{getTemperature()} degrees \{getUnit()}";
    }

    private Object getUnit() {
        return "F";
    }

    private Object getTemperature() {
        return "100";
    }

    private Object getFeelsLike() {
        return "110";
    }
}
