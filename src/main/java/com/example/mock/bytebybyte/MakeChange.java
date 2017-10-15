package com.example.mock.bytebybyte;

/**
 * Return minimum amount of coins need to make change of C cents.
 *
 * 47 -> 25 + 10 + 10 + 1 + 1 -> 5
 **/
public class MakeChange {

  public static int makeChange(int c) {
    return makeChangeRec(new int[]{25,10,1}, c, 0);
  }

  /**
   * Complexity: O(C^k), where k is the number of coin values.
   */
  public static int makeChangeRec(int[] coins, int c, int i) {
    // Base-case, used coins sum == value C
    if (c == 0) {
      return 0;
    }

    // Used every coin in this recursion branch
    // and didnt find a valid sum of C
    if (i >= coins.length) {
      return -1;
    }

    // Start numCoins and amountRemaining values
    // with zero so we can have at least one one
    // recursion branch without this coin
    int numCoins = 0;
    int amountRemaining = 0;

    int min = Integer.MAX_VALUE;
    while (amountRemaining <= c) {
      int minCoinsRec = Math.min(min, makeChangeRec(coins, c - amountRemaining, i + 1));
      if (minCoinsRec > -1) {
        min = Math.min(min, minCoinsRec + numCoins);
      }
      amountRemaining += coins[i];
      numCoins++;
    }
    return min == Integer.MAX_VALUE ? -1 : min;
  }

  // TODO #1 implement greed algorigthm (works for US coins)
  // TODO #2 implement bottom-up dynamic algorithm (works for any coin set)

  public static void main(String[] args) {
    System.out.println("makeChange(47) = " + makeChange(47) + " # Expected: " + 5);
  }
}
