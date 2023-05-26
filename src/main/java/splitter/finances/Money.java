package splitter.finances;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
    private BigDecimal value;
     public Money(String money) {
        if (money == null) {
            throw new IllegalArgumentException("Money can't be null");
        }
        this.value = formatMoney(money);
    }

    private BigDecimal formatMoney(String money) {
        BigDecimal value = new BigDecimal(money);

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Money can't be negative");
        }
        if (value.scale() > 2) {
            System.err.println("WARNING: The decimal part for " + money + " exceeds 2 digits, the result will be rounded accordingly");
        }
        return value.setScale(2, RoundingMode.HALF_UP);
    }

     public BigDecimal getValue() {
        return value;
    }

     public void add(Money other) {
        this.value = this.value.add(other.value);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value.equals(money.value);
    }

}
