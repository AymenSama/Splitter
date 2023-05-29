package splitter;

import splitter.data.Person;
import splitter.data.finances.Transfer;
import splitter.interaction.PersonBuilder;
import splitter.splitter.ExpenseSplitter;

import java.util.List;

public class Start {

  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("Bitte geben sie Personen und ihre Ausgaben an, für die die Ausgaben aufgeteilt werden sollen.");
      return;
    }

    System.out.println("Willkommen bei Splitter\n");
    PersonBuilder builder = new PersonBuilder();
    List<Person> people = builder.build(args);

    ExpenseSplitter splitter = new ExpenseSplitter(people);
    List<Transfer> transfers = splitter.getTransfers();
    printTransfers(transfers);
  }

  private static void printTransfers(List<Transfer> transfers) {
    if (transfers.size() == 0) {
      System.out.println("Keine Überweisungen sind nötig.");
      return;
    }
    System.out.printf("%-10s | %-10s | %-10s\n\n", "Von", "An", "Betrag");
    transfers.forEach(
            t -> System.out.printf("%-10s | %-10s | %-10s\n", t.sender().getName(), t.receiver().getName(), t.value().getValue())
    );
  }
}
