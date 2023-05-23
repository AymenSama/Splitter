package splitter.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonTest {
    @Test
    @DisplayName("Teste dass Willy 320 Euro ausgibt.")
    void test_01() {
        Person p = new Person("Willy", "320");
        BigDecimal expense = p.getExpense();
        String name = p.getName();
        assertThat(name).isEqualTo("Willy");
        assertThat(expense).isEqualTo("320");
    }
}
