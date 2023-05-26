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
        BigDecimal count = new BigDecimal(people.size());

        BigDecimal average = people.stream()
                .map(Person::getExpenseTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(count);

        Person sender = people.get(0);
        Person receiver = people.get(1);

        if (sender.getExpenseTotal().compareTo(receiver.getExpenseTotal()) > 0) {
            Person buffer = sender;
            sender = receiver;
            receiver = buffer;
        }

        Money owedMoney = new Money(receiver.getExpenseTotal().subtract(average));

        Transfer transfer = new Transfer(sender, receiver, owedMoney);
        transfers.add(transfer);

        return transfers;
    }
}
