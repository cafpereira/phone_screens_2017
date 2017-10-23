package com.example.mock.bytebybyte;

import java.util.*;

/**
 http://collabedit.com/qkpuf

 Given an unsorted array of integers, find the length of longest increasing subsequence.

 For example,
 Given [10, 9, 2, 5, 3, 7, 101, 18],
 The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

 Your algorithm should run in O(n2) complexity.
 */
public class LongestIncreaseSequence {

  /*
    Recursive solution
   */
  private List<Integer> LIS(int[] arr, int i, List<Integer> result) {
    if (i == arr.length) return result;

    List<Integer> result1 = LIS(arr, i+1, result);

    if (arr[i] > result.get(result.size() - 1)) {
      result.add(arr[i]);
      List<Integer> result2 = LIS(arr, i+1, result);
      if (result2.size() > result1.size()) return result2;
    }
    return result1;
  }

  // Dynamic Programming:
  // [10, 9, 2, 5, 3, 7, 101, 18]
  // [1,  1, 1, 2, 2  3,   4,  4]
}
