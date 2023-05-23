package splitter.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Person {
    private final String name;
    private BigDecimal expense;
    public Person(String name, String expense) {
        if (name == null) {
            throw new IllegalArgumentException("Name can't be null");
        }
        if (expense == null) {
            throw new IllegalArgumentException("Expense can't be null");
        }
        this.name = name;
        this.expense = new BigDecimal(expense);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getExpense() {
        return expense.setScale(2, RoundingMode.HALF_UP);
    }

    public void addExpense(String expense) {
        this.expense = this.expense.add(new BigDecimal(expense));
    }
}
