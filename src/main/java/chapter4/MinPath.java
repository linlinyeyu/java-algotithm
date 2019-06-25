package chapter4;

/**
 * 最短路径问题
 * @author linlinyeyu
 */
public class MinPath {
    int[][] matrix;
    int[][] steps;
    public int calculateMinPath(int row,int col) {
        //如果为起始位置
        if (row == 0 && col == 0) {
            steps[row][col] = matrix[row][col];
            return steps[row][col];
        }
        if (row > 0 && steps[row-1][col] == 0) {
            steps[row-1][col] = calculateMinPath(row-1,col);
        }
        if (col > 0 && steps[row][col-1] == 0) {
            steps[row][col-1] = calculateMinPath(row,col-1);
        }
        //如果为第一行
        if (row == 0 && col > 0) {
            steps[row][col] = steps[row][col-1] + matrix[row][col];
        } else if (col == 0 && row > 0) {
            steps[row][col] = steps[row-1][col] + matrix[row][col];
        } else {
            steps[row][col] = matrix[row][col] + steps[row][col-1] >= steps[row-1][col] ? steps[row-1][col] : steps[row][col-1];
        }
        return steps[row][col];
    }

    public static void main(String args[]) {
        MinPath minPath = new MinPath();
        minPath.matrix = new int[][]{{1, 4, 5, 6, 2}, {2, 4, 12, 5, 2},{6,8,2,1,5},{6,3,6,2,7},{1,2,1,5,1},{3,2,5,1,6}};
        minPath.steps = new int[6][5];
        minPath.calculateMinPath(4,3);
        for (int i = 0;i<5;i++) {
            for (int j = 0;j<4;j++) {
                System.out.println("到达steps["+i+"]["+j+"]的最大路径:"+minPath.steps[i][j]);
            }
        }
    }
}
