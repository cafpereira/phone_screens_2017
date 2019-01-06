package com.companies.amazon.alexa;

/**
 * If a=1, b=2, c=3,....z=26. Given a string, find all possible codes that string can generate. Give a count as well as print the strings.
 * <p>
 * For example:
 * Input: "1123". You need to general all valid alphabet codes from this string.
 * <p>
 * Output List
 * aabc //a = 1, a = 1, b = 2, c = 3
 * kbc // since k is 11, b = 2, c= 3
 * alc // a = 1, l = 12, c = 3
 * aaw // a= 1, a =1, w= 23
 * kw // k = 11, w = 23
 */
public class DecodeWaysDynamicProg {

    /*
     * Similar to climbing stairs problem. For instance, if the string is "14",
     * imagine we have 2 steps labeled ['1', '4'], we can get to the destination
     * using one step at time (decode '1', then '4') or jumping 2 steps at once
     * which is the equivalent of decoding the combined string '14'.
     * The diference is that we need to address a few edge cases where we cannot
     * use any steps that start with zero, or jump two consective steps if the
     * combined result code is greater than 26.
     */
    public static int decodeWaysDP(String text) {
        int n = text.length();
        int[] table = new int[n + 1];

        // table[0] means an empty string will have one way to decode
        table[0] = 1;
        // table[1] means the way to decode a string of size 1
        table[1] = text.charAt(0) != '0' ? 1 : 0;

        for (int i = 2; i <= n; i++) {
            int first = Integer.valueOf(text.substring(i-1, i));
            int second = Integer.valueOf(text.substring(i-2, i));

            if (first >= 1 && first <= 9) {
                table[i] += table[i - 1];
            }
            if (second >= 10 && second <= 26) {
                table[i] += table[i - 2];
            }
        }
        return table[n];
    }


    public static void main(String[] args) {
        System.out.println("decodeWaysDP('121') = " + decodeWaysDP("121") + ", Expected: " + 3);
        System.out.println("decodeWaysDP('12321') = " + decodeWaysDP("12321") + ", Expected: " + 6);
        System.out.println("decodeWaysDP('1123') = " + decodeWaysDP("1123") + ", Expected: " + 5);
        System.out.println("decodeWaysDP('9090') = " + decodeWaysDP("9090") + ", Expected: " + 0);
    }
}


