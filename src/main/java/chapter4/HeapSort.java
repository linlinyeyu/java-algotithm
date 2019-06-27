package chapter4;

import java.util.Arrays;

/**
 * @author ybliu
 * @version 1.0
 * @date 2019/6/26 13:43
 * 实现大顶堆小顶堆排序
 */
public class HeapSort {
    /**
     * 从小到大排序
     * @param tree
     */
    public void createHeap(int[] tree) {
        for (int i = tree.length/2-1;i>=0;i--) {
            createHeap(tree,tree.length-1);
        }
        for (int i = tree.length - 1;i>=0;i--) {
            swap(tree,i);
            createHeap(tree,i-1);
        }
        Arrays.stream(tree).forEach(System.out::println);
    }

    private void swap(int[] tree,int n) {
        int temp = tree[n];
        tree[n] = tree[0];
        tree[0] = temp;
    }

    /**
     * 建堆
     * @param tree
     * @param length
     */
    public void createHeap(int[] tree,int length) {
        for (int i = length/2-1;i>=0;i--) {
            adjustHeap(tree,i,length);
        }
    }

    /**
     * 调整堆
     * @param tree
     * @param i
     * @param length
     */
    public void adjustHeap(int[] tree,int i,int length) {
        if (2*i+1 > length) {
            return;
        }
        int parent = tree[i];
        if (parent <= tree[2*i+1] || parent <= tree[2*i+2]) {
            int swap = max(tree,i,length);
            tree[i] = tree[swap];
            tree[swap] = parent;
            adjustHeap(tree,swap,length);
        }
    }

    public int max(int[] tree,int i,int length) {
        if (2*i+2 > length) {
            return 2*i+1;
        }
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
