package splitter.splitter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import splitter.data.Person;
import splitter.data.finances.Money;
import splitter.data.finances.Transfer;

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
    @DisplayName("Expenses for which the average does not need to be rounded get equally split")
    void testGroup_noRoundingAverage() {
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
    @Test
    @DisplayName("No transfers when expenses are already split")
    void testEqualExpenses() {
        Person leo = new Person("Leo", "45.2");
        Person martin = new Person("Martin", "45.2");
        Person andreas = new Person("Andreas", "45.2");
        List<Person> people = List.of(leo, martin, andreas);
        ExpenseSplitter splitter = new ExpenseSplitter(people);
        List<Transfer> transfers = splitter.getTransfers();
        assertThat(transfers).isEmpty();
    }
    @Test
    @DisplayName("Expenses for which the average needs to be rounded get equally split")
    void testGroup_roundingAverage() {
        Person leo = new Person("Leo", "45");
        Person martin = new Person("Martin", "45");
        Person andreas = new Person("Andreas", "20");
        List<Person> people = List.of(leo, martin, andreas);
        ExpenseSplitter splitter = new ExpenseSplitter(people);
        List<Transfer> transfers = splitter.getTransfers();
        assertThat(transfers).containsExactlyInAnyOrder(
                new Transfer(andreas, leo, new Money("8.33")),
                new Transfer(andreas, martin, new Money("8.33"))
        );
    }
    @Test
    @DisplayName("Splitting is not deterministic for certain expenses")
    void testNonDeterminism() {
        Person a = new Person("A", "400");
        Person b = new Person("B", "400");
        Person c = new Person("C", "300");
        Person d = new Person("D", "300");
        Person e = new Person("E", "350");
        List<Person> people = List.of(a, b, c, d, e);
        ExpenseSplitter splitter = new ExpenseSplitter(people);

        List<Transfer> transfers = splitter.getTransfers();

        assertThat(transfers).containsAnyOf(
                new Transfer(c, a, new Money("50")),
                new Transfer(c, b, new Money("50"))
        );
        assertThat(transfers).containsAnyOf(
                new Transfer(d, a, new Money("50")),
                new Transfer(d, b, new Money("50"))
        );
    }
}
