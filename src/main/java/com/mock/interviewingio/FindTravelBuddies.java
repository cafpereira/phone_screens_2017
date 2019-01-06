package com.mock.interviewingio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindTravelBuddies {
  public static void main(String[] args) {
    String[] input = {
        "me, 1, 2, 3, 4",
        "u1, 1, 2, 3, 4",
        "u2, 1, 5, 6",
        "u3, 1, 2, 5",
        "u4, 1, 2, 3, 5, 6",
    };

    findBuddies(input);
  }

  public static void findBuddies(String[] input) {
    String first = input[0];
    Set<String> myCities = parseSet(first);
    double limit = Math.ceil(myCities.size() / 2.0);

    List<TravelFriend> res = new ArrayList<>();

    for (int i = 1; i < input.length; i++) {
      String[] friendCities = input[i].split(",");
      String key = friendCities[0];
      int count = 0;
      for (int j = 1; j < friendCities.length; j++) {
        if (myCities.contains(friendCities[j])) {
          count++;
        }
      }
      if (count >= limit) {
        res.add(new TravelFriend(key, count));
      }
    }

    Collections.sort(res, new Comparator<TravelFriend>() {
      @Override
      public int compare(TravelFriend f1, TravelFriend f2) {
        return Integer.compare(f2.count, f1.count);
      }
    });

    for (TravelFriend tf : res) {
      System.out.println(tf.name);
    }
  }

  static Set<String> parseSet(String line) {
    // me, 1, 2, 3, 4
    String[] cities = line.split(",");
    Set<String> res = new HashSet<>();
    for (int i = 1; i < cities.length; i++) {
      res.add(cities[i]);
    }
    return res;
  }

  static class TravelFriend {
    String name;
    int count;

    TravelFriend(String name, int count) {
      this.name = name;
      this.count = count;
    }
  }
}
