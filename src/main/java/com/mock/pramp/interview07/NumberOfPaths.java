package com.mock.pramp.interview07;

/**
 * You’re testing a new driverless car that is located at the Southwest (bottom-left) corner of an n×n grid. The car is supposed to get to the
 * opposite, Northeast (top-right), corner of the grid. Given n, the size of the grid’s axes, write a function numOfPathsToDest that returns the
 * number of the possible paths the driverless car can take.
 */

public class NumberOfPaths {

  static int numOfPathsToDest(int n) {
    // We will use dynamic prog to calculate the number of paths
    // based on results of past iterations
    int[][] grid = new int[n][n];

    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        if (i == 0) {
          // Base case
          grid[i][j] = 1;
          continue;
        }
        // Here both i and j are always greater than zero,
        // no need to test if they are in the boundaries
        int up = grid[i - 1][j];
        int left = grid[i][j - 1];
        grid[i][j] = up + left;
      }
    }
    // last cell has the total of paths
    return grid[n - 1][n - 1];
  }

  /**
   * There is no need to keep the entire matrix (N^2) in memory.
   * Each iteration only reads the values from the current and
   * previous rows.
   *
   * This is an optimized O(N) memory implementation.
   */
  static int numOfPathsToDestV2(int n) {
    int[] cur = null;
    int[] prev = null;

    for (int row = 0; row < n; row++) {
      // Allocate space for the current row
      cur = new int[n];
      for (int col = row; col < n; col++) {
        if (row == 0) {
          // Base case
          cur[col] = 1;
          continue;
        }
        int up = prev[col];
        int left = cur[col - 1]; // no need for boundary check because
                                 // col is always at least one here
        cur[col] = up + left;
      }
      // Save current values in backup array for next iteration, if necessary
      prev = cur;
    }
    return cur[n - 1];
  }

  public static void main(String[] args) {
    // int res = numOfPathsToDest(4);
    int res = numOfPathsToDestV2(4);
    System.out.println("res: " + res);
  }
}
