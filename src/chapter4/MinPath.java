package chapter4;

/**
 * 最短路径问题
 * @author linlinyeyu
 */
public class MinPath {
    int[][] matrix;
    public void calculateMinPath(int[][] steps,int row,int col) {
        //如果为起始位置
        if (row == 0 && col == 0) {
            steps[row][col] = matrix[row][col];
            return;
        }
        if (row > 0 && steps[row-1][col] == 0) {
            calculateMinPath(steps,row-1,col);
        }
        if (col > 0 && steps[row][col-1] == 0) {
            calculateMinPath(steps,row,col-1);
        }
        //如果为第一行
        if (row == 0 && col > 0) {
            steps[row][col] = steps[row][col-1] + matrix[row][col];
        }
        //如果为第一列
        if (col == 0 && row > 0) {
            steps[row][col] = steps[row-1][col] + matrix[row][col];
        }
        if (row >= 1 && col >= 1) {
            steps[row][col] = matrix[row][col] + steps[row][col-1] >= steps[row-1][col] ? steps[row-1][col] : steps[row][col-1];
        }
    }
    public static void main(String args[]) {
        MinPath minPath = new MinPath();
        minPath.matrix = new int[][]{{1, 4, 5, 6, 2}, {2, 4, 12, 5, 2},{6,8,2,1,5},{6,3,6,2,7},{1,2,1,5,1},{3,2,5,1,6}};
        int[][] steps = new int[6][5];
        minPath.calculateMinPath(steps,4,3);
        for (int i = 0;i<5;i++) {
            for (int j = 0;j<4;j++) {
                System.out.println("到达steps["+i+"]["+j+"]的最大路径:"+steps[i][j]);
            }
        }
    }
}
