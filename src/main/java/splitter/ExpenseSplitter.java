package splitter;

import splitter.data.Person;
import splitter.finances.Money;
import splitter.finances.Transfer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExpenseSplitter {
    public static List<Transfer> getTransfers(List<Person> people) {
        if (people.size() == 0 || people.size() == 1) {
            return List.of();
        }

        List<Transfer> transfers = new ArrayList<>();
        BigDecimal average = expenseAverage(people);

        Person sender = people.get(0);
        Person receiver = people.get(1);

        if (sender.expenseTotal().compareTo(receiver.expenseTotal()) > 0) {
            Person buffer = sender;
            sender = receiver;
            receiver = buffer;
        }

        Money owedMoney = new Money(receiver.expenseTotal().subtract(average));

        Transfer transfer = new Transfer(sender, receiver, owedMoney);
        transfers.add(transfer);

        return transfers;
    }
    private static BigDecimal expenseAverage(List<Person> people) {
        BigDecimal count = new BigDecimal(people.size());

        return people.stream()
                .map(Person::expenseTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(count);
    }
}
