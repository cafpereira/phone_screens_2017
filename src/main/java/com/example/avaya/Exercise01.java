package com.example.avaya;

// you can also use imports, for example:
 import java.time.*;
 import java.time.format.*;
 import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {

    static DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) {
        run("22:22:21", "22:22:23");
        run("15:15:00", "15:15:12");
        run("22:38:15", "22:38:15");
        run("01:01:01", "01:01:01");
        run("22:22:21", "45:89:10");
        run("22:22:21", null);
    }

    public static void run(String S, String T) {
        System.out.println("solution( " + S + ", " + T + ") = " + solution(S, T));
    }

    public static int solution(String S, String T) {
        if (S == null || T == null) {
            return 0;
        }
        try {
            LocalTime cur = LocalTime.parse(S, format);
            LocalTime end = LocalTime.parse(T, format);
            int solution = 0;

            while (cur.isBefore(end) || cur.equals(end)) {
                if (interestingTime(cur.format(format), 2)) {
                    solution++;
                }
                cur = cur.plusSeconds(1L);
            }
            return solution;
        } catch (Exception ex) {
            System.err.println("Unable to parse input times, S: " + S + ", T: " + T);
            return 0;
        }
    }

    private static boolean interestingTime(String timeStr, int capSize) {
        Set<Character> digits = new HashSet<>();
        for (char c : timeStr.toCharArray()) {
            if (c != ':') {
                digits.add(c);
            }
            // No need to keep adding more digits
            // if capSize is already reached
            if (digits.size() > capSize) {
                return false;
            }
        }
        return true;
    }
}
