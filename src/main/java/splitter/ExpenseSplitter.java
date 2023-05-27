package splitter;

import splitter.data.Person;
import splitter.finances.Money;
import splitter.finances.Transfer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class ExpenseSplitter {
    public static List<Transfer> getTransfers(List<Person> people) {
        List<Transfer> transfers = new ArrayList<>();
        BigDecimal average = expenseAverage(people);

        Map<Person, BigDecimal> owedTo = new HashMap<>();
        people.forEach(p -> owedTo.put(p, p.expenseTotal().subtract(average)));

        while (!amountsMatched(owedTo)) {
            Person sender = getSender(owedTo);
            Person receiver = getReceiver(owedTo);
            BigDecimal toSend = toSend(sender, receiver, owedTo);
            Transfer transfer = new Transfer(sender, receiver, new Money(toSend));
            transfers.add(transfer);
        }


        return transfers;
    }

    private static Person getSender(Map<Person, BigDecimal> owedTo) {
        BigDecimal minValue = owedTo.values().stream()
                .min(Comparator.naturalOrder())
                .orElseThrow();

        return getPerson(owedTo, minValue);
    }

    private static Person getPerson(Map<Person, BigDecimal> owedTo, BigDecimal value) {
        return owedTo.entrySet().stream()
                .filter(e -> e.getValue().equals(value))
                .map(Map.Entry::getKey)
                .findAny()
                .orElseThrow();
    }

    private static Person getReceiver(Map<Person, BigDecimal> owedTo) {
        BigDecimal maxValue = owedTo.values().stream()
                .max(Comparator.naturalOrder())
                .orElseThrow();

        return getPerson(owedTo, maxValue);
    }

    private static BigDecimal toSend(Person sender, Person receiver, Map<Person, BigDecimal> owedTo) {
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

    private static boolean amountsMatched(Map<Person, BigDecimal> owed) {
        return owed.values()
                .stream()
                .allMatch(v -> v.compareTo(BigDecimal.ZERO) == 0);
    }
    private static BigDecimal expenseAverage(List<Person> people) {
        // Convention
        if (people.size() == 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal count = new BigDecimal(people.size());

        return people.stream()
                .map(Person::expenseTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(count, RoundingMode.HALF_UP);
    }
}
