package com.rostertwo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lesson7 {
  public static void main(String[] args) {
    
    runTask1();
    runTask2();
  } // main
  
  /**
   * Написать программу, читающую текстовый файл.
   * Программа должна составлять отсортированный по алфавиту список слов, найденных в файле и сохранять его в файл-результат.
   * Найденные слова не должны повторяться, регистр не должен учитываться.
   * Одно слово в разных падежах – это разные слова.
   */
  public static void runTask1() {
    // words.txt - sample text file
    Path inputFileName = Paths.get("src\\com\\rostertwo\\words.txt");
    Set<String> words = new TreeSet<>();
    
    try (BufferedReader br = Files.newBufferedReader(inputFileName)) {
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
            .forEach(words::add);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    Path outputFileName = Paths.get("src\\com\\rostertwo\\outputWords.txt");
    try (PrintWriter pw = new PrintWriter(Files.newBufferedWriter(outputFileName))) {
      words.forEach(pw::println);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Создать генератор текстовых файлов, работающий по следующим правилам:
   *  • Предложение состоит из 1<=n1<=15 слов. В предложении после произвольных слов могут находиться запятые.
   *  • Слово состоит из 1<=n2<=15 латинских букв
   *  • Слова разделены одним пробелом
   *  • Предложение начинается с заглавной буквы
   *  • Предложение заканчивается (.|!|?)+" "
   *  • Текст состоит из абзацев. в одном абзаце 1<=n3<=20 предложений. В конце абзаца стоит разрыв строки и перенос каретки.
   */
  public static void runTask2() {
    
    String path = "src\\com\\rostertwo\\";
    int countFiles = 5;
    int sizeFile = 10240;
    
    // read file created in task 1 and produce an array of words
    Path wordsFileName = Paths.get("src\\com\\rostertwo\\outputWords.txt");
    List<String> words = new ArrayList<>();
    try {
      Files
          .lines(wordsFileName)
          .forEach(words::add);
    } catch (IOException e) {
      e.printStackTrace();
    }
    String[] wordsArray = words.toArray(new String[words.size()]);
  
    getFiles(path, countFiles, sizeFile, wordsArray);
  }
  
  /**
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
