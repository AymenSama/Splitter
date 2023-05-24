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
}
