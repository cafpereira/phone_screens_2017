package com.example.pandora;

/**
 * https://codebunk.com/b/811167760/
 */

public class StringToIntConverter {
  public static int convertInt(String str) {
    int sum = 0;
    for (char c : str.toCharArray()) {
      int val = c - '0';
      sum = (sum * 10) + val;
    }
    return sum;
  }

  public static float convertDec(String str) {
    String[] sides = str.split("\\.");

    int operandA = convertInt(sides[0]);
    int operandB = convertInt(sides[1]);

    double denom = Math.pow(10, sides[1].length());
    float decimal =  operandB / (float) denom;

    return operandA + decimal;
  }

  public static void main(String[] args) {
    System.out.println("convert: 3560 = " + convertInt("3560"));
    System.out.println("convert: 3560.501 = " + convertDec("3560.501"));
  }
}
