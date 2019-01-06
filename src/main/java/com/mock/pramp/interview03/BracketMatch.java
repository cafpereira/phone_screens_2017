package com.mock.pramp.interview03;

/*
  Bracket Match

  A string of brackets is considered correctly matched if every opening bracket in
  the string can be paired up with a later closing bracket, and vice versa.
  For instance, “(())()” is correctly matched, whereas “)(“ and “((” aren’t.
  For instance, “((” could become correctly matched by adding two closing brackets
  at the end, so you’d return 2.

  Given a string that consists of brackets, write a function bracketMatch that takes
  a bracket string as an input and returns the minimum number of brackets you’d need
  to add to the input in order to make it correctly matched.

  Explain the correctness of your code, and analyze its time and space complexities.
 */
public class BracketMatch {
  /*
    Keep two counts for each type of missing brackets
    and return the sum of both.
    Complexity: O(N) time
                O(1) space
  */
  static int bracketMatch(String text) {
    // your code goes here
    int missOpen = 0;
    int missClose = 0;

    if (text == null || text.equals("")) {
      return 0;
    }

    for (char bracket : text.toCharArray()) {
      if (bracket == '(') {
        missClose++;
      } else {
        if (missClose == 0) {
          missOpen++;
        } else {
          missClose--;
        }
      }
    }
    return missOpen + missClose;
  }

//  Test Case #1 Input: ")",Expected: 1,Actual: 1
//  Test Case #2 Input: "(",Expected: 1,Actual: 1
//  Test Case #3 Input: "(())",Expected: 0,Actual: 0
//  Test Case #4 Input: "(()",Expected: 1,Actual: 1
//  Test Case #5 Input: "())(",Expected: 2,Actual: 2
//  Test Case #6 Input: "))))",Expected: 4,Actual: 4
//  Test Case #7 Input: ")(",Expected: 2,Actual: 2
//  Test Case #8 Input: "()()()()()",Expected:0,Actual:0

  public static void main(String[] args) {
    System.out.println(bracketMatch("(()"));
    System.out.println(bracketMatch("(())"));
    System.out.println(bracketMatch("())("));
    System.out.println(bracketMatch(""));
  }
}
