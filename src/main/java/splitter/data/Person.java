package splitter.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Person {
    private final String name;
    private final BigDecimal expense;
    public Person(String name, String expense) {
        this.name = name;
        this.expense = new BigDecimal(expense).setScale(2, RoundingMode.HALF_UP);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getExpense() {
        return expense;
    }
}
