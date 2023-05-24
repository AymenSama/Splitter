package splitter.data;

import java.math.BigDecimal;

public class Person {
    private final String name;
    private final Expense expense;
    public Person(String name, String expense) {
        if (name == null) {
            throw new IllegalArgumentException("Name can't be null");
        }
        this.name = name;
        this.expense = new Expense(expense);
    }

    public Person(String name) {
        this(name, "0");
    }


    public String getName() {
        return name;
    }

    public BigDecimal getExpenseTotal() {
        return expense.getValue();
    }

    public void addExpense(String expense) {
        this.expense.add(new Expense(expense));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name) && getExpenseTotal().equals(person.getExpenseTotal());
    }
}
