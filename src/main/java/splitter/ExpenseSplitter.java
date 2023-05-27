package splitter;

import splitter.data.Person;
import splitter.finances.Money;
import splitter.finances.Transfer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

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
        BigDecimal minAmount = owedTo.values().stream()
                .min(Comparator.naturalOrder())
                .orElseThrow();

        Set<Person> qualifiedSenders = owedTo.entrySet().stream()
                .filter(e -> e.getValue().equals(minAmount))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        return qualifiedSenders.stream()
                .findAny()
                .orElseThrow();
    }

    private static Person getReceiver(Map<Person, BigDecimal> owedTo) {
        BigDecimal maxAmount = owedTo.values().stream()
                .max(Comparator.naturalOrder())
                .orElseThrow();

        Set<Person> qualifiedReceivers = owedTo.entrySet().stream()
                .filter(e -> e.getValue().equals(maxAmount))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        return qualifiedReceivers.stream()
                .findAny()
                .orElseThrow();
    }

    private static BigDecimal toSend(Person sender, Person receiver, Map<Person, BigDecimal> owedTo) {
        BigDecimal x = owedTo.get(sender);
        BigDecimal y = owedTo.get(receiver);
        if (y.compareTo(x.abs()) >= 0) {
            owedTo.replace(sender, BigDecimal.ZERO);
            owedTo.replace(receiver, y.add(x));
            return x.abs();
        }
        owedTo.replace(sender, x.add(y));
        owedTo.replace(receiver, BigDecimal.ZERO);
        return y;
    }

    private static boolean amountsMatched(Map<Person, BigDecimal> owed) {
        return owed.values()
                .stream()
                .allMatch(v -> v.compareTo(BigDecimal.ZERO) == 0);
    }
    private static BigDecimal expenseAverage(List<Person> people) {
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
