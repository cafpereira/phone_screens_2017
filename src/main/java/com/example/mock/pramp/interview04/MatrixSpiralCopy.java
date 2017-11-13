package com.example.mock.pramp.interview04;

import java.util.*;

/*
  Given a 2D array (matrix) inputMatrix of integers, create a function spiralCopy
  that copies inputMatrix’s values into a 1D array in a spiral order, clockwise.
  Your function then should return that array. Analyze the time and space complexities
  of your solution.
 */

public class MatrixSpiralCopy {

  static int[] spiralCopy(int[][] inputMatrix) {
    // your code goes here
    int n = inputMatrix.length;
    int m = inputMatrix[0].length;

    int[] spiral = new int[n * m];

    int topRow = 0;
    int lastRow = n -1;
    int firstCol = 0;
    int lastCol = m - 1;
    int cur = 0;

    while(true) {
      // leftToRight(inputMatrix, spiral)
      for (int j = firstCol; j <= lastCol; j++) {
        spiral[cur++] = inputMatrix[topRow][j];
      }
      topRow++;
      if (lastRow < topRow) {
        break;
      }

      // upToBottom(inputMatrix, spiral)
      for (int i = topRow; i <= lastRow; i++) {
        spiral[cur++] = inputMatrix[i][lastCol];
      }
      lastCol--;
      if (lastCol < firstCol) {
        break;
      }

      // rightToLeft(inputMatrix, spiral)
      for (int j = lastCol; j >= firstCol; j--) {
        spiral[cur++] = inputMatrix[lastRow][j];
      }
      lastRow--;
      if (lastRow < topRow) {
        break;
      }

      // bottomUp(inputMatrix, spiral)
      for (int i = lastRow; i >= topRow; i--) {
        spiral[cur++] = inputMatrix[i][firstCol];
      }
      firstCol++;
      if (lastCol < firstCol) {
        break;
      }
    }
    return spiral;
  }

  public static void main(String[] args) {
    int [][] inputMatrix = {{1,   2,   3,   4,   5},
                            {6,   7,   8,   9,   10},
                            {11,  12,  13,  14,  15},
                            {16,  17,  18,  19,  20}};

    // Expected: [1, 2, 3, 4, 5, 10, 15, 20, 19, 18, 17, 16, 11, 6, 7, 8, 9, 14, 13, 12]
    System.out.println("spiralCopy(inputMatrix) = " + Arrays.toString(spiralCopy(inputMatrix)));
  }
}