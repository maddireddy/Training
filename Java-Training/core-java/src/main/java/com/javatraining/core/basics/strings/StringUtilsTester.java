package testLearning;


import org.apache.commons.lang.StringUtils;

public class StringUtilsTester {

    public static String lazyTrim(String input) {
        if (input == null || input.length() == 0) {
            return "";
        }

        if (input.charAt(0) != ' ' || input.charAt(input.length() - 1) != ' ') {
            return input;
        }

        return StringUtils.trim(input);
    }
}
