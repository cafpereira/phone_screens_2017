package com.companies.amazon.alexa.aug2017;

import java.util.HashSet;
import java.util.Set;

/**
 If a=1, b=2, c=3,....z=26. Given a string, find all possible codes that string can generate. Give a count as well as print the strings.

 For example:
 Input: "1123". You need to general all valid alphabet codes from this string.

 Output List
 aabc //a = 1, a = 1, b = 2, c = 3
 kbc // since k is 11, b = 2, c= 3
 alc // a = 1, l = 12, c = 3
 aaw // a= 1, a =1, w= 23
 kw // k = 11, w = 23

 */
public class DecodeWays_CountAndPrint {

    public static void main(String[] args) {
//        String s = "1123";
//        String s = "101";
        String s = "30";
        System.out.println("count ways: " + gen(s, ""));
    }

    static int gen(String s, String perm) {
        if (s.isEmpty()) {
            System.out.println(perm);
            return 1;
        }

        // zero itself is not mapped to any letter
        if (s.charAt(0) == '0') {
            return 0;
        }

        int c = 0;
        c += gen(s.substring(1), perm + code(Integer.parseInt(s.substring(0, 1))));
        if (s.length() > 1) {
            int numCode = Integer.parseInt(s.substring(0, 2));
            // Two-digits code number can go only up to 26
            if (numCode <= 26) {
                c += gen(s.substring(2), perm + code(numCode));
            }
        }
        return c;
    }

    static char code(int num) {
        return (char) ('a' + num - 1);
    }
}


