package com.example.refdash.interview02Ravi;

public class ReconstructTree {

  public static Node rebuild (int[] IN, int[] POS) {
    return rebuildHelper(0, POS.length - 1, 0, IN.length - 1, IN, POS);
  }

  public static Node rebuildHelper(int starPos, int endPos, int starIn, int endIn, int[] IN, int[] POS) {
    if (starPos > endPos || starIn > endIn) {
      return null;
    }

    int rootValue = POS[endPos];
    Node n = new Node(rootValue);

    int rootPreIdx = search(IN, starIn, endIn, rootValue);
    int rightSize = endIn - rootPreIdx;

    n.left = rebuildHelper(starPos, endPos - rightSize - 1, starIn, rootPreIdx - 1, IN, POS);
    n.right = rebuildHelper(endPos - rightSize, endPos - 1, rootPreIdx + 1, endIn, IN, POS);

    return n;
  }

  private static int search(int[] array, int start, int end, int value) {
    for (int i = start; i <= end; i++) {
      if (array[i] == value) {
        return i;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    /* Input tree:
               1
             /   \
            2     3
             \     \
             4      5
       IN:  2,4,1,3,5
       POS: 4,2,5,3,1
    */

    Node tree = rebuild(new int[] {2,4,1,3,5}, new int[] {4,2,5,3,1});
    System.out.println("root: " + tree + ", left: " + tree.left + ", right: " + tree.right);
  }
}

class Node {
  final int value;
  Node left;
  Node right;

  Node(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.valueOf(this.value);
  }
}
