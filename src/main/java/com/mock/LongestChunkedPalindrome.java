package com.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LongestChunkedPalindrome {

// Given a string, the task is to return the length of its longest possible chunked palindrome.
// It means a palindrome formed by substring in the case when it is not formed by characters of the string.

//Input : ghiabcdefhelloadamhelloabcdefghi
//Output : 7
//(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)

  private static final Logger logger = LoggerFactory.getLogger(LongestChunkedPalindrome.class);

  public static void main(String[] args) {
    logger.debug("LCP of: \"{}\" is: {}", "null", LCP(null));
    logger.debug("LCP of: \"{}\" is: {}", "empty", LCP(""));
    logger.debug("LCP of: \"{}\" is: {}", "V", LCP("V"));
    logger.debug("LCP of: \"{}\" is: {}", "VOLVO", LCP("VOLVO"));
    logger.debug("LCP of: \"{}\" is: {}", "VOLVOV", LCP("VOLVOV"));
    logger.debug("LCP of: \"{}\" is: {}", "ghiabcdefhelloadamhelloabcdefghi", LCP("ghiabcdefhelloadamhelloabcdefghi"));
  }

  public static int LCP(String str) {
    return longestChunkedPalin(str, 0);
  }

  public static int longestChunkedPalin(String str, int chunkCount) {
    if (str == null || str.isEmpty()) {
      return 0;
    }

    // if a single letter is left out
    if (str.length() == 1) {
      return chunkCount + 1;
    }

    // Try matching palindrome strings for increasingly larger chunk sizes
    for (int chunkSize = 1; chunkSize <= str.length() / 2; chunkSize++) {
      String left = substring(str, 0, chunkSize);
      String right = substring(str,str.length() - chunkSize, chunkSize);
      if (left.equals(right)) {
        String innerStr = substring(str, chunkSize, str.length() - (2 * chunkSize));
        // Pair of matching chunks found, increase chunk counter by 2
        return longestChunkedPalin(innerStr, chunkCount + 2);
      }
    }

    // No pair of matching chunks was found, count the entire str as one chunk
    return chunkCount + 1;
  }

  public static String substring(String str, int start, int offset){
    return str.substring(start, start + offset);
  }
}
