package com.example.mock.pramp.interview07;

/**
 * You’re testing a new driverless car that is located at the Southwest (bottom-left) corner of an n×n grid. The car is supposed to get to the
 * opposite, Northeast (top-right), corner of the grid. Given n, the size of the grid’s axes, write a function numOfPathsToDest that returns the
 * number of the possible paths the driverless car can take.
 */

public class NumberOfPaths {

  static int numOfPathsToDest(int n) {
    // your code goes here
    int[][] grid = new int[n][n];

    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        if (i == 0) {
          grid[i][j] = 1;
          continue;
        }
        // After the check above, i and j are always
        // greater than zero, no need to test if they
        // are inside the matrix boundaries
        int up = grid[i - 1][j];
        int left = grid[i][j - 1];
        grid[i][j] = up + left;
      }
    }
    // last cell has the total of paths
    return grid[n - 1][n - 1];
  }

  public static void main(String[] args) {
    int res = numOfPathsToDest(4);
    System.out.println("res: " + res);
  }
}
