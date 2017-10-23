package com.example.refdash.interview03Andy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 url: https://leetcode.com/problems/candy/description
 There are N children standing in a line. Each child is assigned a rating value.
 You are giving candies to these children subjected to the following requirements:
   - Each child must have at least one candy.
   - Children with a higher rating get more candies than their neighbors.
 What is the minimum candies you must give?
 */
public class MinimumCandyV2 {
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
