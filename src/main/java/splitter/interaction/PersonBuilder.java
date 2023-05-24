package splitter.interaction;

import splitter.data.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonBuilder {
    public static List<Person> build(String[] strings) {
        List<Person> accumulator = new ArrayList<>();
        for (String string: strings) {
            accumulator.add(new Person(string));
        }
        return accumulator;
    }
}