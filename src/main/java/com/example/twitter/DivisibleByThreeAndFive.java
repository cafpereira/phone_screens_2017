package com.example.twitter;

import java.util.*;

/*
  ## Question 1: ##
  Print integers 0-99
  if integer is divisible by 3 instead print "Foo"
  if integer is  divisible by 5 instead print "Bar"
  if integer is  divisible by 3 and 5 print "FooBar"
*/
public class DivisibleByThreeAndFive {

  public static String printFooBar(int num) {

    if (num % 3 == 0) {
      if (num % 5 == 0) {
        return "FooBar";
      } else {
        return "Foo";
      }
    }
    if (num % 5 == 0) {
      return "Bar";
    }
    return String.valueOf(num);
  }

  public static void main (String[] args) {
    for (int num = 0; num <= 99; num++) {
      System.out.println("printFooBar('" + num + "')=" + printFooBar(num));
    }
  }
}
