package com.rostertwo;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Generate a paragraph
 */
public class ParagraphGenerator {
  private static final int MAX_PARAGRAPH_LENGTH = 20;
  
  /**
   * Generates a paragraph consisting of random letters
   * @return - returns paragraph in String format
   */
  public static String generate() {
    return generate(null);
  }
  
  /**
   * Generates a paragraph consisting of words from wordsArray
   * @param wordsArray - a String[] array of words to generate file
   * @return - returns paragraph in String format
   */
  public static String generate(String[] wordsArray) {
    Random random = new Random();
    int paragraphLength = random.nextInt(MAX_PARAGRAPH_LENGTH) + 1;  // add 1 to avoid 0-length paragraphs
    StringBuilder paragraph;
    Supplier<String> initSentence;
    
    if (null == wordsArray) {
      initSentence = SentenceGenerator::generate;
    } else {
      initSentence = () -> SentenceGenerator.generate(wordsArray);
    }
  
    paragraph = Stream
        .generate(initSentence)
        .limit(paragraphLength)
        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
    paragraph.append("\n\r");
    
    return paragraph.toString();
  }
}
