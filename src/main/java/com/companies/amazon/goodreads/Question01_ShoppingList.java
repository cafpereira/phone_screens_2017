package com.companies.amazon.goodreads;

/**
 <p>
 Bob’s fruit company is running a promotion in which customers receive prizes for purchasing a secret combination of items. The combination will change each day, so Bob wants to use a code list to make it easy to change the combination. The code list contains groups of fruits. Both the order of the groups within the code list and the order of the fruits within the groups matter. However, between the groups of fruits, any number, and type of fruit is allowable. The term “anything” is used to allow for any type of fruit to appear in that location within the group.
 <br>
 <br>
 Consider the following secret code list: [[apple, apple], [banana, anything, banana]]<br>Based on the above secret code list, a customer who made either of the following purchases would win the prize:
 <br>
 orange, apple, apple, banana, orange, banana
 <br>
 apple, apple, orange, orange, banana, apple, banana, banana
 <br>
 <br>
 Write an algorithm to output 1 if the customer is a winner else output 0.
 </p>
 */
// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
import java.util.Arrays;
import java.util.List;
// CLASS BEGINS, THIS CLASS IS REQUIRED

class Solution01
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public int checkWinner(List<List<String>> codeList,
                           List<String> shoppingCart)
    {
        // WRITE YOUR CODE HERE
        return checkWinner(codeList, shoppingCart, 0, 0) ? 1 : 0;
    }
    // METHOD SIGNATURE ENDS

    public boolean checkWinner(List<List<String>> codeList, List<String> shoppingCart,
                           int codeIdx, int cartIdx) {
        if (codeIdx == codeList.size()) {
            // All secret codes matched
            return true;
        }
        List<String> pattern = codeList.get(codeIdx);
        for (int i = cartIdx; i < shoppingCart.size(); i++) {
            String q = shoppingCart.get(i);
            if (q.equals(pattern.get(0))) {
                int lastMatch = matchPattern(shoppingCart, pattern, i);
                if (lastMatch > -1 && checkWinner(codeList, shoppingCart, codeIdx + 1, lastMatch)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int matchPattern(List<String> query, List<String> pattern, int cartIdx) {
        int curPatternIdx = 0;
        while (curPatternIdx < pattern.size() && cartIdx < query.size() &&
                (query.get(cartIdx).equals(pattern.get(curPatternIdx)) || (pattern.get(curPatternIdx).equals("anything")))){
            cartIdx++;
            curPatternIdx++;
        }
        return curPatternIdx == pattern.size() ? cartIdx : -1;
    }

    public static void main(String[] args) {
        Solution01 solution = new Solution01();

        //codelist: [[apple, apple], [banana, anything, banana]]
        List<List<String>> codeList = Arrays.asList(Arrays.asList("apple", "apple"), Arrays.asList("banana", "anything", "banana"));

        //cart1: orange, apple, apple, banana, orange, banana
        List<String> cart1 = Arrays.asList("orange", "apple", "apple", "banana", "orange", "banana");
        System.out.println("checkWinner( cart1 ) = " + solution.checkWinner(codeList, cart1));

        //cart2: apple, apple, orange, orange, banana, apple, banana, banana
        List<String> cart2 = Arrays.asList("apple", "apple", "orange", "orange", "banana", "apple", "banana", "banana");
        System.out.println("checkWinner( cart2 ) = " + solution.checkWinner(codeList, cart2));

        //cart3: apple, apple, orange, orange, apple, banana, banana
        List<String> cart3 = Arrays.asList("apple", "apple", "orange", "orange", "apple", "banana", "banana");
        System.out.println("checkWinner( cart3 ) = " + solution.checkWinner(codeList, cart3));
    }
}
