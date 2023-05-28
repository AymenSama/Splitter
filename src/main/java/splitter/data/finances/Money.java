package splitter.data.finances;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.util.Objects.requireNonNull;

public class Money {
    private BigDecimal value;
     public Money(BigDecimal money) {
        if (money == null) {
            throw new IllegalArgumentException("Money can't be null");
        }
        this.value = formatMoney(money);
    }

    public Money(String money) {
        this(new BigDecimal(requireNonNull(money, "Money can't be null")));
    }

    private BigDecimal formatMoney(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Money can't be negative");
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
