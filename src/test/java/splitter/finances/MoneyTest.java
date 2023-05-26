package splitter.finances;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {
    @Test
    @DisplayName("199.993 gets rounded correctly to 199.99")
    void testRounding() {
        Money money = new Money("199.993");
        BigDecimal value = money.getValue();
        assertThat(value).isEqualTo("199.99");
    }

    @Test
    @DisplayName("Money has to be a number")
    void testNFException() {
        Executable badMoney = () -> new Money( "89a");
        assertThrows(NumberFormatException.class, badMoney);
    }

    @Test
    @DisplayName("No money can be null")
    void testNull() {
        Executable badMoney = () -> new Money( null);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, badMoney);
        assertThat(exception.getMessage()).containsIgnoringCase("money");
    }

    @Test
    @DisplayName("Money value gets rounded correctly after adding expenses")
    void testAddExpense() {
        Money money = new Money("200");
        money.add(new Money("32.5"));
        money.add(new Money("12.755"));
        assertThat(money.getValue()).isEqualTo("245.26");
    }
    @Test
    @ExtendWith(OutputCaptureExtension.class)
    @DisplayName("A warning gets logged when passing a number of which the decimal part exceeds 2 digits")
    void testWarning(CapturedOutput output) {
        new Money("65.998");
        assertThat(output.getErr()).contains("WARNING", "decimal");
    }
    @Test
    @ExtendWith(OutputCaptureExtension.class)
    @DisplayName("No warning gets logged when passing a number of which the decimal part does not exceed 2 digits")
    void testNoWarning(CapturedOutput output) {
        Money money = new Money("1.78");
        assertThat(output.getErr()).isEmpty();
    }

}
