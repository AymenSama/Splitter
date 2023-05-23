package splitter.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonTest {

    @Test
    @DisplayName("Willy spends 320 Euro")
    void test_01() {
        Person p = new Person("Willy", "320");
        BigDecimal expense = p.getExpenseTotal();
        String name = p.getName();
        assertThat(name).isEqualTo("Willy");
        assertThat(expense).isEqualTo("320.00");
    }

    @Test
    @DisplayName("Martin spends 199.99 Euro")
    void test_02() {
        Person p = new Person("Martin", "199.993");
        BigDecimal expense = p.getExpenseTotal();
        String name = p.getName();
        assertThat(name).isEqualTo("Martin");
        assertThat(expense).isEqualTo("199.99");
    }
    @Test
    @DisplayName("Expense has to be a number")
    void test_03() {
        Executable badPerson = () -> new Person("this will fail", "89a");
        assertThrows(NumberFormatException.class, badPerson);
    }
    @Test
    @DisplayName("No name can be null")
    void test_04() {
        Executable badPerson = () -> new Person(null, "");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, badPerson);
        assertThat(exception.getMessage()).containsIgnoringCase("name");
    }
    @Test
    @DisplayName("No expense can be null")
    void test_05() {
        Executable badPerson = () -> new Person("this will fail", null);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, badPerson);
        assertThat(exception.getMessage()).containsIgnoringCase("expense");
    }

    @Test
    @DisplayName("A person can make more expenses")
    void test_06() {
        Person p = new Person("Martin", "199.993");
        p.addExpense("50.67");
        assertThat(p.getExpenseTotal()).isEqualTo("250.66");
    }
    @Test
    @DisplayName("Expense total gets rounded correctly after adding expenses")
    void test_07() {
        Person p = new Person("Andrew", "200");
        p.addExpense("32.5");
        p.addExpense("12.755");
        assertThat(p.getExpenseTotal()).isEqualTo("245.26");
    }
}
