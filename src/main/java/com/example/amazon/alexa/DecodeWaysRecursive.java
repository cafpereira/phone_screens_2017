package com.example.amazon.alexa;

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
public class DecodeWaysRecursive {

    public static void main(String[] args) {
        String input = "1123";
        System.out.println("Response for " + input + ": " + get_interpretations(input));
    }

    public static int get_interpretations(String s){
        return get_branchs(s) + 1;
    }

    public static int get_branchs(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        if (Integer.parseInt(s.substring(0, 2)) <= 26) {
          if(!s.substring(0,2).contains("0"))
              return 1 + get_branchs(s.substring(1)) + get_branchs(s.substring(2));
          else
              return get_branchs(s.substring(2));
        } else {
            return get_branchs(s.substring(1));
        }
    }
}


