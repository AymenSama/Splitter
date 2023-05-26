package splitter.finances;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import splitter.data.Person;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    @Test
    @DisplayName("Sending money to oneself throws an exception (nice try :P)")
    void testSelfTransfer() {
        Person tim = new Person("Tim");
        Executable badTransfer = () -> new Transfer(tim, tim, new Money("1000000000000"));
        assertThrows(UnsupportedOperationException.class, badTransfer);
    }
}
