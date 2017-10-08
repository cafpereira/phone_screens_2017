package com.example.refdash.screencast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hippity Hop Race
 * 1) You're given a starting speed. A speed at any given point indicates how
 * much you will move in the X direction with the next jump.
 * 2) You can adjust your speed by up to 1 unit before the next jump.
 * 3) Stop safely anywhere along the runway with speed of zero
 *
 * Refdash training session:
 *   https://www.youtube.com/watch?v=kKhnYLpME3w
 */
class HopRace {

  static boolean[] runway = {true, false, true, true, true, false, true, true, false, true, true};

  private static List<Bounce> findPath(int initialSpeed) {
    Map<Integer, Map<Integer, Integer>> memo = new HashMap<>();

    Boolean hasPath = findPath(0, initialSpeed, memo);
    if (!hasPath) {
      return null;
    }

    Integer cur = 0;
    Integer speed = initialSpeed;
    List<Bounce> path = new ArrayList<>();

    while (speed != 0) {
      speed = memo.get(cur).get(speed);
      path.add(new Bounce(cur, speed));
      cur += speed;
    }
    // Time complexity: O(sqrt(N) * N)
    return path;
  }

  public static Boolean findPath(int i, int speed, Map<Integer, Map<Integer, Integer>> memo) {
    int n = runway.length;
    // notice that speed cannot be larger than N, more specifically sqrt(N)
    // = k + (k-1) + (k-1) + (k-1) + ... + 2 + 1 <= N
    // ~ k^2 + k <= N
    // ~ k <= sqrt(N)
    if (i >= n || i < 0 || !runway[i]) {
      return false;
    }

    if (speed == 0) {
      return true;
    }

    if (memo.containsKey(i)) {
      if (memo.get(i).containsKey(speed)) {
        return memo.get(i).get(speed) != -1;
      }
    } else {
      memo.put(i, new HashMap<>());
    }

    for (int next : new int[]{speed - 1, speed, speed + 1}) {
      if (findPath(i + next, next, memo)) {
        memo.get(i).put(speed, next);
        return true;
      }
    }
    memo.get(i).put(speed, -1);
    return false;
  }

  public static void main(String[] args) {
    System.out.println("findPath(initialSpeed = 5) = " + findPath(5));
  }
}

class Bounce {
  int pos;
  int spd;

  public Bounce(Integer pos, Integer spd) {
    this.pos = pos;
    this.spd = spd;
  }
  @Override
  public String toString() {
    return "(" + pos + ", " + spd + ")";
  }
}