package com.example;

// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

import java.util.*;

class Solution02 {

    public static void main(String[] args) {
        run(new int[]{5,5,1,1,3,1,3,5,3,1});
    }

    public static void run(int[] A) {
        System.out.println("solution() = " + solution(A));
    }

    public static int solution(int[] A) {
        LinkedHashMap<Integer, Integer>  lastOccur = initLastOccurrences(A);
        int allCities = lastOccur.size();
        Subarray min = new Subarray(0, Integer.MAX_VALUE - 1);
        int visitedCities = 0;
        for (int i = 0; i < A.length; i++) {
            if (lastOccur.get(A[i]) == null) {
                visitedCities++;
            }
            lastOccur.remove(A[i]);
            lastOccur.put(A[i], i);
            if (visitedCities == allCities) {
                Integer start = getFirstEntry(lastOccur);
                min = minSubarray(min, new Subarray(start, i));
            }
        }
        return min.lenght();
    }

    private static Integer getFirstEntry(LinkedHashMap<Integer, Integer> lastOccur) {
        Integer first = null;
        for (Map.Entry<Integer, Integer> e : lastOccur.entrySet()) {
            first = e.getValue();
            break;
        }
        return first;
    }

    private static Subarray minSubarray(Subarray s1, Subarray s2) {
        return s1.compareTo(s2) <= 0 ? s1 : s2;
    }

    private static LinkedHashMap<Integer, Integer> initLastOccurrences(int[] A) {
        LinkedHashMap<Integer, Integer> lastOccur = new LinkedHashMap<>();
        for (int city : A) {
            lastOccur.put(city, null);
        }
        return lastOccur;
    }

    static class Subarray implements Comparable <Subarray>{
        final int start;
        final int end;

        public Subarray(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public Integer lenght() {
            return end - start + 1;
        }
        @Override
        public int compareTo(Subarray other) {
            return this.lenght().compareTo(other.lenght());
        }
    }

}
