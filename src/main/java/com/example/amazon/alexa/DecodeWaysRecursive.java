package com.example.amazon.alexa;

import java.util.*;

/**
 * If a=1, b=2, c=3,....z=26. Given a string, find all possible codes that string can generate. Give a count as well as print the strings.
 * <p>
 * For example:
 * Input: "1123". You need to general all valid alphabet codes from this string.
 * <p>
 * Output List
 * aabc //a = 1, a = 1, b = 2, c = 3
 * kbc // since k is 11, b = 2, c= 3
 * alc // a = 1, l = 12, c = 3
 * aaw // a= 1, a =1, w= 23
 * kw // k = 11, w = 23
 */
public class DecodeWaysRecursive {

  public static int decodeWaysRec(String text) {
    // Successfully finished recursion of a new branch, count as 1
    if (text.isEmpty()) {
      return 1;
    }

    // Dont count this branch if starts with 0 digit,
    // since zero is not mapped to any letter
    if (text.charAt(0) == '0') {
      return 0;
    }

    // Count decoding branches of size n-1
    int ways = decodeWaysRec(text.substring(1));

    // If possible, branch out to count decodings of size n-2
    if (text.length() > 1) {
      int code = Integer.valueOf(text.substring(0, 2));
      // Dont recurse if code is not within valid range
      if (code <= 26) {
        ways += decodeWaysRec(text.substring(2));
      }
    }
    return ways;
  }


  public static void main(String[] args) {
    System.out.println("decodeWaysRec('121') = " + decodeWaysRec("121") + ", Expected: " + 3);
    System.out.println("decodeWaysRec('12321') = " + decodeWaysRec("12321") + ", Expected: " + 6);
    System.out.println("decodeWaysRec('1123') = " + decodeWaysRec("1123") + ", Expected: " + 5);
    System.out.println("decodeWaysRec('9090') = " + decodeWaysRec("9090") + ", Expected: " + 0);
  }
}


