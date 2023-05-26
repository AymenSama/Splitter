package splitter.finances;

import splitter.data.Person;

public record Transfer(Person sender, Person receiver, Money value) {}
