package splitter.finances;

import splitter.data.Person;

public record Transfer(Person sender, Person receiver, Money value) {
    public Transfer {
        if (sender.equals(receiver)) {
            throw new UnsupportedOperationException("Sender and receiver can not be identical");
        }
    }
}
