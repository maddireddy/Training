package testLearning;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilsTesterTest {
    @Test
    public void lazyTrim() {
        String input = "abc";
        String expected = "abc";
        String actual = StringUtilsTester.lazyTrim(input);

        assertEquals(expected, actual);
    }
}
