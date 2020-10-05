package com.rostertwo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Изменить приложение для сериализации данных. Сохранять объект в JSON с помощью одной из библиотек сериализации.
 */
public class Lesson21 {
  public static void main(String[] args) throws IOException {
  
    runTask1();
    
    runTask2();
    
  } // main
  
  /**
   * This method illustrates serialization of Word objects.
   * The task is from the Lesson7.
   * Написать программу, читающую текстовый файл.
   * Программа должна составлять отсортированный по алфавиту список слов, найденных в файле и сохранять его в файл-результат.
   * Найденные слова не должны повторяться, регистр не должен учитываться.
   * Одно слово в разных падежах – это разные слова.
   */
  public static void runTask1() throws IOException {
    
    Set<Word> words = new TreeSet<>();
    // words.txt - sample text file
    String inputFileName = "words.txt";
    InputStream is = getFileFromResourceAsStream(inputFileName);
    
    try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
         BufferedReader br = new BufferedReader(streamReader)) {
      String line;
      while ((line = br.readLine()) != null) {
        Stream
            .of(line.split(" "))
            .map(String::toLowerCase)
            .map(w -> w.replaceAll(",$",""))
            .map(w -> w.replaceAll("\\?$", ""))
            .map(w -> w.replaceAll("\\.$", ""))
            .map(w -> w.replaceAll("\\!$", ""))
            .map(w -> w.replaceAll("'$", ""))
            .map(Word::new)
            .forEach(words::add);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    // write words set in JSON format to outputFileName
    ObjectMapper objectMapper = new ObjectMapper();
    String outputFileName = "outputWords.json";
    objectMapper.writeValue(new FileOutputStream(outputFileName), words);
  }
  
  /**
   * Reads a file from the resources directory
   * @param fileName - input filename
   * @return - returns an input stream from the input file
   */
  private static InputStream getFileFromResourceAsStream(String fileName) {
    // The class loader that loaded the class
    ClassLoader classLoader = Lesson21.class.getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(fileName);
    
    // the stream holding the file content
    if (inputStream == null) {
      throw new IllegalArgumentException("file not found! " + fileName);
    } else {
      return inputStream;
    }
  }
  
  /**
   * This method shows deserialization of Word objects
   * The task is from the Lesson7
   * Создать генератор текстовых файлов, работающий по следующим правилам:
   *  • Предложение состоит из 1<=n1<=15 слов. В предложении после произвольных слов могут находиться запятые.
   *  • Слово состоит из 1<=n2<=15 латинских букв
   *  • Слова разделены одним пробелом
   *  • Предложение начинается с заглавной буквы
   *  • Предложение заканчивается (.|!|?)+" "
   *  • Текст состоит из абзацев. в одном абзаце 1<=n3<=20 предложений. В конце абзаца стоит разрыв строки и перенос каретки.
   */
  public static void runTask2() {
    
    String path = "";
    int countFiles = 5;
    int sizeFile = 10240;
    
    // read file created in task 1 and produce an array of words
    ObjectMapper objectMapper = new ObjectMapper();
    String inputFileName = "outputWords.json";
    
    String[] wordsArray = null;
    try (FileInputStream fin = new FileInputStream(inputFileName)) {
      // read JSON array into array of Word objects
      Word[] words = objectMapper.readValue(fin, Word[].class);
      // Retrieve string value of the word variable and create an array of them
      wordsArray = Arrays
          .stream(words)
          .map(Word::getWord)
          .toArray(String[]::new);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    
    // create files
    getFiles(path, countFiles, sizeFile, wordsArray);
  }
  
  /**
   * The task is from the Lesson7
   * Необходимо написать метод getFiles(String path, int n, int size, String[] words), который создаст n файлов размером size в каталоге path. words - массив слов.
   * @param path - каталог, в котором надо создать файлы
   * @param fileCount - количество файлов, которые надо создать
   * @param fileSize - размер создаваемых файлов
   * @param words - массив слов, из которых надо создать файлы
   */
  static void getFiles(String path, int fileCount, int fileSize, String[] words) {
    Supplier<InputStream> initFile = () -> FileGenerator.generate(fileSize, words);
    Consumer<InputStream> writeFile = (n) -> FileGenerator.write(n, path);

    Stream
        .generate(initFile)
        .limit(fileCount)
        .forEach(writeFile);
  } // getFiles
}
