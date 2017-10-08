package com.example.mock.bytebybyte;

/**
 * Return minimum amount of coins need to make change of C cents.
 *
 * 47 -> 25 + 10 + 10 + 1 + 1 -> 5
 **/
public class MakeChange {

  public static int makeChange(int c) {
    return makeChangeRec(c, 0, new int[]{25,10,1});
  }

  public static int makeChangeRec(int c, int i, int[] coins) {
    if (c == 0) {
      return 0;
    }
    int withCoin = Integer.MAX_VALUE;
    int withoutCoin = Integer.MAX_VALUE;

    if (c >= coins[i]) {
      withCoin = makeChangeRec(c - coins[i], i, coins) + 1;
    }
    if (i < coins.length - 1) {
      withoutCoin = makeChangeRec(c, i + 1, coins);
    }

    return Math.min(withCoin, withoutCoin);
  }

  public static void main(String[] args) {
    System.out.println("makeChange(47) = " + makeChange(47) + " # Expected: " + 5);
  }
}
