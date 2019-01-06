package com.mock.pramp.interview01;

import java.util.*;

class Solution {

    static int[] pancakeSort(int[] arr) {
        // your code goes here
        int n = arr.length - 1;
        while (n > 0) {
            int max = Integer.MIN_VALUE;
            int k = -1;
            for (int i = n; i >= 0; i--) {
                if (arr[i] > max) {
                    k = i;
                    max = arr[i];
                }
            }
            flip(arr, k + 1);
            flip(arr, n + 1);
            n--;
        }

        return arr;
    }

    static void flip(int[] arr, int k) {
        int start = 0;
        int end = k - 1;
        while (start < end) {
            int tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 4, 3, 2};
        int[] res = Solution.pancakeSort(arr);
        System.out.println(Arrays.toString(res));
    }
}
