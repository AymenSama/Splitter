package splitter.interaction;

import splitter.data.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonBuilder {
    public static List<Person> build(String[] strings) {
        List<Person> accumulator = new ArrayList<>();
        Person p = null;
        for (String string : strings) {
            try {
                Double.parseDouble(string);
            } catch (NumberFormatException e) {
                p = new Person(string);
                accumulator.add(p);
                continue;
            }
            p.addExpense(string);
        }
        return accumulator;
    }
}