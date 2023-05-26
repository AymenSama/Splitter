package splitter.finances;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
    private BigDecimal value;
     public Money(String money) {
        if (money == null) {
            throw new IllegalArgumentException("Expense can't be null");
        }
        this.value = formatExpense(money);
    }

    private BigDecimal formatExpense(String expense) {
        BigDecimal value = new BigDecimal(expense);
        if (value.scale() > 2) {
            System.err.println("WARNING: The decimal part for " + expense + " exceeds 2 digits, the result will be rounded accordingly");
        }
        return value.setScale(2, RoundingMode.HALF_UP);
    }

     public BigDecimal getValue() {
        return value;
    }

     public void add(Money other) {
        this.value = this.value.add(other.value);
    }
}
