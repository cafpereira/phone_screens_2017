package com.companies.pandora;

import java.util.Stack;

/**
 * Perform a preorder traversal of a binary search tree, printing the value
 * of each node.
 *
 * Example Binary Tree
 *            100
 *           /   \
 *        50      150
 *       /\        /\
 *     25  75   125  175
 *              /
 *            110
 *
 *  Solution would print: 100, 50, 25, 75, 150, 125, 110, 175
 */
class BinaryTreeHelper {
  public void traverse(Node n) {
    if (n == null) {
      return;
    }

    Stack<Node> stack = new Stack<>();
    stack.push(n);

    while (!stack.isEmpty()) {
      Node cur = stack.pop();
      System.out.println(cur.value);
      if (cur.right != null) {
        stack.push(cur.right);
      }
      if (cur.left != null) {
        stack.push(cur.left);
      }
    }
  }
}

class Solution {
  public static void main(String[] args) {
    Node root = BinaryTreeHelperAsst.getTestTree();
    BinaryTreeHelper helper = new BinaryTreeHelper();
    helper.traverse(root);
  }
}

class Node {
  public Node left;
  public Node right;
  public int value;
}

class BinaryTreeHelperAsst {
  public static Node getTestTree() {
    Node root = new Node();
    root.value = 100;
    root.left = new Node();
    root.left.value = 50;
    root.right = new Node();
    root.right.value = 150;
    root.left.left = new Node();
    root.left.left.value = 25;
    root.left.right = new Node();
    root.left.right.value = 75;
    root.right.left = new Node();
    root.right.left.value = 125;
    root.right.right = new Node();
    root.right.right.value = 175;
    root.right.left.left = new Node();
    root.right.left.left.value = 110;
    return root;
  }
}