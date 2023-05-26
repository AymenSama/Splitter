package splitter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import splitter.data.Person;
import splitter.finances.Money;
import splitter.finances.Transfer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ExpenseSplitterTest {
    @Test
    @DisplayName("No transfers when no people have made expenses")
    void testNoPeople() {
        List<Person> people = List.of();
        List<Transfer> transfers = ExpenseSplitter.getTransfers(people);
        assertThat(transfers).isEmpty();
    }
    @Test
    @DisplayName("No transfers when one person has made an expense")
    void testOnePerson() {
        List<Person> people = List.of(new Person("Axel", "90"));
        List<Transfer> transfers = ExpenseSplitter.getTransfers(people);
        assertThat(transfers).isEmpty();
    }
    @Test
    @DisplayName("Expenses get split equally between two people")
    void testTwoPeople() {
        Person axel = new Person("Axel", "90");
        Person martin = new Person("Martin", "65.7");
        Money owed = new Money("12.15");
        List<Person> people = List.of(martin, axel);
        List<Transfer> transfers = ExpenseSplitter.getTransfers(people);
        assertThat(transfers).containsExactlyInAnyOrder(new Transfer(martin, axel, owed));
    }
}
