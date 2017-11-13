package com.example.mock.pramp.interview01;


import java.util.*;

class Solution02 {
    static double findGrantsCap(double[] grantsArray, double newBudget) {
       int n = grantsArray.length;

        List<Double> grantsList = new ArrayList<>();
        for (double d : grantsArray) {
            grantsList.add(d);
        }


    // sort the array in a descending order.
        Collections.sort(grantsList, Comparator.reverseOrder());

    // pad the array with a zero at the end to
    // cover the case where 0 <= cap <= grantsArray[i]
        grantsList.add(0.0);

    // calculate the total amount we need to
    // cut back to meet the reduced budget
        double sum = grantsList.stream().reduce(0.0, (i,j) -> i + j);
        double surplus = sum - newBudget;

    // if there is nothing to cut, simply return
    // the highest grant as the cap. Recall that
    // the grants array is sorted in a descending
    // order, so the highest grant is positioned
    // at index 0
        if (surplus <= 0) {
            return grantsList.get(0);
        }

    // start subtracting from surplus the
    // differences (“deltas”) between consecutive
    // grants until surplus is less or equal to zero.
    // Basically, we are testing out, in order, each
    // of the grants as potential lower bound for
    // the cap. Once we find the first value that
    // brings us below zero we break
        int i = 0;
        for ( ; i < n; i++) {
            surplus -= (i + 1) * (grantsList.get(i) - grantsList.get(i+1));
            if (surplus <= 0 ) {
                break;
            }
        }

    // since grantsArray[i+1] is a lower bound
    // to our cap, i.e. grantsArray[i+1] <= cap,
    // we  need to add to grantsArray[i+1] the
    // difference: (-total / float(i+1), so the
    // returned value equals exactly to cap.
        return grantsList.get(i+1) + (-surplus / (i + 1));
    }

    public static void main(String[] args) {
//        double cap = Solution02.findGrantsCap(new double[]{2, 100, 50, 120, 1000}, 190);
        double cap = Solution02.findGrantsCap(new double[]{2, 100, 50, 120, 1000}, 5);
        System.out.println("findGrantsCap() = " + cap);
    }
}
