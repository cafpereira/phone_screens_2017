package com.example.refdash.interview03Andy;

import java.util.*;
import java.util.stream.Collectors;

/**
 * As a reward for your kindergarten students taking a test, you decide to give them some candy. The kids all wait in a line and await you to give
 * them some candy. The only way the children think this is fair is that if a student has a higher score than a neighboring child, that the student
 * with the higher score gets more candy. How many pieces of candy do you need to give out?
 */
public class MinimumCandyLine {
  public int totalCandy(int[] scores, int maxScore) {

    int n = scores.length;
    if (n <= 1) {
      return n;
    }

    int[] candy = new int[n];

    List<Integer>[] scoreMap = new ArrayList[maxScore + 1];
    for (int i = 0 ; i < n; i++) {
      int score = scores[i];
      if (scoreMap[score] == null) {
        scoreMap[score] = new ArrayList<Integer>();
      }
      scoreMap[score].add(i);
    }

    for (int curScore = 1; curScore <= maxScore; curScore++) {
      for (int idx : scoreMap[curScore]) {
        int leftScore = idx > 0 ? scores[idx - 1] : -1;
        int rightScore = idx < n - 1 ? scores[idx + 1] : -1;
        if (scores[idx] > Math.max(leftScore, rightScore)) {
          if (leftScore > rightScore) {
            candy[idx] = candy[leftScore] + 1;
          }
          else{
            candy[idx] = candy[rightScore] + 1;
          }
        }
        else {
          candy[idx] = candy[idx] + 1;
        }
      }
    }

    return Arrays.stream(candy)
        .boxed()
        .mapToInt(i -> i)
        .sum();
  }
}
