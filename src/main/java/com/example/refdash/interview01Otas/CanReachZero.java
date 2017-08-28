package com.example.interview01Otas;

import java.util.Set;

public class CanReachZero {
    boolean canReachZero(int[] arr, int startIndex, Set<Integer> visited) {
        int jumpLen = arr[startIndex];
        if (jumpLen == 0) {
            return true;
        }

        // Can i go left?
        int leftJump = startIndex - jumpLen;
        if (jumpLen <= startIndex && visited.contains(leftJump)) {
            visited.add(leftJump);
            if (canReachZero(arr, leftJump, visited)){
                return true;
            }
        }

        // jump to the right
        int rightJump = startIndex + jumpLen;
        if (jumpLen < arr.length - startIndex && visited.contains(rightJump)) {
            visited.add(rightJump);
            return canReachZero(arr, rightJump, visited);
        }
        return false;
    }
}
