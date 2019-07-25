package chapter4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 描述
 * 元旦快到了，校学生会让乐乐负责新年晚会的纪念品发放工作。为使得参加晚会的同学所获得 的纪念品价值相对均衡，他要把购来的纪念品根据价格进行分组，但每组最多只能包括两件纪念品， 并且每组纪念品的价格之和不能超过一个给定的整数。为了保证在尽量短的时间内发完所有纪念品，乐乐希望分组的数目最少。
 *
 * 你的任务是写一个程序，找出所有分组方案中分组数最少的一种，输出最少的分组数目。
 *
 * 【限制】
 *
 * 50%的数据满足: 1 <=n <= 15
 *
 * 100%的数据满足: 1 <= n <= 30000， 80 <= W <= 200
 *
 * 格式
 * 输入格式
 * 第1行包括一个整数w，为每组纪念品价格之和的上限= 第2行为一个整数n，表示购来的纪念品的总件数G
 *
 * 第3-n+2行每行包含一个正整数Pi (5 <= Pi <= w3)w表示所对应纪念品的价格。
 *
 * 输出格式
 * 仅1行，包含一个整数， ep最少的分组数目合
 *
 * 样例1
 * 样例输入1
 * 100
 * 9
 * 90
 * 20
 * 20
 * 30
 * 50
 * 60
 * 70
 * 80
 * 90
 * Copy
 * 样例输出1
 * 6
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
