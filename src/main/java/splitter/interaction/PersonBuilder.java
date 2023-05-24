package splitter.interaction;

import splitter.data.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonBuilder {
    private static boolean isNumeric(String string) {
        try {
            Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    public static List<Person> build(String[] strings) {
        List<Person> accumulator = new ArrayList<>();
        Person p = null;
        for (String string : strings) {
            if (isNumeric(string)) {
                p.addExpense(string);
            } else {
                p = new Person(string);
                accumulator.add(p);
            }
        }
        return accumulator;
    }
}