package splitter.data;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonTest {
    @Disabled
    @Test
    @DisplayName("Willy spends 320 Euro")
    void test_01() {
        Person p = new Person("Willy", "320");
        BigDecimal expense = p.getExpense();
        String name = p.getName();
        assertThat(name).isEqualTo("Willy");
        assertThat(expense).isEqualTo("320");
    }

    @Test
    @DisplayName("Martin spends 199.99 Euro")
    void test_02() {
        Person p = new Person("Martin", "199.993");
        BigDecimal expense = p.getExpense();
        String name = p.getName();
        assertThat(name).isEqualTo("Martin");
        assertThat(expense).isEqualTo("199.99");
    }
}
