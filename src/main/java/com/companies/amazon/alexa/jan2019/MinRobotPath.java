package com.companies.amazon.alexa.jan2019;

import java.util.*;

/**
 * Given a map representation (matrix) find the minimum path from (0,0) to the cell A[i][j]== 9
 * The robot can go up, down, left and right. It only moves to (i,j) if A[i][j]==1
 */
public class MinRobotPath {
  static class Coordinate {
    int x;
    int y;

    Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
  // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
  int removeObstacle(int numRows, int numColumns, List<List<Integer>> lot)
  {
    Deque<Coordinate> queue = new LinkedList<>();
    queue.add(new Coordinate(0, 0));

    int d = 0;
    boolean[][] visited = new boolean[numRows][numColumns];
    visited[0][0] = true;

    while(!queue.isEmpty()) {
      Coordinate cur = queue.remove();
      if (found(cur, lot)) {
        return d;
      }

      int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};
      for (int[] dir : directions) {
        int x = cur.x + dir[0];
        int y = cur.y + dir[1];
        Coordinate next = new Coordinate(x, y);
        if (valid(next, numRows, numColumns, lot) && !visited[x][y]) {
          queue.add(next);
          visited[x][y] = true;
        }
      }
      d++;
    }
    return -1;
  }

  boolean found(Coordinate c, List<List<Integer>> lot) {
    return lot.get(c.x).get(c.y) == 9;
  }

  boolean valid(Coordinate c, int numRows, int numColumns, List<List<Integer>> lot) {
    return c.x >= 0 && c.x < numRows && c.y >= 0 && c.y < numColumns &&
        lot.get(c.x).get(c.y) > 0;
  }

  public static void main(String[] args) {
    MinRobotPath s = new MinRobotPath();

    List<List<Integer>> lot = new ArrayList<>();
    lot.add(Arrays.asList(1,0,0));
    lot.add(Arrays.asList(1,0,0));
    lot.add(Arrays.asList(1,9,1));

    System.out.println(s.removeObstacle(3, 3, lot));
  }
}
