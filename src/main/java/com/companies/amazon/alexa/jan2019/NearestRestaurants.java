package com.companies.amazon.alexa.jan2019;

import java.util.List;

/**
 * Given a list of coordinates (x,y), find k nearest restaurants from (0,0)
 * Distance formula: f(x,y) = sqrt(x^2 + y^2)
 */
public class NearestRestaurants {
  // FIXME: forgot to copy the solution on the interview day.
  //  I used the standart solution from the EPI book, problem 11.4: "Compute the k closest stars"
  //  With a max heap of size k we can track the closest elements.

  static class Coordinate {
    int x;
    int y;
    double distance;

    Coordinate(int x, int y, double d) {
      this.x = x;
      this.y = y;
      this.distance = d;
    }
  }

  List<List<Integer>> nearestRestaurants(int numRestaurants, List<List<Integer>> allLocations) {
    //TODO
    return null;
  }
}
