package com.example.mock.gainlo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
https://docs.google.com/document/d/1V3WaypsfTp3cfhybc__oKAeIzz11Ru9oDMT7X2DcBSw/edit


String of lowercase latin letters
Q: given a dictionary, can that string be split into words?

input : w
1 >= w > N

Dic = {
  “Be”, “Bed”, “Bedin”, “bath”, “and”, “beyond”, “etc..”
}

bedinbathandbeyond
         ^    ^
A | a | a | a  a baab
1 + 2 + 3 + ..  + N = N2
a*

i=0
j=0,1,2

O(N + n2) = n2
 */
public class SplitWords {
  boolean canSplit(String w, Set<String> dic) {
    Map<String, Boolean> cache = new HashMap<>();
    return canSplit(w, dic, cache);
  }

  boolean canSplit(String w, Set dic, Map<String, Boolean> cache) {
    if(w.equals("")) {
      return true;
    }

    if(cache.containsKey(w)) {
      return cache.get(w);
    }

    for (int split = 0; split < w.length(); split++) {
      String before = w.substring(0, split + 1);
      String after = w.substring(split);
      if (dic.contains(before)) {
        if (canSplit(after, dic)) {
          cache.put(w, true);
          return true;
        }
      }
    }
      cache.put(w, false);
      return false;
    }
}
