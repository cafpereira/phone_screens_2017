package com.example.pramp.interview02;

class Solution {

    static int calcDroneMinEnergy(int[][] route) {
        // your code goes here
        int initial = route[0][2];
        int n = route.length;
        int max = 0;
        for (int i = 1; i < n; i++) {
            if (route[i][2] > max){
                max = route[i][2];
            }
        }
        return max > initial ? max - initial : 0;
    }

    public static void main(String[] args) {

    }

}
