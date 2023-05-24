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
    void testPerson() {
        Person p = new Person("Willy", "320");
        BigDecimal expense = p.getExpenseTotal();
        String name = p.getName();
        assertThat(name).isEqualTo("Willy");
        assertThat(expense).isEqualTo("320.00");
    }

    @Test
    @DisplayName("No name can be null")
    void testNull() {
        Executable badPerson = () -> new Person(null, "");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, badPerson);
        assertThat(exception.getMessage()).containsIgnoringCase("name");
    }

    @Test
    @DisplayName("A person can make more expenses")
    void testAddExpense() {
        Person p = new Person("Martin", "199.993");
        p.addExpense("50.67");
        assertThat(p.getExpenseTotal()).isEqualTo("250.66");
    }
    @Test
    @DisplayName("Philipp has no expenses")
    void testPersonNoExpense() {
        Person p = new Person("Philipp");
        BigDecimal expense = p.getExpenseTotal();
        String name = p.getName();
        assertThat(name).isEqualTo("Philipp");
        assertThat(expense).isZero();
    }
    @Test
    @DisplayName("Two people are the same when they have the same name and expense total")
    void testEquals() {
        Person p1 = new Person("Martin", "30");
        Person p2 = new Person("Martin", "30");
        boolean isEqual = p1.equals(p2);
        assertThat(isEqual).isTrue();
    }

    @Test
    @DisplayName("Two people are different when either the name or the expense total is different")
    void testNotEquals() {
        Person p1 = new Person("Martin", "40");
        Person p2 = new Person("Tim", "40");
        Person p3 = new Person("Tim", "21.56");
        assertThat(p1.equals(p2)).isFalse();
        assertThat(p2.equals(p3)).isFalse();
    }
}
