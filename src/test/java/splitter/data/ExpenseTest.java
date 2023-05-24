package splitter.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpenseTest {
    @Test
    @DisplayName("199.993 gets rounded correctly to 199.99")
    void testRounding() {
        Expense expense = new Expense("199.993");
        BigDecimal value = expense.getValue();
        assertThat(value).isEqualTo("199.99");
    }

    @Test
    @DisplayName("Expense has to be a number")
    void testNFException() {
        Executable badExpense = () -> new Expense( "89a");
        assertThrows(NumberFormatException.class, badExpense);
    }

    @Test
    @DisplayName("No expense can be null")
    void testNull() {
        Executable badExpense = () -> new Expense( null);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, badExpense);
        assertThat(exception.getMessage()).containsIgnoringCase("expense");
    }

    @Test
    @DisplayName("Expense value gets rounded correctly after adding expenses")
    void testAddExpense() {
        Expense expense = new Expense("200");
        expense.add(new Expense("32.5"));
        expense.add(new Expense("12.755"));
        assertThat(expense.getValue()).isEqualTo("245.26");
    }
    @Test
    @ExtendWith(OutputCaptureExtension.class)
    @DisplayName("A warning gets logged when passing a number of which the decimal part exceeds 2 digits")
    void testWarning(CapturedOutput output) {
        new Expense("65.998");
        assertThat(output.getErr()).contains("WARNING", "decimal");
    }
    @Test
    @ExtendWith(OutputCaptureExtension.class)
    @DisplayName("No warning gets logged when passing a number of which the decimal part does not exceed 2 digits")
    void testNoWarning(CapturedOutput output) {
        Expense expense = new Expense("1.78");
        assertThat(output.getErr()).isEmpty();
    }

}
