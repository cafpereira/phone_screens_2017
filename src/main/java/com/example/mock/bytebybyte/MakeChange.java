package com.example.mock.bytebybyte;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Return minimum amount of coins need to make change of C cents.
 *
 * 47 -> 25 + 10 + 10 + 1 + 1 -> 5
 **/
public class MakeChange {

  public static int[] coins = {25, 10, 1};

  public static int makeChangeRec(int c) {
    return makeChangeRec(coins, c, 0);
  }

  /**
   * Recursive algorithm explores every path that sums to C
   * Complexity: O(C^k), where k is the number of distinct coins.
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

  /**
   * Greedy algorithm, subtract the largest coin value as many
   * time as necessary, then move to smaller values until
   * remaining money gets to zero.
   *
   * It only works if the coin set is guaranteed to always
   * reach zero using the greedy choice. For instance, the
   * US currency coins.
   *
   * Complexity: O(1) time and space
   */
  public static int makeChangeGreedy(int[] coins, int c) {
    // Make sure the input coins are sorted in decreasing order
    List<Integer> coinList = Arrays.stream(coins)
        .boxed()
        .collect(Collectors.toList());
    Collections.sort(coinList, Comparator.reverseOrder());

    int numCoins = 0;
    for (int i = 0; i < coinList.size(); i ++) {
      numCoins += c / coinList.get(i);
      c = c % coinList.get(i);
      if (c == 0) {
        break;
      }
    }
    return numCoins;
  }

  /**
   * Bottom up dynamic programming solution
   * Complexity: O(C * k) time
   *             O(C) space
   */
  public static int makeChangeDP(int[] coins, int c) {

    // Not necessary to sort the coins, just make it easier
    // to debug the algorithm in the IDE
    Arrays.sort(coins);

    // Instead of setting max as Integer.MAX_VALUE we pick another
    // smaller value that will allow us to increase and compare
    // variables like Math.min(max, max + 1) without worrying about
    // integer overflow.
    int max = c + 1;
    int[] table = new int[c + 1];
    Arrays.fill(table, 1, c + 1, max);

    for (int coin : coins) {
      for (int i = 1; i <= c; i++) {
        if (i >= coin) {
          table[i] = Math.min(table[i], table[i - coin] + 1);
        }
      }
    }
    return table[c] >= max ? 0 : table[c];
  }

  public static void main(String[] args) {
    System.out.println("makeChangeRec(47) = " + makeChangeRec(47) + " # Expected: " + 5);
    System.out.println("makeChangeGreedy(47) = " + makeChangeGreedy(coins, 47) + " # Expected: " + 5);
    System.out.println("makeChangeDP(47) = " + makeChangeDP(coins, 47) + " # Expected: " + 5);
  }
}
