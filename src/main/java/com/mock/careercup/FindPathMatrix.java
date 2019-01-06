package com.mock.careercup;

import java.util.*;

/**
 * Given a matrix with only 0s and 1s, where 0 represents a wall
 * and 1 represents a path, return if there is a path from first
 * to last row. (0,0) -> (m-1,n-1)
 *
 * Follow-up: Return the path. Also, how can we find the shortest path?
 * Answer: Use BFS instead of DFS. (or maybe Djkstra?)
 *         BFS implementation requires queue instead of recursion stack
 */
public class FindPathMatrix {

  List<Cell> findPath(int[][] matrix) {
    // TODO check corner cases, e.g. empty and null matrix
    int m = matrix.length;
    int n = matrix[0].length;
    boolean[][] visited = new boolean[m][n];
    List<Cell> path = new ArrayList<>();

    return findPath(matrix, 0, 0, visited, path) ? path : null;
  }

  boolean findPath(int[][] matrix, int row, int col ,  boolean[][] visited, List<Cell> path) {
    int m = matrix.length;
    int n = matrix[0].length;

    // Base case:
    // suggestion move this to a helper function
    if (row < 0 || row >= m || col < 0 || col >= n || visited[row][col] || matrix[row][col] == 0) {
      return false;
    }

    path.add(new Cell(row, col));

    if (row == m && col == n && matrix[row][col] == 1) {
      return true;
    }

    visited[row][col] = true;
    //                                   up,   down,    left,    right
    int [][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    for (int[] dir : directions) {
      if (findPath(matrix, dir[0] + row, dir[1] + col, visited, path)) {
        return true;
      }
    }
    // Backtrack
    path.remove(path.size() - 1);
    return false;
  }
}

class Cell {
  int row;
  int col;

  public Cell(int row, int col) {
    this.row = row;
    this.col = col;
  }
}
