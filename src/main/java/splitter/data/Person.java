package splitter.data;

import java.math.BigDecimal;

public class Person {
    private final String name;
    private final BigDecimal expense;
    public Person(String name, String expense) {
        this.name = name;
        this.expense = new BigDecimal(expense);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getExpense() {
        return expense;
    }
}
