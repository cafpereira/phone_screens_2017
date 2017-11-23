package com.example.uber;

import java.util.*;

class Node {
  public Node (int value) {
    this.value = value;
  }
  int value;
  Node left;
  Node right;
}

/**
 * Check if root node points to a valid BST
 */
public class ValidBST {

  public static boolean validBST(Node root) {
    int lower = Integer.MIN_VALUE;
    int upper = Integer.MAX_VALUE;

    // TODO check for null

    return validBST(root, lower, upper);
  }

  public static boolean validBST(Node node, int lower, int upper) {
    if (node == null) {
      return true;
    }

    if (lower <= node.value && node.value < upper) {
      return validBST(node.left, lower, node.value) && validBST(node.right, node.value, upper);
    }
    else {
      return false;
    }
  }

  public static void main (String[] args) {
    System.out.println("hello world");
    Node A = new Node(5);
    Node B = new Node(2);
    Node C = new Node(9);
    A.left = B;
    A.right = C;

    Node D = new Node(1);
    Node E = new Node(3);
    B.left = D;
    B.right = E;

    System.out.println("validBST(root) = " + validBST(A) + " # Expected: true");
  }
}
