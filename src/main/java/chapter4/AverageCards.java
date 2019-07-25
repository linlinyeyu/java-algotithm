package chapter4;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 描述
 * 有 N 堆纸牌，编号分别为 1，2，…, N。每堆上有若干张，但纸牌总数必为 N 的倍数。可以在任一堆上取若于张纸牌，然后移动。
 *
 * 移牌规则为：在编号为 1 堆上取的纸牌，只能移到编号为 2 的堆上；在编号为 N 的堆上取的纸牌，只能移到编号为 N-1 的堆上；其他堆上取的纸牌，可以移到相邻左边或右边的堆上。
 *
 * 现在要求找出一种移动方法，用最少的移动次数使每堆上纸牌数都一样多。
 *
 * 例如 N=4，4 堆纸牌数分别为：
 * ①　9　②　8　③　17　④　6
 * 移动3次可达到目的：
 * 从 ③ 取 4 张牌放到 ④ （9 8 13 10） -> 从 ③ 取 3 张牌放到 ②（9 11 10 10）-> 从 ② 取 1 张牌放到①（10 10 10 10）。
 *
 * 格式
 * 输入格式
 * N（N 堆纸牌，1 <= N <= 100）
 * A1 A2 … An （N 堆纸牌，每堆纸牌初始数，l<= Ai <=10000）
 *
 * 输出格式
 * 所有堆均达到相等时的最少移动次数。
 *
 * 样例1
 * 样例输入1
 * 4
 * 9 8 17 6
 * Copy
 * 样例输出1
 * 3
 * @author ybliu
 * @version 1.0
 * @date 2019/7/12 9:41
 * 贪心算法
 */
public class AverageCards {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int[] nums = new int[count];
        for (int i = 0;i<count;i++) {
            nums[i] = scanner.nextInt();
        }
        int avg = Arrays.stream(nums).sum()/nums.length;
        int[] avgs = Arrays.stream(nums).map(n -> n - avg).toArray();
        int result = 0;
        for (int i = 0;i<avgs.length-1;i++) {
            if (avgs[i] != 0) {
                avgs[i+1] += avgs[i];
                result ++;
                avgs[i] = 0;
            }
        }
        System.out.println(result);
    }
}
