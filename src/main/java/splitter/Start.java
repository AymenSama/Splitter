package splitter;

import splitter.data.Person;
import splitter.data.finances.Transfer;
import splitter.interaction.PersonBuilder;
import splitter.splitter.ExpenseSplitter;

import java.util.List;

public class Start {

  /**
   * @param args Names and their expenses.
   *             Example: Willy 320 Tim 40 Gaby 48 Tim 100 Karl
   *             Each name is followed by its expense value. If a name is not followed by a number,
   *             the program implicitly assumes the value 0 for its expense. So "Karl" and "Karl 0"
   *             are considered identical in the above example.
   */
  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("Please provide individuals and their expenses for which the expenses should be split.");
      return;
    }

    System.out.println("Welcome to Splitter!\n");
    PersonBuilder builder = new PersonBuilder();
    List<Person> people = builder.build(args);

    ExpenseSplitter splitter = new ExpenseSplitter(people);
    List<Transfer> transfers = splitter.getTransfers();
    printTransfers(transfers);
  }

  private static void printTransfers(List<Transfer> transfers) {
    if (transfers.size() == 0) {
      System.out.println("No transfers are needed.");
      return;
    }

    int nSpaces = 10;
    String format = "%-" + nSpaces + "s | %-" + nSpaces + "s | %-" + nSpaces + "s\n";

    System.out.printf(format + "\n", "From", "To", "Amount");
    transfers.forEach(t->t.print(format));
  }

}
