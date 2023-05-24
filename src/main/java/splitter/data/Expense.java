package splitter.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

class Expense {
    private BigDecimal value;
    Expense(String expense) {
        this.value = formatExpense(expense);
    }

    private BigDecimal formatExpense(String expense) {
        BigDecimal value = new BigDecimal(expense);
        return value.setScale(2, RoundingMode.HALF_UP);
    }

    BigDecimal getValue() {
        return value;
    }

    void add(Expense other) {
        this.value = this.value.add(other.value);
    }
}
