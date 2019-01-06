package com.mock.pramp.interview06;

import java.util.*;

/*
  Write a function that finds four numbers (quadruplet) in arr that sum up to s.
  Your function should return an array of these numbers in an ascending order.
  If such a quadruplet doesn’t exist, return an empty array.
 */
public class ArrayQuadruplet {
  static int[] findArrayQuadruplet(int[] arr, int s) {
    Arrays.sort(arr);
    int n = arr.length;
    for (int i = 0; i < n - 3; i++) {
      for (int j = i + 1; j < n - 2; j++) {
        int partial = arr[i] + arr[j];
        int target = s - partial;
        int l = j + 1;
        int k = n - 1;
        while (l < k) {
          int slk = arr[l] + arr[k];
          if (slk == target) {
            return new int[]{arr[i],arr[j],arr[l],arr[k]};
          }
          else if (slk < target){
            l++;
          }
          else { // slk > target
            k--;
          }
        }
      }
    }
    return new int[0];
  }

  public static void main(String[] args) {
    int[] sol = findArrayQuadruplet(new int[] {2, 7, 4, 0, 9, 5, 1, 3}, 20);
    System.out.println("findArrayQuadruplet = " + Arrays.toString(sol));
  }
}
