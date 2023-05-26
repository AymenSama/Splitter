package splitter.finances;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import splitter.data.Person;

import static org.assertj.core.api.Assertions.assertThat;

public class TransferTest {
    @Test
    @DisplayName("Lars sends money of value 108 to Mike")
    void testTransfer() {
        Person lars = new Person("Lars");
        Person mike = new Person("Mike");
        Money money = new Money("100");
        Transfer transfer = new Transfer(lars, mike, money);
        assertThat(transfer.sender().getName()).isEqualTo("Lars");
        assertThat(transfer.receiver().getName()).isEqualTo("Mike");
        assertThat(transfer.value().getValue()).isEqualTo("100.00");
    }
}
