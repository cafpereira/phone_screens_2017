package com.companies.amazon.alexa.aug2017;

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


  /**
   * Solution Geeks4geeks: https://www.geeksforgeeks.org/count-possible-decodings-given-digit-sequence/
   */
  static int countDecoding(char[] digits, int n)
  {
    // base cases
    if (n == 0 || n == 1)
      return 1;

    // Initialize count
    int count = 0;

    // If the last digit is not 0, then
    // last digit must add to
    // the number of words
    if (digits[n - 1] > '0')
      count = countDecoding(digits, n - 1);

    // If the last two digits form a number
    // smaller than or equal to 26,
    // then consider last two digits and recur
    if (digits[n - 2] == '1' ||
        (digits[n - 2] == '2' && digits[n - 1] < '7'))
      count += countDecoding(digits, n - 2);

    return count;
  }


  public static void main(String[] args) {
    System.out.println("decodeWaysRec('121') = " + decodeWaysRec("121") + ", Expected: " + 3);
    System.out.println("decodeWaysRec('12321') = " + decodeWaysRec("12321") + ", Expected: " + 6);
    System.out.println("decodeWaysRec('1123') = " + decodeWaysRec("1123") + ", Expected: " + 5);
    System.out.println("decodeWaysRec('9090') = " + decodeWaysRec("9090") + ", Expected: " + 0);
  }
}


