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
        if (strings == null) {
            throw new IllegalArgumentException("String array can't be null");
        }

        List<Person> people = new ArrayList<>();
        Person person = null;

        for (String string : strings) {
            person = makePersonOrAddExpense(person, string);
            if (person != null) {
                people.add(person);
            }
        }

        return people;
    }

    private static Person makePersonOrAddExpense(Person person, String string) {
        if (isNumeric(string) && person != null) {
            person.addExpense(string);
            person = null;
        } else if (!isNumeric(string)) {
            person = new Person(string);
        }
        return person;
    }
}