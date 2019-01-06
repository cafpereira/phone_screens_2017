package com.mock.pramp.interview01;

import java.util.*;

class Solution01 {
    static double findGrantsCap(double[] grantsArray, double newBudget) {
        Arrays.sort(grantsArray);

        int n = grantsArray.length;
        double cap = newBudget / n;

        int i = 0;
        while (i < n) {
            double used = 0;
            while (i < n && grantsArray[i] < cap) {
                used += grantsArray[i++];
            };

            if (i < n) {
                newBudget = newBudget - used;
                cap = newBudget / (n - i);
            }

            while (i < n && grantsArray[i] >= cap) {
                i++;
            };
        }
        return cap;
    }

    public static void main(String[] args) {
//        double cap = Solution01.findGrantsCap(new double[]{2, 100, 50, 120, 1000}, 190);
        double cap = Solution02.findGrantsCap(new double[]{2, 100, 50, 120, 1000}, 5);
        System.out.println("findGrantsCap() = " + cap);
    }
}
