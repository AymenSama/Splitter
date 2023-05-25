package splitter.interaction;

import splitter.data.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonBuilder {
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
                addIfAbsent(people, person);
            }
        }

        return people;
    }

    private static Person handleString(List<Person> people, Person person, String string) {
        if (NumericString.isNumeric(string) && person != null) {
            person.addExpense(string);
            person = null;
        } else if (!NumericString.isNumeric(string)) {
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

    private static void addIfAbsent(List<Person> people, Person person) {
        if (!people.contains(person)) {
            people.add(person);
        }
    }
}