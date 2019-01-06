package com.mock.interviewingio;

/*
 1) Implement a binary tree class, so that you can construct arbitrary binary trees

 2) Implement a function, that takes as input a binary tree object (instance of what you built in 1),
    and then outputs true if the binary tree is "complete," false otherwise

 In a complete binary tree every level, except possibly the last, is completely filled, and all nodes
 in the last level are as far left as possible. It can have between 1 and 2^h nodes inclusive at the last level h.
 */

import java.util.*;

public class BinaryTreeIsComplete {

  static class Node {
    Node left;
    Node right;
    int val;

    Node(int val) {this.val = val;}

    void setLeft(Node left) { this.left = left; }
    void setRight(Node right) { this.right = right; }

    Node getLeft() { return this.left; }
    Node getRight() { return this.right; }
  }

  /*

        A
      /   \
     B     C
    /
   D


  A B C D null null null


        A
      /   \
     B     C
      \
       D

  A B C null D null null .........


        1
     B     C
   D   3 3   3
  G
        A
      /   \
     B     C
      \
        D

*/
  public static void main(String[] args) {

    Node a = new Node(10), b = new Node(5), c = new Node(3), d = new Node(1);

    a.setLeft(b);
    a.setRight(c);
    b.setLeft(d);

    BinaryTree bt = new BinaryTree(a);
    System.out.println("isComplete: " + bt.isComplete());
  }

  static class BinaryTree {
    Node root;

    BinaryTree(Node root) { this.root = root; }

    Node getRoot() { return this.root; }

    boolean isComplete() {
      if (root == null) {
        return false;
      }

      Deque<Node> queue = new LinkedList<Node>();
      queue.add(root);

      boolean notComplete = false;

      while (!queue.isEmpty()) {
        Node cur = queue.remove();
        if (cur != null) {
          if (notComplete) {
            return false;
          }
          if ((cur.getLeft() == null || cur.getRight() == null)) {
            notComplete = true;
          }
          queue.add(cur.getLeft());
          queue.add(cur.getRight());
        }
      }
      return true;
    }
  }
}
