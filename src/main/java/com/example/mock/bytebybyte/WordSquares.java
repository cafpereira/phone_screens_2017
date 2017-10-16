package com.example.mock.bytebybyte;

import java.util.*;

/**
 * url: https://discuss.leetcode.com/topic/63516/explained-my-java-solution-using-trie-126ms-16-16
 *
 * Given a list of W words, where each word has size N,
 * return all possible word squares that can be formed.
 *
 * Example:
 * N = 5
 * words = {heart, ember, abuse, resin, trend, drive, spine}
 *
 * H E A R T
 * E M B E R
 * A B U S E
 * R E S I N
 * T R E N D
 **/
public class WordSquares {

  public static void main(String[] args) {
    runTest(new String[]{"heart", "ember", "abuse", "resin", "trend", "drive", "spine"}, 5);
    runTest(new String[]{"area", "lead", "wall", "lady", "ball"}, 4);
  }

  private static void runTest(String[] words, int n) {
    WordSquares demo = new WordSquares();
    System.out.println("----------");
    System.out.println("findWordSquares(words, " + n + ") = ");
    List<List<String>> allSquares = demo.findWordSquares(words, n);
    for (List<String> wordSqr : allSquares) {
      System.out.println("----------");
      for (String l : wordSqr) { System.out.println(l); }
    }
  }

  public List<List<String>> findWordSquares(String[] words, int n) {
    List<List<String>> solution = new ArrayList<>();
    List<String> currentSqr = new ArrayList<>();

    Trie trie = new Trie(words);

    for (String w : words) {
      currentSqr.add(w);
      buildWordSquares(currentSqr, 1, n, trie, solution);
      int last = currentSqr.size() - 1;
      currentSqr.remove(last); // Backtrack before searching next word square
    }

    return solution;
  }

  private void buildWordSquares(List<String> curr, int index, int n, Trie trie, List<List<String>> solution) {
    if (index == n) {
      solution.add(new ArrayList<>(curr));
      return;
    }

    String prefix = topDownPrefix(curr, index);
    List<String> words = trie.findByPrefix(prefix);
    for (String next : words) {
      curr.add(next);
      buildWordSquares(curr, index + 1, n, trie, solution);
      curr.remove(curr.size() - 1);
    }
  }

  private String topDownPrefix(List<String> wordSquare, int col) {
    StringBuilder sb = new StringBuilder();
    for (String word : wordSquare) {
      sb.append(word.charAt(col));
    }
    return sb.toString();
  }
}

class TrieNode {
  List<String> startWith;
  TrieNode[] children;

  TrieNode() {
    startWith = new ArrayList<>();
    children = new TrieNode[26];
  }
}

class Trie {
  TrieNode root;

  Trie(String[] words) {
    root = new TrieNode();
    for (String w : words) {
      TrieNode cur = root;
      for (char ch : w.toCharArray()) {
        int idx = ch - 'a';
        if (cur.children[idx] == null)
          cur.children[idx] = new TrieNode();
        cur.children[idx].startWith.add(w);
        cur = cur.children[idx];
      }
    }
  }

  List<String> findByPrefix(String prefix) {
    List<String> ans = new ArrayList<>();
    TrieNode cur = root;
    for (char ch : prefix.toCharArray()) {
      int idx = ch - 'a';
      if (cur.children[idx] == null)
        return ans;

      cur = cur.children[idx];
    }
    ans.addAll(cur.startWith);
    return ans;
  }
}
