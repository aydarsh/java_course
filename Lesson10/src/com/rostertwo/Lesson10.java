package com.rostertwo;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.Console;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lesson10 {
  private static final int COUNT = 10_000;   // Number of integers
  private static final int UPPER_BOUND = 10_000;   // upper bound (inclusive) of integers to generate
  private static final String SOME_CLASS_SRC_PATH = "src\\com\\rostertwo\\SomeClass.java";   // path relative to Lesson10 class working directory  ..\..\..\
  
  public static void main(String[] args) throws Exception {
//    runTask1();
//    runTask2();
//    runTask3();
    runTask4();
  } // main
  
  /**
   * 1. Необходимо создать класс, который выполняет 2 вида сортировки массива (коллекции):
   * - Сортировка “пузырьком”;
   * - Сортировка с помощью стандартной java реализации - Arrays.sort() (или Collections.sort())
   */
  public static void runTask1() {
    // generate an array of random integers
    Integer[] arrayIntegers = generateIntegersArray();
    // create a copy of the array generated above
    Integer[] arrayIntegersCopy = Arrays.copyOf(arrayIntegers, arrayIntegers.length);
    
    SortableAlgorithms<Integer> sortableAlgorithms = new SortableAlgorithmsImpl<>();
    sortableAlgorithms.bubbleSort(arrayIntegers);
    sortableAlgorithms.nativeSort(arrayIntegersCopy);
  }
  
  /**
   * 2. Создать прокси класс, который замеряет время работы методов базового класса и выводит результат в некий файл
   */
  public static void runTask2() {
    // generate an array of random integers
    Integer[] arrayIntegers = generateIntegersArray();
    // create a copy of the array generated above
    Integer[] arrayIntegersCopy = Arrays.copyOf(arrayIntegers, arrayIntegers.length);
  
    // retrieve an instance of proxied SortableAlgorithmsImpl
    SortableAlgorithms<Integer> timedSortableAlgorithms = (SortableAlgorithms<Integer>) getTimedSortableAlgorithms();
  
    // run sorting algorithms with time measurements
    timedSortableAlgorithms.bubbleSort(arrayIntegers);
    timedSortableAlgorithms.nativeSort(arrayIntegersCopy);
  }
  
  /**
   * 3. Замерить разницу в скорости сортировки для 10000, 100000 и 1000000 элементов для каждого из методов. Для бОльшей достоверности, запустить измерения по 10 раз на каждый метод для каждого из заданных объемов
   */
  public static void runTask3() {
    // generate arrays of integers
    List<Integer[]> arrayListIntegers = new ArrayList<>();
    // Takes long time to sort 100_000 and 1_000_000 arrays, that is why the following values are taken
    arrayListIntegers.add(generateIntegersArray(1000));
    arrayListIntegers.add(generateIntegersArray(5000));
    arrayListIntegers.add(generateIntegersArray(10000));
    
    // create an instance of proxied class
    SortableAlgorithms<Integer> timedSortableAlgorithms = (SortableAlgorithms<Integer>) getTimedSortableAlgorithms();
    
    // a consumer that runs each measurement 10 times for each method and for each amount of integers
    Consumer<Integer[]> measureTimedSortableAlgorithms = (n) -> {
      for (int i = 0; i < 10; i++) {
        // create a copy of the original array for bubbleSort()
        Integer[] arrayIntegersCopy = Arrays.copyOf(n, n.length);
        timedSortableAlgorithms.bubbleSort(arrayIntegersCopy);
        // create another copy of the original array for nativeSort()
        arrayIntegersCopy = Arrays.copyOf(n, n.length);
        timedSortableAlgorithms.nativeSort(arrayIntegersCopy);
      }
    };
    
    // run measurements
    arrayListIntegers
        .forEach(measureTimedSortableAlgorithms);
  }
  
  /**
   * 4. Дан интерфейс
   * public interface Worker {
   *     void doWork();
   * }
   *
   * Необходимо написать программу, выполняющую следующее:
   * - Программа с консоли построчно считывает код метода doWork. Код не должен требовать импорта дополнительных классов.
   * - После ввода пустой строки считывание прекращается и считанные строки добавляются в тело метода public void doWork() в файле SomeClass.java.
   * - Файл SomeClass.java компилируется программой (в рантайме) в файл SomeClass.class.
   * - Полученный файл подгружается в программу с помощью кастомного загрузчика
   * - Метод, введенный с консоли, исполняется в рантайме (вызывается у экземпляра объекта подгруженного класса)
   */
  public static void runTask4() throws Exception {
    // read code for doWork() method from console line by line into inputCode list
    List<String> inputCode = readCodeFromConsole();
  
    // insert code entered in console to the doWork() method of SomeClass.java
    modifySourceCode(inputCode);
    
    // compile the modified source code
    Path classFile = compileSourceCode(Paths.get(SOME_CLASS_SRC_PATH));
    
    // run SomeClass.class
    runClass(classFile);
    
//    Worker worker = new SomeClass();
//    worker.doWork();
  }
  
  /**
   * reads code for doWork() method from console line by line into inputLines list
   * @return - list of entered in console lines
   */
  private static List<String> readCodeFromConsole() {
    Scanner scanner = new Scanner(System.in);
    List<String> inputLines = new ArrayList<>();
    String input = "start";
    System.out.println("Enter code for doWork() method: (empty line to quit)");
    while (!isBlankString(input)) {
      input = scanner.nextLine();
      if (!isBlankString(input)) inputLines.add(input);
    }
    return inputLines;
  }
  
  /**
   * reads source SomeClass.java,
   * inserts code entered from console into the doWork() method
   * @param inputLines - list of entered in console lines
   */
  private static void modifySourceCode(List<String> inputLines) throws IOException {
    // read lines from SomeClass.java
    List<String> srcLines = new ArrayList<>();
    Path sourcePath = Paths.get(SOME_CLASS_SRC_PATH);
    try {
      srcLines = Files.readAllLines(sourcePath);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    // find doWork() method's declaration line index and insert code read from the console
    int lineIndex = 0;
    for (String line : srcLines) {
      if (line.contains("public void doWork()")) {
        lineIndex = srcLines.indexOf(line);
      }
    }
    srcLines.addAll(lineIndex + 1, inputLines);
    
    // write lines to the SomeClass.java file
    String contentToWrite = srcLines
        .stream()
        .collect(Collectors.joining("\n"));
    Files.write(sourcePath, contentToWrite.getBytes());
  }
  
  /**
   * compiles javaFile
   * @param javaFile - path to source file
   * @return - path to SomeClass.class file
   */
  private static Path compileSourceCode(Path javaFile) {
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    compiler.run(null, null, null, javaFile.toFile().getAbsolutePath());
    return javaFile.getParent().resolve("SomeClass.class");
  }
  
  /**
   * runs the class
   * @param javaClass - SomeClass.class file location
   * @throws Exception
   */
  private static void runClass(Path javaClass) throws Exception {
    // replace SomeClass.class which was compiled during project build with the one compiled in comprileSourceCode() method
    Path copied = Paths.get("out\\production\\Lesson10\\com\\rostertwo\\SomeClass.class");
    Files.copy(javaClass, copied, StandardCopyOption.REPLACE_EXISTING);
  
    // load class, create new instance and run the method
    URL classUrl = javaClass.getParent().toFile().toURI().toURL();
    URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{classUrl});
    Class<?> cls = Class.forName("com.rostertwo.SomeClass", true, classLoader);
    SomeClass instance = (SomeClass) cls.newInstance();
    instance.doWork();
  }
  
  /**
   * Generates an array of COUNT number random integers between 0 (inclusive) and UPPER_BOUND (inclusive)
   * @return Integer[]
   */
  public static Integer[] generateIntegersArray() { return generateIntegersArray(null);}
  
  /**
   * Generates an array of count number random integers between 0 (inclusive) and UPPER_BOUND (inclusive)
   * @return Integer[]
   */
  public static Integer[] generateIntegersArray(Integer count) {
    Random random = new Random();
    if (null == count) {
      count = COUNT;
    }
    return Stream
        .generate(() -> random.nextInt(UPPER_BOUND + 1))
        .limit(count)
        .toArray(Integer[]::new);
  }
  
  /**
   * Wraps SortableAlgorithms' methods with timing
   * @return - returns a proxied instance of SortableAlgorithmsImpl class
   */
  private static SortableAlgorithms<?> getTimedSortableAlgorithms() {
    SortableAlgorithms<?> sortableAlgorithms = new SortableAlgorithmsImpl<>();
    TimedSortableAlgorithms<SortableAlgorithms<?>> handle = new TimedSortableAlgorithms<>(sortableAlgorithms);
    
    return (SortableAlgorithms<?>) Proxy.newProxyInstance(
        Lesson10.class.getClassLoader(),
        sortableAlgorithms.getClass().getInterfaces(),
        handle
    );
  }
  
  /**
   * Checks if string is blank
   * @param string - string to check if it is blank
   * @return - boolean
   */
  private static boolean isBlankString(String string) {
    return string == null || string.trim().isEmpty();
  }
}
