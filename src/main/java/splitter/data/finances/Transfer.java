package splitter.data.finances;

import splitter.data.Person;

public record Transfer(Person sender, Person receiver, Money value) {
    public Transfer {
        if (sender == null || receiver == null || value == null) {
            throw new IllegalArgumentException("An argument for a transfer can't be null");
        }
        if (sender.equals(receiver)) {
            throw new UnsupportedOperationException("Sender and receiver can not be identical");
        }
        if (value.equals(new Money("0"))) {
            throw new IllegalArgumentException("A transfer can't have zero money");
        }
    }
}
