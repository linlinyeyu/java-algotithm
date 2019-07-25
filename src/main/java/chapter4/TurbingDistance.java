package chapter4;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author ybliu
 * @version 1.0
 * @date 2019/7/25 16:46
 *
 * 描述
 * 某石油公司计划建造一条由东向西的主输油管道。该管道要穿过一个有n 口油井的油田。从每口油井都要有一条输油管道沿最短路经(或南或北)与主管道相连。如果给定n口油井的位置,即它们的x 坐标（东西向）和y 坐标（南北向）,应如何确定主管道的最优位置,即使各油井到主管道之间的输油管道长度总和最小的位置?
 *
 * 编程任务：
 * 给定n 口油井的位置,编程计算各油井到主管道之间的输油管道最小长度总和.
 *
 * 格式
 * 输入格式
 * 输入第1行是油井数n，1≤n≤10000。
 *
 * 接下来n行是油井的位置，每行2个整数x和y，-10000≤x，y≤10000。
 *
 * 输出格式
 * 输出第1行中的数是油井到主管道之间的输油管道最小长度总和。
 *
 * 样例1
 * 样例输入1
 * 5
 * 1 2
 * 2 2
 * 1 3
 * 3 -2
 * 3 3
 * Copy
 * 样例输出1
 * 6
 * Copy
 * 限制
 * 各个测试点1s
 */
public class TurbingDistance {
    public static void main(String args[]) {
        //思路是绝对值不等式，点映射到数轴，求y值中位数
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int[] data = new int[num];
        for(int i=0;i<num;i++) {
            int temp = scanner.nextInt();
            data[i] = scanner.nextInt();
        }
        Arrays.sort(data);
        int middle = data[num/2];
        int result = 0;
        for (int i = 0;i<num;i++) {
            result += Math.abs(data[i]-middle);
        }
        System.out.println(result);
    }
}
