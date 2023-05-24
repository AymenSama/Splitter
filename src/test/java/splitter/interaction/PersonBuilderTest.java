package splitter.interaction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import splitter.data.Person;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonBuilderTest {
    @Test
    @DisplayName("An empty array has no people in it")
    void testNoPeople() {
        String[] input = {};
        List<Person> people = PersonBuilder.build(input);
        assertThat(people).isEmpty();
    }
    @Test
    @DisplayName("An array with one name generates a list with one person")
    void testOnePerson_noExpense() {
        String[] input = {"Martin"};
        List<Person> people = PersonBuilder.build(input);
        assertThat(people).containsExactly(new Person("Martin"));
    }
    @Test
    @DisplayName("An array with a name and a number builds a list with one person having that expense")
    void testOnePerson_expense() {
        String[] input = {"Bob", "25.89"};
        List<Person> people = PersonBuilder.build(input);
        assertThat(people).containsExactly(new Person("Bob", "25.89"));
    }
    @Test
    @DisplayName("An array with two names and two numbers builds a list with 2 people")
    void testTwoPeople() {
        String[] input = {"Bob", "25.89", "Tim", "80.9"};
        List<Person> people = PersonBuilder.build(input);
        assertThat(people).containsExactly(new Person("Bob", "25.89"), new Person("Tim", "80.9"));
    }
}
