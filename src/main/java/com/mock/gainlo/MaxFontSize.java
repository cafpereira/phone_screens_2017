package com.mock.gainlo;

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
    public Dimension (int width, int height) {
      this.width = width;
      this.height = height;
    }
}

public class MaxFontSize {
  public static int MIN = 1;
  public static int MAX = 100;

  public static int getMaxFontSize(Dimension screen, String str) {
    for (int font = MIN + 1; font <= MAX; font++) {
      String wrapped = wrap(str, font, screen.width);
      Dimension cur = calc(wrapped, font);
      if (cur.height > screen.height || cur.width > screen.width) {
        return font - 1;
      }
    }
    return MAX;
  }

  public static String wrap(String text, int font, int screenWidth) {
    int pixelsLeft = screenWidth;
    String[] words = text.split("\\s");
    StringBuilder builder = new StringBuilder();
    for (String w : words) {
      int wordWidth = calc(w + " ", font).width;
      if (wordWidth <= pixelsLeft) {
        builder.append(w + " ");
        pixelsLeft -= wordWidth;
      }
      else {
        builder.append("\n" + w + " ");
        pixelsLeft = screenWidth  - wordWidth;
      }
    }
    return builder.toString();
  }

  /*
    Fake implementation of calc(..) just for debugging purposes
  */
  public static Dimension calc(String str, int fontSize) {
    String[] lines = str.split("\\n");
    int height = lines.length * fontSize;
    int width = 0;
    for (String line : lines) {
      width = Math.max(width, line.length() * fontSize);
    }
    return new Dimension(width, height);
  }

  public static void main(String[] args) {
    System.out.println("getMaxFontSize(text, 480x320) = " + getMaxFontSize(new Dimension(480, 320), "Today is a good day"));
  }
}
