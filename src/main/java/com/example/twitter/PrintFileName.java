package com.example.twitter;

/*
  ## Question 2: ##
  I want a function to print the file name of a path
  path = "/some/folder/structure/file.txt" -> "file"

  ## Question 3: ##
  overload it to work with windows slashes as well
  path = "oh\\no\\windowsname.ppt" -> "windowsname"

  ## Question 4: ##
  rewrite each of these functions as a test case
  console.log(getFileName('/some/folder/structure/file.txt') === 'file');
  console.log(getFileName1('oh\\no\\windowsname.ppt') === 'windowsname');

  ## Question 5: ##
  modify this to print a testname and whether it passed
  And it should print a summary at the bottom, for example 4/4 PASS, or 3/4 FAIL
*/
public class PrintFileName {

  public static String fileName(String path) {
    // Question 2 and 3
    boolean isWindowsPath = path.indexOf("\\") > 0;
    String delimeter = isWindowsPath ? "\\\\" : "/";
    String[] paths = path.split(delimeter);
    int last = paths.length  - 1;

    return paths[last].split("\\.")[0];
  }

  public static void main (String[] args) {
    // Question 4
    System.out.println(assertPath("oh\\no\\windowsname.ppt", "windowsname"));
    System.out.println(assertPath("/some/folder/structure/file.txt", "file"));
    System.out.println(assertPath("", ""));
    System.out.println(assertPath("/some/folder/fdofhdsaif/name.ppt", "name"));

    // Question 5 (pseudo-code)
    // int total = list.size();
    // int success = for ((in, exp) in list : runTest(in, exp)).filter(true).count()
    // print '4/4'
  }

  public static boolean assertPath(String input, String expected) {
    return expected.equals(fileName(input));
  }
}
