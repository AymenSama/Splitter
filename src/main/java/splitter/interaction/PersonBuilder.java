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
            person = handleString(people, person, string);
            if (person != null) {
                people.add(person);
            }
        }

        return people;
    }

    private static Person handleString(List<Person> people, Person person, String string) {
        if (isNumeric(string) && person != null) {
            person.addExpense(string);
            person = null;
        } else if (!isNumeric(string)) {
            person = findOrMake(people, string);
        }
        return person;
    }

    private static Person findOrMake(List<Person> people, String name) {
        return people.stream()
                .filter(p -> p.getName().equals(name))
                .findAny()
                .orElse(new Person(name));
    }
}