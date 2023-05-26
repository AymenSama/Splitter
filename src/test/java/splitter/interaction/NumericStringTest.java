package splitter.interaction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumericStringTest {
    @Test
    @DisplayName("'132' is numeric")
    void testInt() {
        String s = "132";
        boolean numeric = NumericString.isNumeric(s);
        assertThat(numeric).isTrue();
    }
    @Test
    @DisplayName("'8.789' is numeric")
    void testFloat() {
        String s = "8.789";
        boolean numeric = NumericString.isNumeric(s);
        assertThat(numeric).isTrue();
    }
    @Test
    @DisplayName("'abba' is not numeric")
    void testWord() {
        String s = "abba";
        boolean numeric = NumericString.isNumeric(s);
        assertThat(numeric).isFalse();
    }
    @Test
    @DisplayName("'986a97' is not numeric")
    void testLetterInNumber() {
        String s = "986a97";
        boolean numeric = NumericString.isNumeric(s);
        assertThat(numeric).isFalse();
    }
    @Test
    @DisplayName("'45,97' is not numeric")
    void testComma() {
        String s = "45,97";
        boolean numeric = NumericString.isNumeric(s);
        assertThat(numeric).isFalse();
    }
    @Test
    @DisplayName("'63.9.7' is not numeric")
    void testPoints() {
        String s = "63.9.7";
        boolean numeric = NumericString.isNumeric(s);
        assertThat(numeric).isFalse();
    }
    @Test
    @DisplayName("Empty string is not numeric")
    void testEmptyString() {
        String s = "";
        boolean numeric = NumericString.isNumeric(s);
        assertThat(numeric).isFalse();
    }
    @Test
    @DisplayName("'.1334' is not numeric")
    void testStartsWithPoint() {
        String s = ".1334";
        boolean numeric = NumericString.isNumeric(s);
        assertThat(numeric).isFalse();
    }
    @Test
    @DisplayName("'087.' is not numeric")
    void testEndsWithPoint() {
        String s = "087.";
        boolean numeric = NumericString.isNumeric(s);
        assertThat(numeric).isFalse();
    }
    @Test
    @DisplayName("Providing null throws an IllegalArgumentException")
    void testNull() {
        String s = null;
        Executable e = () -> NumericString.isNumeric(s);
        assertThrows(IllegalArgumentException.class, e);
    }
}
