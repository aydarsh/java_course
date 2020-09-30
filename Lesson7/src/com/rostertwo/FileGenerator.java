package com.rostertwo;

import java.io.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Generate a file
 */
public class FileGenerator {
  private static int id = 0;  // used in fileName
  
  /**
   * Generates an int id
   * @return - returns integer id
   */
  private static int createId() {
    return ++id;
  }
  
  /**
   * Generates file contents consisting of random letters
   * @param size - size of file
   * @return - returns file contents in ByteArrayInputStream format
   */
  public static InputStream generate(int size) {
    return generate(size, null);
  }
  
  /**
   * Generates file contents consisting of words from wordsArray
   * @param size - size of file
   * @param wordsArray - a String[] array of words to generate file
   * @return - returns file contents in ByteArrayInputStream
   */
  public static InputStream generate(int size, String[] wordsArray) {
    Supplier<String> initParagraph;
    
    if (null == wordsArray) {
      initParagraph = ParagraphGenerator::generate;
    } else {
      initParagraph = () -> ParagraphGenerator.generate(wordsArray);
    }
    
    StringBuilder fileContents = Stream
        .generate(initParagraph)
        .limit(500)
        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
    byte[] arrayFileContents = fileContents.toString().getBytes();
    InputStream byteStreamFileContents = new ByteArrayInputStream(arrayFileContents, 0, size);
    
    return byteStreamFileContents;
  }
  
  /**
   * Writes file contents to file
   * @param fin - file contents in ByteArrayInputStream format
   * @param path - directory to write file to
   */
  public static void write(InputStream fin, String path) {
    String fileName = path + "file-" + createId() + ".txt";
    try (OutputStream fos = new FileOutputStream(fileName)) {
      byte[] buffer = new byte[fin.available()];
      // read to buffer
      fin.read(buffer, 0, buffer.length);
      // write to file
      fos.write(buffer, 0, buffer.length);
    } catch(IOException ex) {
      System.out.println(ex.getMessage());
    }
  }
}

