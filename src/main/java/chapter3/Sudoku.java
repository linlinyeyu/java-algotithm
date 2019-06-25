package chapter3;

/**
 * 背景
 * XX学校风靡一款智力游戏，也就是数独（九宫格），先给你一个数独，并需要你验证是否符合规则。
 * <p>
 * 描述
 * 具体规则如下:
 * 每一行都用到1,2,3,4,5,6,7,8,9，位置不限，
 * 每一列都用到1,2,3,4,5,6,7,8,9，位置不限，
 * 每3×3的格子（共九个这样的格子）都用到1,2,3,4,5,6,7,8,9，位置不限，
 * 游戏的过程就是用1,2,3,4,5,6,7,8,9填充空白，并要求满足每行、每列、每个九宫格都用到1,2,3,4,5,6,7,8,9。
 * <p>
 * 如下是一个正确的数独:
 * 5 8 1 4 9 3 7 6 2
 * 9 6 3 7 1 2 5 8 4
 * 2 7 4 8 6 5 9 3 1
 * 1 2 9 5 4 6 3 7 8
 * 4 3 6 1 8 7 2 9 5
 * 7 5 8 3 2 9 1 4 6
 * 8 9 2 6 7 1 4 5 3
 * 6 1 5 9 3 4 8 2 7
 * 3 4 7 2 5 8 6 1 9
 * <p>
 * 格式
 * 输入格式
 * 输入n个数独，你来验证它是否违反规则.
 * 第一行为数独个数，第二行开始为第一个数独，之后为第二个，至第n个.
 * 注意！每个数独之间有一个回车隔开!
 * <p>
 * 输出格式
 * 若正确则输出”Right”若不正确则输出”Wrong” 输出一个换一行
 * <p>
 * 样例1
 * 样例输入1
 * 2
 * 5 8 1 4 9 3 7 6 2
 * 9 6 3 7 1 2 5 8 4
 * 2 7 4 8 6 5 9 3 1
 * 1 2 9 5 4 6 3 7 8
 * 4 3 6 1 8 7 2 9 5
 * 7 5 8 3 2 9 1 4 6
 * 8 9 2 6 7 1 4 5 3
 * 6 1 5 9 3 4 8 2 7
 * 3 4 7 2 5 8 6 1 9
 * <p>
 * 1 2 3 4 5 6 7 8 9
 * 2 3 4 5 6 7 8 9 1
 * 3 4 5 6 7 8 9 1 2
 * 4 5 6 7 8 9 1 2 3
 * 5 6 7 8 9 1 2 3 4
 * 6 7 8 9 1 2 3 4 5
 * 7 8 9 1 2 3 4 5 6
 * 8 9 1 2 3 4 5 6 7
 * 9 1 2 3 4 5 6 7 8
 * 样例输出1
 * Right
 * Wrong
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author linlinyeyu
 */
public class Sudoku {
    int[][] sudokus = new int[9][9];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sudokusNumber = Integer.valueOf(scanner.nextLine());
        for (int i = 1; i <= sudokusNumber; i++) {
            boolean flag = true;
            Sudoku sudoku = new Sudoku();
            int k = 0;
            List<String> numbers = new ArrayList<>();
            if (i != 1) {
                scanner.nextLine();
            }
            while (k<9) {
                numbers.add(scanner.nextLine());
                k++;
            }
            for (int m = 0;m<numbers.size();m++) {
                String[] values = numbers.get(m).split(" ");
                for (int n = 0;n<values.length;n++) {
                    sudoku.sudokus[m][n] = Integer.valueOf(values[n]);
                }
            }
            int rowsum = 0,columsum = 0,rowmulti = 1,columnmulti = 1;
            for (int row = 0;row < 9;row++) {
                for (int column = 0;column<9;column++) {
                    rowsum += sudoku.sudokus[row][column];
                    rowmulti *= sudoku.sudokus[row][column];
                }
                if (rowsum != 45 || rowmulti != 362880) {
                    flag = false;
                    break;
                }
                rowsum = 0;
                rowmulti = 1;
            }
            if (flag) {
                for (int column = 0;column < 9;column++) {
                    for (int row = 0;row < 9;row++) {
                        columsum += sudoku.sudokus[row][column];
                        columnmulti *= sudoku.sudokus[row][column];
                    }
                    if (columsum != 45 || columnmulti != 362880) {
                        flag = false;
                        break;
                    }
                    columsum = 0;
                    columnmulti = 1;
                }
            }

            if (flag) {
                int pos = 0;
                while (pos <= 6) {
                    int[] sum = {0,0,0};
                    int[] multi = {1,1,1};
                    for (int row = pos;row<pos+3;row++) {
                        for (int column = 0;column<3;column++) {
                            sum[0] += sudoku.sudokus[row][column];
                            multi[0] *= sudoku.sudokus[row][column];
                        }
                        for (int column = 3;column<6;column++) {
                            sum[1] += sudoku.sudokus[row][column];
                            multi[1] *= sudoku.sudokus[row][column];
                        }
                        for (int column = 6;column<9;column++) {
                            sum[2] += sudoku.sudokus[row][column];
                            multi[2] *= sudoku.sudokus[row][column];
                        }
                    }
                    for (int value:sum) {
                        if (value != 45) {
                            flag = false;
                            break;
                        }
                    }
                    for (int value:multi) {
                        if (value != 362880) {
                            flag = false;
                            break;
                        }
                    }
                    pos += 3;
                }
            }

            if (flag) {
                System.out.println("Right");
            } else {
                System.out.println("Wrong");
            }
        }
    }
}
