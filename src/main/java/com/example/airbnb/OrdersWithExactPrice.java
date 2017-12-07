package com.example.airbnb;
import java.io.*;
import java.util.*;

/*
 https://xkcd.com/287/

  ("mixed fruit", 2.15);
  ("french fries", 2.75);
  ("side salad", 3.35);
  ("hot wings", 3.55);
  ("mozzarella sticks", 4.20);
  ("sampler plate", 5.80);

  Examples:
  2.15 -> fruit
  3.00 -> []
  4.90 -> fruit, fries
  4.30 -> fruit, fruit
  7.95 -> fruit, plate
 */
public class OrdersWithExactPrice {
  public static void printOrders(double[] prices, String[] menu, double target) {
    printOrders(prices, menu, target, 0, 0.0, new ArrayList<>());
  }

  public static void printOrders(double[] prices, String[] menu, double target, int idx, double curSum, List<String> items) {
    int n = prices.length;
    if (idx == n || curSum > target) {
      return;
    }

    if (Math.abs(curSum - target) < 0.0001) {
      print(items);
      return;
    }

    for (int i = idx; i < n; i++) {
      items.add(menu[i]);
      printOrders(prices, menu, target, i, curSum + prices[i], items);
      items.remove(items.size() - 1);
    }
  }

  public static void print(List<String> list) {
    for (String s : list) {
      System.out.print(s + " ");
    }
  }

  public static void main(String[] args) {
    double[] prices = {2.15, 2.75, 3.35, 3.55, 4.20, 5.80};
    String[] menu = {"fruit","fries","salad","wings","mozz","plate"};

    System.out.println("-----");
    printOrders(prices, menu, 2.15);
    System.out.println("\n-----");
    printOrders(prices, menu, 3.00);
    System.out.println("\n-----");
    printOrders(prices, menu, 4.90);
    System.out.println("\n-----");
    printOrders(prices, menu, 4.30);
    System.out.println("\n-----");
    printOrders(prices, menu, 7.95);
    System.out.println("\n-----");
  }
}
