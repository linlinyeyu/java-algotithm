package chapter4;

/**
 * 汉诺塔问题
 * @author linlinyeyu
 */
public class Hanoi {
    public void move(int i,String from,String to,String assist) {
        if (i==0) {
            return;
        }
        move(i - 1,from,assist,to);
        display(i,from,to);
        move(i - 1,assist,to,from);
    }

    public void display(int i,String from,String to) {
        System.out.println("第"+i+"块从"+from+"移到"+to);
    }

    public static void main(String args[]) {
        Hanoi hanoi = new Hanoi();
        hanoi.move(3,"a","b","c");
    }
}
