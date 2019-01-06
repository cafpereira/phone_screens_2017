package com.companies.amazon.goodreads;

import java.util.HashMap;
import java.util.Map;

/**
 *
 <p>A binary search tree (BST) is defined as a binary tree in which each node satisfies the property such that its value is larger than the value of every node in its left subtree, and less than or equal to the value of every node in its right subtree. The distance between two values in a binary search tree is the minimum number of edges traversed to reach from one value to the other.</p>
 <p>Given a list of <em>n</em> unique integers, construct a BST by inserting each integer in the given order without rebalancing the tree. Then, find the distance between the two given nodes, <em>node1</em> and <em>node2</em>, of the BST. In case, either <em>node1</em> or <em>node2</em> is not present in the tree, return -1.</p><p><strong>Input</strong><br>The input to the function/method consists of four arguments - <em>values</em>, representing a list of integers;&nbsp;<em>n</em>, an integer representing the number of elements in the list;&nbsp;<em>node1</em>, an integer representing the first node and&nbsp;<em>node2</em>, an integer representing the second node.</p>
 <p><strong>Output</strong><br>Return an integer representing the distance between <em>node1</em> and <em>node2</em>, else return -1, if either <em>node1</em> or <em>node2</em> is not present in the tree.</p>
 <p><strong>Constraints</strong><br>0 &lt; <em>n</em>&nbsp;&lt; 2<sup>31</sup> <br>0 ≤ <em>values[i]</em> &lt; 2<sup>31</sup> <br>0 ≤ i &lt; <em>n</em></p>
 <p><strong>Example</strong><br> Input:<br> <em>values</em> = [5, 6, 3, 1, 2, 4], n = 6,&nbsp;<em>node1</em> = 2,&nbsp;<em>node2</em> = 4</p>
 <p>Output:<br>3</p>
 <p>Explanation:<br>For the list <em>values</em> = [5, 6, 3, 1, 2, 4], the tree is given as below:</p>
 <p>&nbsp; &nbsp; &nbsp; &nbsp;5<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;\<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;\<br>&nbsp;&nbsp;&nbsp;&nbsp;3&nbsp;&nbsp;&nbsp;&nbsp;6<br>&nbsp;&nbsp;&nbsp;/&nbsp;\<br>&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;\<br>&nbsp;1&nbsp;&nbsp;&nbsp;&nbsp;4<br>&nbsp;&nbsp;&nbsp;\<br>&nbsp;&nbsp;&nbsp;&nbsp;\<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2<br> The path traversed from <em>node1</em> = 2 to <em>node2</em> = 4 is: 2, 1, 3, and 4, so output is 3.</p>
 */
class Solution02
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public int bstDistance(int[] values, int n,
                           int node1, int node2)
    {
        // WRITE YOUR CODE HERE
        BST bst = new BST();
        for (int v : values) {
            bst.insert(v);
        }

        Node n1 = bst.findNode(node1);
        Node n2 = bst.findNode(node2);

        if (n1 == null || n2 == null) {
            return -1;
        }
        return bst.findDistance(n1, n2);
    }
    // METHOD SIGNATURE ENDS
    public static void main(String[] args) {
        Solution02 s = new Solution02();
        System.out.println("bstDistance(...) = " + s.bstDistance(new int[]{5, 6, 3, 1, 2, 4}, 6, 2, 4));
    }
}

class Node {
    int value;
    Node left;
    Node right;
    Node parent;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node parent) {
        this.value = value;
        this.parent = parent;
    }
}

class BST {
    Node root;

    Map<Integer, Node> nodesByVal = new HashMap<>();

    public void insert(int value) {
        if (root == null) {
            root = new Node(value);
            nodesByVal.put(value, root);
            return;
        }
        Node cur = root;
        Node parent = null;

        while (cur != null) {
            parent = cur;
            cur = cur.value > value ? cur.left : cur.right;
        }

        Node node = new Node(value, parent);
        nodesByVal.put(value, node);

        if (parent.value > value) {
            parent.left = node;
        } else {
            parent.right = node;
        }
    }

    public Node findNode(Integer value) {
        return nodesByVal.get(value);
    }

    public int findDistance(Node n1, Node n2) {
        int d1 = getDepth(n1);
        int d2 = getDepth(n2);

        if (d2 > d1) {
            Node temp = n1;
            n1 = n2;
            n2 = temp;
        }

        int diff = Math.abs( d1 - d2 );
        int distance = diff;

        while (diff > 0) {
            n1 = n1.parent;
            diff--;
        }

        while (n1 != n2) {
            n1 = n1.parent;
            n2 = n2.parent;
            distance += 2;
        }

        return distance;
    }

    private int getDepth(Node node) {
        int d = 0;

        while (node != null) {
            node = node.parent;
            d++;
        }
        return d;
    }
}