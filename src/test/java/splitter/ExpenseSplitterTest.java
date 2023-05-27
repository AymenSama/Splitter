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
        ExpenseSplitter splitter = new ExpenseSplitter(people);
        List<Transfer> transfers = splitter.getTransfers();
        assertThat(transfers).isEmpty();
    }
    @Test
    @DisplayName("No transfers when one person has made an expense")
    void testOnePerson() {
        List<Person> people = List.of(new Person("Axel", "90"));
        ExpenseSplitter splitter = new ExpenseSplitter(people);
        List<Transfer> transfers = splitter.getTransfers();
        assertThat(transfers).isEmpty();
    }
    @Test
    @DisplayName("Expenses get split equally between two people")
    void testTwoPeople() {
        Person axel = new Person("Axel", "90");
        Person martin = new Person("Martin", "65.7");
        Money owed = new Money("12.15");
        List<Person> people = List.of(martin, axel);
        ExpenseSplitter splitter = new ExpenseSplitter(people);
        List<Transfer> transfers = splitter.getTransfers();
        assertThat(transfers).containsExactlyInAnyOrder(new Transfer(martin, axel, owed));
    }
    @Test
    @DisplayName("Expenses get split equally between more than two people")
    void testGroup() {
        Person willy = new Person("Willy" ,"320");
        Person tim = new Person("Tim" , "140");
        Person gaby = new Person("Gaby" , "48");
        Person karl = new Person("Karl");
        Money gabyOwsTim = new Money("13");
        Money gabyOwsWilly = new Money("66");
        Money karlOwsWilly = new Money("127");
        List<Person> people = List.of(willy, tim, gaby, karl);
        ExpenseSplitter splitter = new ExpenseSplitter(people);
        List<Transfer> transfers = splitter.getTransfers();
        assertThat(transfers).containsExactlyInAnyOrder(
                new Transfer(gaby, tim, gabyOwsTim),
                new Transfer(gaby, willy, gabyOwsWilly),
                new Transfer(karl, willy, karlOwsWilly)
        );
    }
}
