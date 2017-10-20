package com.example.dropbox;

/*
 * If a cell is alive and has exactly 2 or 3 alive neighbors, then it stays alive.
 * else, it dies
 * If a cell is dead and has exactly 3 alive neighbors, it becomes alive, else it dies
   Input:
   d d d
   a a d
   a d d

   Output:
   d d d
   a a d
   a a d

 * Board iterate(Board b)
 *
 */

public class GameOfLife {
  public static boolean[][] evolve(boolean[][] input) {
    int n = input.length;
    int m = input[0].length;

    boolean[][] output = new boolean[n][m];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        evolveCell(input, i, j, output);
      }
    }
    return output;
  }


  public static void evolveCell(boolean[][] input, int row, int col, boolean[][] output) {
    int n = input.length;
    int m = input[0].length;

    int alive = 0;

    for (int i = row - 1; i <= row + 1; i++) {
      for (int j = col - 1; j <= col + 1; j++) {
        if (inBoundary(i,j,n,m) && (i != row || j != col)) {
          if (input[i][j]) {
            alive++;
          }
        }
      }
    }

    if (input[row][col]) {
      output[row][col] = input[row][col] && (alive == 2 || alive == 3);
    } else {
      output[row][col] = alive == 3;
    }
  }
  public static boolean inBoundary(int row, int col, int n, int m) {
    return row >= 0 && row < n && col >= 0 && col < m;
  }
}