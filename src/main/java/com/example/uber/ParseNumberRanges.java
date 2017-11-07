package com.example.uber;

// This is the text editor interface.
// Anything you type or change here will be seen by the other person in real time.

// Give an array of integers, output a string which represents a ranges of existing numbers separate by comma.
// Each range is in the format of “begin-end”. For example:
// Input: [1,2,3,6,5,9] [1,2,3,5,10,6,9,11,15,17]
// Output: “1-3,5-6,9” “1-3,5-6,9-11,15,17”

// https://codepair.hackerrank.com/paper/mjluwyxakfagicebbpswhkrfvjrlhshp?b=eyJpbnRlcnZpZXdfaWQiOjQzODIxNiwicm9sZSI6ImludGVydmlld2VyIiwic2hvcnRfdXJsIjoiaHR0cDovL2hyLmdzLzJlNGQ5YyJ9#

import java.util.*;

class ParseNumberRanges {

  public static List<String> findRanges(int[] array) {
    // TODO

    int start = 0;
    int end = 0;

    Arrays.sort(array);

    List<String> res = new ArrayList<>();
    for (int i = 1; i < array.length; i++) {
      int v = array[i];
      if (v == array[i-1] + 1 || v == array[i-1]) {
        end = i;
      } else {
        res.add(closeRange(array, start, end));
        start = i;
        end = start;
      }
    }
    res.add(closeRange(array, start, end));
    return res;
  }

  public static List<Integer> parseRanges(String allRanges) {
    List<Integer> array = new ArrayList<>();
    int start = 0;
    int end = 0;

    for (String range : allRanges.split(",")) {
      char first = range.charAt(0);
      if (first != '-') {
        // Same idea from before
      }
      else {
        String str = range.substring(1);
        String[] nums = str.split("-");
        start = Integer.valueOf(nums[0]) * -1;
        end = 0;
        if (nums[1].isEmpty() && nums.length > 2) {
          end = Integer.valueOf(nums[2]) * -1;
        }
        else {
          end = Integer.valueOf(nums[1]);
        }
      }

      for (int i = start; i <= end; i++) {
        array.add(i);
      }
    }

    return array;
  }

  public static String closeRange(int[] array, int start, int end) {
    return start == end ? String.valueOf(array[start]) : array[start] + "-" + array[end];
  }

  public static void main(String[] args) {
    System.out.println("findRanges() = " + findRanges(new int[]{1,2,3,6,5,9}) + " # expected: '1-3,5-6,9'");
    System.out.println("findRanges() = " + findRanges(new int[]{1,2,2,5,10,10,9}) + " # expected: '1-2,5,9-10'");
    System.out.println("parseRanges() = " + parseRanges("-10--5"));
  }
}

