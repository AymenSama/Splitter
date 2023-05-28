package splitter.interaction;

import splitter.data.Person;

import java.util.ArrayList;
import java.util.List;

import static splitter.interaction.NumericString.isNumeric;

public class PersonBuilder {
    private final List<Person> people = new ArrayList<>();

    public List<Person> build(String[] strings) {
        if (strings == null) {
            throw new IllegalArgumentException("String array can't be null");
        }

        Person person = null;

        for (String string : strings) {
            if (string == null) {
                continue;
            }
            person = handleString(string, person);
            if (person != null) {
                addIfAbsent(person);
            }
        }

        return people;
    }

    private Person handleString(String string, Person person) {
        if (isNumeric(string) && person != null) {
            person.addExpense(string);
            person = null;
        } else if (!isNumeric(string)) {
            person = findOrMake(string);
        }
        return person;
    }

    private Person findOrMake(String name) {
        return people.stream()
                .filter(p -> p.getName().equals(name))
                .findAny()
                .orElse(new Person(name));
    }

    private void addIfAbsent(Person person) {
        if (!people.contains(person)) {
            people.add(person);
        }
    }
}