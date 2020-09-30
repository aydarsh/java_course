package com.rostertwo;

import java.util.Collections;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Generate a sentence
 */
public class SentenceGenerator {
  private static final int MAX_SENTENCE_LENGTH = 15;
  
  /**
   * Generates a sentence consisting of random letters
   * @return - returns sentence in String format
   */
  public static String generate() {
    return generate(null);
  }
  
  /**
   * Generates a sentence consisting of words from wordsArray
   * @param wordsArray - a String[] array of words to generate file
   * @return - returns sentence in String format
   */
  public static String generate(String[] wordsArray) {
    Random random = new Random();
    int sentenceLength = random.nextInt(MAX_SENTENCE_LENGTH) + 1;  // add 1 to avoid 0-length sentences
    StringBuilder sentence;
    
    if (null == wordsArray) {
      Supplier<String> initWord = WordGenerator::generate;
      sentence = Stream
          .generate(initWord)
          .limit(sentenceLength)
          .map(w -> w + (random.nextBoolean() ? ", " : " "))
          .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
    } else {
      sentence = Stream
          .of(wordsArray)
          .collect(Collectors.collectingAndThen(Collectors.toList(), collected -> {
            Collections.shuffle(collected);
            return collected.stream();
          }))
          .limit(sentenceLength)
          .map(w -> w + (random.nextBoolean() ? ", " : " "))
          .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
    }
    
    sentence.setCharAt(0, Character.toUpperCase(sentence.charAt(0)));
  
    // Предпоследний символ предложения может быть либо запятым, либо буквой
    // Если это запятая, то меняем на sentenceEndChar, если буква, то вставляем перед пробелом sentenceEndChar
    char sentenceEndChar = random.nextBoolean() ? '.' : (random.nextBoolean() ? '!' : '?');
    if (sentence.charAt(sentence.length() - 2) == ',') {
      sentence.setCharAt(sentence.length() - 2, sentenceEndChar);
    } else {
      sentence.insert(sentence.length() - 1, sentenceEndChar);
    }
  
    return sentence.toString();
  }
  
}
