package splitter.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

class Money {
    private BigDecimal value;
    Money(String expense) {
        if (expense == null) {
            throw new IllegalArgumentException("Expense can't be null");
        }
        this.value = formatExpense(expense);
    }

    private BigDecimal formatExpense(String expense) {
        BigDecimal value = new BigDecimal(expense);
        if (value.scale() > 2) {
            System.err.println("WARNING: The decimal part for " + expense + " exceeds 2 digits, the result will be rounded accordingly");
        }
        return value.setScale(2, RoundingMode.HALF_UP);
    }

    BigDecimal getValue() {
        return value;
    }

    void add(Money other) {
        this.value = this.value.add(other.value);
    }
}
