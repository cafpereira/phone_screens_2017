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
import java.util.List;
// CLASS BEGINS, THIS CLASS IS REQUIRED
class Solution01_Kika {

    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public int checkWinner(List<List<String>> codeList,
                           List<String> shoppingCart) {

        int shoppingCardIndex = 0; // cart search pointer
        int shoppingCartSize = shoppingCart.size();
        boolean fruitFound = false; // if the fruit code was found

        // If cart is null or empty return 0
        if (null == shoppingCart || shoppingCart.isEmpty()) {
            return 0;
        }

        // If codeList is null or empty, customer is always a winner
        if (null == shoppingCart || shoppingCart.isEmpty()) {
            return 1;
        }

        // Iterate between list
        for (List<String> fruitCodeList : codeList) {

            if (null == fruitCodeList || fruitCodeList.isEmpty()) {
                fruitFound = true;
            } else {

                fruitFound = false;
                int fruitListSize = fruitCodeList.size();

                // If customer cart does not have enough items
                if (shoppingCardIndex + fruitListSize > shoppingCartSize) return 0;

                // Search the list
                while (!fruitFound && shoppingCardIndex + fruitListSize <= shoppingCartSize) {
                    int cartSearch = shoppingCardIndex;
                    fruitFound = true;

                    for (String fruit : fruitCodeList) {
                        if (fruit.equals("anything") || (fruit.equals(shoppingCart.get(cartSearch)))) {
                            cartSearch++;
                        } else {
                            fruitFound = false;
                            shoppingCardIndex++;
                            break;
                        }
                    }

                    // update cart index if list was found
                    if (fruitFound) shoppingCardIndex = cartSearch;
                }
            }
        }

        // Return if winner
        if (!fruitFound) {
            return 0;
        } else {
            return 1;
        }
    }
    // METHOD SIGNATURE ENDS
}