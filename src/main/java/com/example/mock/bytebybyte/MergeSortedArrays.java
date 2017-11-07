package com.example.mock.bytebybyte;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
  Array of sorted arrays
  Merge all the arrays into a single sorted array
  merge({
    {1, 4, 7},
    {2, 5, 8},
    {3, 6, 9}
  })
  {1, 2, 3, 4, 5, 6, 7, 8, 9}
 */
public class MergeSortedArrays {

  public List<Integer> sortLines(int[][] lines) {
    int rows = lines.length;
    PriorityQueue<Data> heap = new PriorityQueue<>();
    List<Integer> heads = new ArrayList<>();

    for (int i = 0; i < rows; i++) {
      heads.add(0);
      heap.add(new Data(lines[i][0], i));
    }

    List<Integer> res = new ArrayList<>();
    while (!heap.isEmpty()) {
      Data min = heap.remove();
      res.add(min.value);

      int next = heads.get(min.row) + 1;
      if (next < lines[min.row].length) {
        heap.add(new Data(lines[min.row][next], min.row));
      }
    }
    return res;
  }

  class Data implements Comparable<Data> {
    int value;
    int row;

    public Data(int value, int row) {
      this.value = value;
      this.row = row;
    }

    @Override
    public int compareTo(Data other) {
      return Integer.compare(this.value, other.value);
    }
  }
}
