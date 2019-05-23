package test;

import java.math.BigDecimal;

public class Test {
    public static void main(String args[]) {
        BigDecimal[] values = BigDecimal.valueOf(169437).divideAndRemainder(BigDecimal.valueOf(98517));
        System.out.println(values[0]+","+values[1]);
    }
}
