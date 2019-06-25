package chapter4;

import javafx.scene.input.Mnemonic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author linlinyeyu
 */
public class MinGroup {
    public int countGroup(List<Integer> prices,int count,int limit) {
        int i = 0,j = prices.size() - 1;
        while (i <= j) {
            if (prices.get(i) + prices.get(j) <= limit) {
                i++;
                j--;
                count++;
            } else if (prices.get(i) <= limit){
                i++;
                count++;
            } else {
                i++;
            }
        }
        return count;
    }
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int limit = scanner.nextInt();
        int number = scanner.nextInt();
        List<Integer> prices = new ArrayList<>();
        for (int i = 0;i< number;i++) {
            prices.add(scanner.nextInt());
        }
        prices.sort((a,b) -> a > b? -1: (Integer.compare(a,b) == 0 ? 0 : 1));
        MinGroup minGroup = new MinGroup();
        int count = minGroup.countGroup(prices,0,limit);
        System.out.println(count);
    }
}
