package splitter.splitter;

import splitter.data.Person;
import splitter.data.finances.Money;
import splitter.data.finances.Transfer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class ExpenseSplitter {
    private final Collection<Person> people;
    private final List<Transfer> transfers = new ArrayList<>();
    private final Map<Person, BigDecimal> owedTo = new HashMap<>();

    public ExpenseSplitter(Collection<Person> people) {
        this.people = people;
    }

    public List<Transfer> getTransfers() {
        BigDecimal average = expenseAverage();
        people.forEach(p -> owedTo.put(p, p.expenseTotal().subtract(average)));

        while (!expensesMatched()) {
            Person sender = getSender();
            Person receiver = getReceiver();
            BigDecimal toSend = toSend(sender, receiver);
            Transfer transfer = new Transfer(sender, receiver, new Money(toSend));
            transfers.add(transfer);
        }


        return transfers;
    }

    private Person getSender() {
        BigDecimal minValue = owedTo.values().stream()
                .min(Comparator.naturalOrder())
                .orElseThrow();

        return getPerson(minValue);
    }

    private Person getPerson(BigDecimal value) {
        return owedTo.entrySet().stream()
                .filter(e -> e.getValue().equals(value))
                .map(Map.Entry::getKey)
                .findAny()
                .orElseThrow();
    }

    private Person getReceiver() {
        BigDecimal maxValue = owedTo.values().stream()
                .max(Comparator.naturalOrder())
                .orElseThrow();

        return getPerson(maxValue);
    }

    private BigDecimal toSend(Person sender, Person receiver) {
        BigDecimal x = owedTo.get(sender);
        BigDecimal y = owedTo.get(receiver);
        BigDecimal toSend = x.abs();
        // Default assumption: y >= |x|. Swap sender and receiver otherwise.
        if (y.compareTo(x.abs()) < 0) {
            sender = returnFirst(receiver, receiver = sender);
            toSend = y;
        }

        owedTo.replace(sender, BigDecimal.ZERO);
        owedTo.replace(receiver, y.add(x));
        return toSend;
    }

    private static <T> T returnFirst(T t1, T t2) {
        return t1;
    }

    private boolean expensesMatched() {
        return owedTo.values()
                .stream()
                .allMatch(v -> v.compareTo(new BigDecimal("-0.001")) >= 0 && v.compareTo(new BigDecimal("0.001")) <= 0);
    }

    private BigDecimal expenseAverage() {
        // Convention
        if (people.size() == 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal count = new BigDecimal(people.size());

        return people.stream()
                .map(Person::expenseTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(count, 3, RoundingMode.HALF_UP);
    }
}
