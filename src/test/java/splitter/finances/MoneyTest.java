package splitter.finances;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

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
    @DisplayName("Passing a null BigDecimal throws an exception")
    void testNull_01() {
        BigDecimal bd = null;
        Executable badMoney = () -> new Money(bd);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, badMoney);
        assertThat(exception.getMessage()).containsIgnoringCase("null");
    }
    @Test
    @DisplayName("Passing a null string throws an exception")
    void testNull_02() {
        String s = null;
        Executable badMoney = () -> new Money(s);
        NullPointerException exception = assertThrows(NullPointerException.class, badMoney);
        assertThat(exception.getMessage()).containsIgnoringCase("null");
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
    @DisplayName("Providing a negative value throws an exception")
    void testNegativeMoney() {
        Executable badMoney = () -> new Money("-1");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, badMoney);
        assertThat(exception.getMessage()).containsIgnoringCase("negative");
    }
    @Test
    @DisplayName("Money is equal when it has the same (rounded) value")
    void testEquals() {
        Money m1 = new Money("98.125");
        Money m2 = new Money("98.13");
        boolean isEqual = m1.equals(m2);
        assertThat(isEqual).isTrue();
    }
    @Test
    @DisplayName("Money is different when it has different value")
    void testNotEquals() {
        Money m1 = new Money("98.12");
        Money m2 = new Money("98.13");
        boolean isEqual = m1.equals(m2);
        assertThat(isEqual).isFalse();
    }
    @Test
    @DisplayName("Passing a BigDecimal as an argument yields a correct (rounded) result")
    void testBigDecimalArg() {
        Money money = new Money(new BigDecimal("4562.9874"));
        assertThat(money.getValue()).isEqualTo("4562.99");
    }

}