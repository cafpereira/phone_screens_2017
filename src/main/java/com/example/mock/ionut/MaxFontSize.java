package com.example.mock.ionut;

/*
https://docs.google.com/document/d/18DV8BFyRCZFVd2Iy-oUTy3ttAM_e1Rca2w2qHtDQmdI/edit?ts=59c68611

// Returns the dimension that is going to be occupied by the input on the screen. Can handle ‘/n’
Dimension calc(String s, int fontSize);

Given a piece of text that contains [a-z][A-Z][“ “] and min/max font size, we want to word wrap and display the text with the maximum font size possible based on the screensize of the screen provided.

Today is a good day -> calc() 30 x 5

Today is a
Good day -> calc -> 15 x 12
 */

class Dimension {
    int width; //pixels
    int height; //pixels
}

public class MaxFontSize {
  public static int MIN = 0;
  public static int MAX = 84;

  public Dimension calc(String s, int fontSize){
    return null;
  }

  public int getMaxFontSize(Dimension scSize, String str) {
    for (int font = MIN + 1; font <= MAX; font++) {
      Dimension cur = calc(str, font);
      if (cur.height > scSize.height) {
        return font - 1;
      }
      if (cur.width > scSize.width) {
        // TODO
        str = wrap(str, font, scSize);
      }
    }
    return MAX;
  }

  public String wrap(String in, int font, Dimension scSize) {
    int curWid = 0;
    String[] words = in.split("\\s");
    StringBuilder builder = new StringBuilder();
    for (String w : words) {
      Dimension d = calc(w + " ", font);
      if (scSize.width < curWid + d.width) {
        builder.append(w + " ");
        curWid += d.width;
      }
      else {
        builder.append("\n" + w + " ");
      }
    }
    return builder.toString();
  }
}
