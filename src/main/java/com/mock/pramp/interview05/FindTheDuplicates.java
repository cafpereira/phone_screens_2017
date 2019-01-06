package com.mock.pramp.interview05;

import java.util.*;

/*
  Given two sorted arrays of US passport numbers: Arr1 and Arr2 of lengths n and m respectively,
  how can you most efficiently compute an array of all persons included on both arrays?

  Solve and analyze the complexity for 2 cases:
  1. m ≈ n - lengths are approximately the same
  2. m ≫ n - one is much longer than the other
 */
public class FindTheDuplicates {
  public static int[] findDuplicates(int[] arr1, int[] arr2) {
    int[] m = arr1.length > arr2.length ? arr1 : arr2;
    int[] n = arr1.length > arr2.length ? arr2 : arr1;

    List<Integer> dup = new ArrayList<>();
    for (int num : n) {
      if (binarySearch(num, m)){
        dup.add(num);
      }
    }
    return dup.stream().mapToInt(i->i).toArray();
  }

  private static boolean binarySearch(int num, int[] arr) {
    int start = 0;
    int end = arr.length - 1;

    while (start <= end) {
      int mid = (start + end) / 2;
      if (arr[mid] == num) {
        return true;
      } else if (arr[mid] < num) {
        start = mid + 1;
      }
      else { // (arr[mid] > num)
        end = mid - 1;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    System.out.println("findDuplicates([1,5], [3,5,8,10]) = " +
        Arrays.toString(findDuplicates(new int[]{1,5}, new int[]{3,5,8,10})) + " # Expected: [5]");
  }
}
