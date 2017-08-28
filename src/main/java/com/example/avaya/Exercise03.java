package com.example;

class Solution03 {
    public static void main(String[] args) {
        System.out.println("solution(): " + solution(10, 10,
                new int[]{1,1,5,1,1})
        );
    }

    public static int solution(int X, int Y, int[] A) {
        int N = A.length;
        int result = -1;
        int nX = 0;
        int nY = 0;
        for (int i = 0; i < N; i++) {
            if (A[i] == X)
                nX += 1;
// FIXME  else if (A[i] == Y)
            if (A[i] == Y)
                nY += 1;
            if (nX == nY)
                result = i;
        }
        return result;
    }
}


