package com.rostertwo;

import java.util.Random;

/**
 * Generate a word
 */
public class WordGenerator {
  private static final int LEFT_LIMIT = 97; // letter 'a'
  private static final int RIGHT_LIMIT = 122; // letter 'z'
  private static final int MAX_WORD_LENGTH = 15;
  
  /**
   * Generates a word from random letters
   * @return - returns word in String format
   */
  public static String generate() {
    Random random = new Random();
    int wordLength = random.nextInt(MAX_WORD_LENGTH) + 1;  // add 1 to avoid 0-length words
    String word = random
        .ints(wordLength, LEFT_LIMIT, RIGHT_LIMIT + 1)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
    return word;
  }
}
