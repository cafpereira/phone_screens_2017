package com.companies.google;

import java.util.*;

public class FixBinaryTrees {

  /*
    Question:
    Given an binary tree, write a function that will determine
    if the tree is valid or not.
                      Example:
    (invalid)                          (valid)
       A                                  A
     /   \        remove edge(C,D)      /   \
    B    C          ==========>        B    C
     \  /                               \
      D                                  D
  */
  public static boolean isValid(Node root) {
    if (root == null) {
      return true;
    }
    if (root.visited) {
      return false;
    }
    root.visited = true;
    return isValid(root.left) && isValid(root.right);
  }

  /*
    Follow-up question:
    Provide a function that will fix the invalid links and return a valid tree
  */

  public static void fixTree(Node root) {
    fixTree(null, root);
  }

  public static void fixTree(Node from, Node root) {
    if (root == null) {
      return;
    }
    if (root.visited) {
      if (root.isLeftChildOf(from)) {
        from.left = null;
      }
      else {
        from.right = null;
      }
    } else {
      root.visited = true;
      fixTree(root, root.left);
      fixTree(root, root.right);
    }
  }

  static class Node {
    String value;
    Node left;
    Node right;
    boolean visited;

    public Node (String value) {
      this.value = value;
    }

    public boolean isLeftChildOf(Node node) {
      if (node == null) {
        System.out.println("debug");
      }
      if (node.left != null && node.left.value.equals(this.value)) {
        return true;
      }
      return false;
    }

    @Override
    public String toString() {
      return "[" + this.value + "]";
    }
  }

  static List<Node> treeNodes;

  public static void main(String[] args) {
    Node tree = badTreeV1();
    System.out.println("isValid(tree) = " + isValid(tree) + " # Expected: false");
    resetTree();
    System.out.println("fixTree(tree) ..."); fixTree(tree);
    resetTree();
    System.out.println("isValid(tree) = " + isValid(tree) + " # Expected: true");
  }

  private static void resetTree() {
    for (Node n : treeNodes){
      n.visited = false;
    }
  }

  private static Node badTreeV1() {
    Node A = new Node("A");
    Node B = new Node("B");
    Node C = new Node("C");
    Node D = new Node("D");

    A.left = B; A.right = C;

    // Invalid tree, D has two parents (B and C)
    B.right = D;
    C.left = D;

    FixBinaryTrees.treeNodes = Arrays.asList(A, B, C, D);
    return A;
  }
}
