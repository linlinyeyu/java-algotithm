package chapter4;

import java.util.Arrays;

/**
 * @author ybliu
 * @version 1.0
 * @date 2019/6/26 13:43
 * 实现大顶堆小顶堆排序
 */
public class HeapSort {
    public void createHeap(int[] tree) {
        for (int i = tree.length/2-1;i>0;i--) {

        }
        Arrays.stream(tree).forEach(System.out::println);
    }

    public void swap(int[] tree,int i) {
        if (2*i+1 > tree.length) {
            return;
        }
        int parent = tree[i];
        if (parent <= tree[2*i+1] || parent <= tree[2*i+2]) {
            int swap = max(tree,i);
            tree[i] = tree[swap];
            tree[swap] = parent;
        }
    }

    public int max(int[] tree,int i) {
        if (tree[2*i+1] >= tree[2*i+2]) {
            return 2*i+1;
        } else {
            return 2*i+2;
        }
    }

    public static void main(String args[]) {
        HeapSort heapSort = new HeapSort();
        heapSort.createHeap(new int[]{3,5,1,21,5,213,55,64,70,21,56,62,61,32,90});
    }
}
