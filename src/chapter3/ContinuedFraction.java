package chapter3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 背景
 * ...
 *
 * ...:“这个简单...我们还是去刚才的海边呗...”
 * ...:"其实今晚...我是有一定要完成的事情的..." .,
 * 威尼斯真的是一个美丽的城市...很小的时候我就听说这个地方..
 *
 * 这一天..从贝鲁特归来的商队..除了布匹和香辛料...还带来的东方的数字....
 * 也有人曾经讨论过它们的历史...
 * 只是很长时间这些都不被那些数学家们所重视..
 *
 * 人们怀着敬畏的心情..小心的审视着这些新奇的东西...
 * 而它们..给生活在这片土地上的人们所带来的..是很大的帮助..
 * ...
 *
 * 描述
 * 写一个程序...可以实现在连分数和分数之间的互相转换...
 *
 * 样例1
 * 样例输入1
 * [2;3,7]
 * 51/22
 * 样例输出1
 * 51/22
 * [2;3,7]
 */

/**
 * 连分数与分数互相转化
 * @author linlinyeyu
 */
public class ContinuedFraction {
    List<Long> continueNumber = new ArrayList<>();
    class Fraction{
        long numerator = 0;
        long denominator;
    }

    /**
     * 分数转连分数
     * @param fraction
     */
    public void fractionToContinue(Fraction fraction) {
        BigDecimal[] value = BigDecimal.valueOf(fraction.numerator).divideAndRemainder(BigDecimal.valueOf(fraction.denominator));
        continueNumber.add(value[0].longValue());
        if (Long.compare(0,value[1].longValue()) == 0) {
            return;
        }
        fraction.numerator = fraction.denominator;
        fraction.denominator = value[1].longValue();
        fractionToContinue(fraction);
    }

    /**
     * 连分数转分数
     * @param fraction
     */
    public void continueToFraction(Fraction fraction) {
        BigDecimal nemNumberator = BigDecimal.valueOf(fraction.denominator).multiply(BigDecimal.valueOf(continueNumber.get(0))).add(BigDecimal.valueOf(fraction.numerator));
        continueNumber.remove(0);
        fraction.numerator = fraction.denominator;
        fraction.denominator = nemNumberator.longValue();
        if (continueNumber.isEmpty()) {
            long temp = fraction.numerator;
            fraction.numerator = fraction.denominator;
            fraction.denominator = temp;
            return;
        }
        continueToFraction(fraction);
    }

    /**
     * 约分
     * @param numberator
     * @param denominator
     */
    public long reductionFraction(long numberator,long denominator) {
        BigDecimal remainder = BigDecimal.valueOf(numberator).remainder(BigDecimal.valueOf(denominator));
        long minvalue = Long.min(numberator,denominator);
        if (remainder.longValue() != 0) {
            return reductionFraction(minvalue,remainder.longValue());
        } else {
            return minvalue;
        }
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String numberStr = scanner.nextLine();
            ContinuedFraction continuedFraction = new ContinuedFraction();
            ContinuedFraction.Fraction fraction = continuedFraction.new Fraction();
            if (numberStr.contains("[")) {
                numberStr = numberStr.replaceAll("\\[|\\]","");
                if (numberStr.contains(";")) {
                    String firstValue = numberStr.substring(0,numberStr.indexOf(';'));
                    String remainValue = numberStr.substring(numberStr.indexOf(';') + 1);
                    String[] values = remainValue.split(",");
                    continuedFraction.continueNumber.add(Long.valueOf(firstValue));
                    for (String value:values) {
                        continuedFraction.continueNumber.add(Long.valueOf(value));
                    }
                } else {
                    long value = Long.valueOf(numberStr);
                    continuedFraction.continueNumber.add(value);
                }
                Collections.reverse(continuedFraction.continueNumber);
                fraction.numerator = 0;
                fraction.denominator = 1;
                continuedFraction.continueToFraction(fraction);
                long commonDividor = continuedFraction.reductionFraction(fraction.numerator,fraction.denominator);
                fraction.numerator = fraction.numerator/commonDividor;
                fraction.denominator = fraction.denominator/commonDividor;
                if (fraction.numerator == 1) {
                    System.out.println(fraction.numerator);
                } else {
                    System.out.println(fraction.numerator+"/"+fraction.denominator);
                }
            } else if (numberStr.contains("/")){
                String[] values = numberStr.split("/");
                fraction.numerator = Long.valueOf(values[0]);
                fraction.denominator = Long.valueOf(values[1]);
                continuedFraction.fractionToContinue(fraction);
                StringBuilder result = new StringBuilder("[");
                result.append(continuedFraction.continueNumber.get(0)).append(";");
                if (continuedFraction.continueNumber.size() == 1) {
                    result = new StringBuilder(result.substring(0, result.length() - 1));
                    result.append("]");
                    System.out.println(result);
                    return;
                }
                continuedFraction.continueNumber.remove(0);
                for (long i: continuedFraction.continueNumber) {
                    result.append(i).append(",");
                }
                result = new StringBuilder(result.substring(0, result.length() - 1));
                result.append("]");
                System.out.println(result);
            }
        }
    }
}
