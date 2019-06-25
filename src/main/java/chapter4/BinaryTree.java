package chapter4;

import java.util.Stack;

/**
 * @author ybliu
 * @version 1.0
 * @date 2019/6/14 14:06
 */
public class BinaryTree {
    class Node{
        String value;
        Node leftTree;
        Node rightTree;

        Node value(String value) {
            this.value = value;
            return this;
        }
    }
    private Node root;

    /**
     * 前序遍历
     * @param node
     */
    public void preOrderTraversal(Node node) {
        display(node);
        if (node.leftTree != null) {
            preOrderTraversal(node.leftTree);
        }
        if (node.rightTree != null) {
            preOrderTraversal(node.rightTree);
        }
    }

    /**
     * 中序遍历
     * @param node
     */
    public void interTraversal(Node node) {
        if (node.leftTree != null) {
            interTraversal(node.leftTree);
        }
        display(node);
        if (node.rightTree != null) {
            interTraversal(node.rightTree);
        }
    }

    /**
     * 后序遍历
     * @param node
     */
    public void postTraversal(Node node) {
        if (node.leftTree != null) {
            postTraversal(node.leftTree);
        }
        if (node.rightTree != null) {
            postTraversal(node.rightTree);
        }
        display(node);
    }

    /**
     * 深度优先遍历
     */
    public void depthTraversal(Node node, Stack stack) {
        if (node.leftTree != null) {
            display(node);
            depthTraversal(node,stack);
        }
        if (node.rightTree != null) {

        }
    }

    public void display(Node node) {
        System.out.print(node.value);
    }

    public static void main(String args[]) {
        BinaryTree binaryTree = new BinaryTree();
        Node node = binaryTree.new Node().value("A");
        node.leftTree = binaryTree.new Node().value("B");
        node.leftTree.rightTree = binaryTree.new Node().value("C");
        node.leftTree.rightTree.leftTree = binaryTree.new Node().value("D");
        node.rightTree = binaryTree.new Node().value("E");
        node.rightTree.rightTree = binaryTree.new Node().value("F");
        node.rightTree.rightTree.leftTree = binaryTree.new Node().value("G");
        node.rightTree.rightTree.leftTree.leftTree = binaryTree.new Node().value("H");
        node.rightTree.rightTree.leftTree.rightTree = binaryTree.new Node().value("K");

        binaryTree.preOrderTraversal(node);
    }
}
