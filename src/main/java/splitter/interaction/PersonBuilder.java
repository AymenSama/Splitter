package splitter.interaction;

import splitter.data.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonBuilder {
    private static boolean isNumeric(String string) {
        // Special cases for which the below implementation doesn't work
        if (string.equals("") || string.startsWith(".") || string.endsWith(".")) {
            return false;
        }

        String[] parts = string.split("\\.", 2);
        for (String part: parts) {
            boolean isNotNumeric = part.chars().anyMatch(c -> !Character.isDigit(c));
            if (isNotNumeric) {
                return false;
            }
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
            if (string == null) {
                continue;
            }
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