package com.companies.refdash.interview03Andy;

import java.util.*;

/**
 url: https://leetcode.com/problems/candy/description

 There are N children standing in a line. Each child is assigned a rating value.
 You are giving candies to these children subjected to the following requirements:
   - Each child must have at least one candy.
   - Children with a higher rating get more candies than their neighbors.
 What is the minimum candies you must give?
 */
public class MinimumCandyV2 {

  /**
   * Complexity:
   *  O(N) time
   *  O(N) space
   */
  public static int minCandy(int[] ratings) {
    int n = ratings.length;
    int[] candy = new int[n];
    Arrays.fill(candy, 1);

    for (int i = 1; i < n; i++) {
      if (ratings[i] > ratings[i-1]) {
        // If left neighbor rating is less than current
        // then make sure that current kid has more candies
        candy[i] = candy[i-1] + 1;
      }
    }

    // Now go backwards checking the ratings of the
    // neighbors to the right are lower
    for (int i = n-2; i >= 0; i--) {
      if (ratings[i] > ratings[i+1]) {
        // Only increase candy count if we need more candy
        // than the amount we used on the first pass
        candy[i] = Math.max(candy[i], candy[i + 1] + 1);
      }
    }
    return Arrays.stream(candy).sum();
  }

  public static void main(String[] args) {
    System.out.println("minCandy(ratings) = " + minCandy(new int[]{9,7,6,3,7,8,4,3,2}));
  }
}
