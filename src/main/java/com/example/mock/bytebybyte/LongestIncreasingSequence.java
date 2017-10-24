package com.example.mock.bytebybyte;

import java.util.*;

/**
 http://collabedit.com/qkpuf

 Given an unsorted array of integers, find the length of longest increasing subsequence.

 For example,
 Given [10, 9, 2, 5, 3, 7, 101, 18],
 The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
 Note that there may be more than one LIS combination, it is only necessary for you to return the length.

 Your algorithm should run in O(n2) complexity.
 */
public class LongestIncreasingSequence {

  /*
    Recursive solution
      - Exponential time Complexity: O(2^N)
   */
  public static int lisRecursive(int[] arr) {
    return lisRecursive(arr, Integer.MIN_VALUE, 0);
  }

  public static int lisRecursive(int[] arr, int prev, int i) {
    if (i == arr.length) {
      return 0;
    }
    int taken = 0;
    if (prev < arr[i]) {
      taken = lisRecursive(arr, arr[i], i + 1) + 1;
    }
    int notTaken = lisRecursive(arr, prev, i + 1);

    return Math.max(taken, notTaken);
  }

  /*
    Memoization solution
    - Time Complexity: O(N^2)
    - Space:  O(N^2)
  */
  public static int lisMemo(int[] arr) {
    int n = arr.length;
    int[][] memo = new int[n + 1][n];

    for (int[] line : memo) {
      Arrays.fill(line, -1);
    }
    return lisMemo(arr, -1, 0, memo);
  }

  public static int lisMemo(int[] arr, int prevIdx, int curIdx, int[][] memo) {
    if (curIdx == arr.length) {
      return 0;
    }
    if (memo[prevIdx + 1][curIdx] >= 0) {
      return memo[prevIdx + 1][curIdx];
    }

    int taken = 0;
    if (prevIdx < 0 || arr[prevIdx] < arr[curIdx]) {
      taken = lisMemo(arr, curIdx , curIdx + 1, memo) + 1;
    }
    int notTaken = lisMemo(arr, prevIdx, curIdx + 1, memo);

    memo[prevIdx + 1][curIdx] = Math.max(taken, notTaken);
    return memo[prevIdx + 1][curIdx];
  }

  /*
   Dynamic Programming
   - Time Complexity: O(N^2)
   - Space:  O(N)
  */
  public static int lisDP(int[] arr) {
    int n = arr.length;
    int[] dp = new int[n];

    for (int i = 0; i < n; i++) {
      int max = 0;
      for (int j = 0; j < i; j++) {
        if (arr[j] <= arr[i]) {
          max = Math.max(dp[j], max);
        }
      }
      dp[i] = max + 1;
    }

    int lis = 0;
    for (int length : dp) {
      lis = Math.max(lis, length);
    }

    return lis;
  }

  public static void main(String[] args) {
    System.out.println("lisRecursive(array) = " + lisRecursive(new int[]{10, 9, 2, 5, 3, 7, 101, 18}) + " # Expected: " + 4);
    System.out.println("lisMemo(array) = " + lisMemo(new int[]{10, 9, 2, 5, 3, 7, 101, 18}) + " # Expected: " + 4);
    System.out.println("lisDP(array) = " + lisDP(new int[]{10, 9, 2, 5, 3, 7, 101, 18}) + " # Expected: " + 4);
  }
}
