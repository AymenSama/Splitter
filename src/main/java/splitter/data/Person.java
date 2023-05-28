package splitter.data;

import splitter.data.finances.Money;

import java.math.BigDecimal;

public class Person {
    private final String name;
    private final Money expense;
    public Person(String name, String expense) {
        if (name == null) {
            throw new IllegalArgumentException("Name can't be null");
        }
        BigDecimal value = getValue(expense);
        this.name = name;
        this.expense = new Money(value);
    }
    public Person(String name) {
        this(name, "0");
    }

    private static BigDecimal getValue(String expense) {
        BigDecimal value = new BigDecimal(expense);
        if (value.scale() > 2) {
            System.err.println("WARNING: The decimal part for " + value + " exceeds 2 digits, the result will be rounded accordingly");
        }
        return value;
    }


    public String getName() {
        return name;
    }

    public BigDecimal expenseTotal() {
        return expense.getValue();
    }

    public void addExpense(String expense) {
        this.expense.add(new Money(expense));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name) && expenseTotal().equals(person.expenseTotal());
    }
}
