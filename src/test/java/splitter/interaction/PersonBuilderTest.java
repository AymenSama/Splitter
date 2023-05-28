package splitter.interaction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import splitter.data.Person;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonBuilderTest {
    @Test
    @DisplayName("An empty array has no people in it")
    void testNoPeople() {
        String[] input = {};
        List<Person> people = new PersonBuilder().build(input);
        assertThat(people).isEmpty();
    }
    @Test
    @DisplayName("An array with one name generates a list with one person")
    void testOnePerson_noExpense() {
        String[] input = {"Martin"};
        List<Person> people = new PersonBuilder().build(input);
        assertThat(people).containsExactlyInAnyOrder(new Person("Martin"));
    }
    @Test
    @DisplayName("An array with a name and a number builds a list with one person having that expense")
    void testOnePerson_expense() {
        String[] input = {"Bob", "25.89"};
        List<Person> people = new PersonBuilder().build(input);
        assertThat(people).containsExactlyInAnyOrder(new Person("Bob", "25.89"));
    }
    @Test
    @DisplayName("An array with two names and two numbers builds a list with 2 people")
    void testTwoPeople() {
        String[] input = {"Bob", "25.89", "Tim", "80.9"};
        List<Person> people = new PersonBuilder().build(input);
        assertThat(people).containsExactlyInAnyOrder(new Person("Bob", "25.89"), new Person("Tim", "80.9"));
    }
    @Test
    @DisplayName("Expenses get skipped when no names precede them")
    void testMissingNames() {
        String[] input = {"70" , "Bob", "25.89", "80", "102", "Tim"};
        List<Person> people = new PersonBuilder().build(input);
        assertThat(people).containsExactlyInAnyOrder(new Person("Bob", "25.89"), new Person("Tim", "0"));
    }
    @Test
    @DisplayName("Passing null throws an IllegalArgumentException")
    void testNullArgument() {
        Executable e = () -> new PersonBuilder().build(null);
        assertThrows(IllegalArgumentException.class, e);
    }
    @Test
    @DisplayName("Null strings get skipped")
    void testNullStrings() {
        String[] input = {null ,"Bob", null, "Martin", "Tim"};
        List<Person> people = new PersonBuilder().build(input);
        assertThat(people).containsExactlyInAnyOrder(new Person("Bob"), new Person("Martin"), new Person("Tim"));
    }
    @Test
    @DisplayName("Expenses get added to the same person when a name appears more than once in the array")
    void testNameDuplicate() {
        String[] input = {"Willy", "320", "Tim", "40", "Gaby", "48", "Tim", "100"};
        List<Person> people = new PersonBuilder().build(input);
        assertThat(people).containsExactlyInAnyOrder(
                new Person("Tim", "140"),
                new Person("Willy", "320"),
                new Person("Gaby", "48")
        );
    }
}
