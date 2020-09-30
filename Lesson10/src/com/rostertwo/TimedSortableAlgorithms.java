package com.rostertwo;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

/**
 * 2. Создать прокси класс, который замеряет время работы методов базового класса и выводит результат в некий файл
 * @param <T>
 */
public class TimedSortableAlgorithms<T extends SortableAlgorithms<?>> implements InvocationHandler {
  private static final String OUTPUT_FILE_NAME = "src\\com\\rostertwo\\timedSortableAlgorithms.txt";
  private final T t;
  
  public TimedSortableAlgorithms(T t) {
    this.t = t;
  }
  
  /**
   * Overrides invoke method of the InvocationHandler interface.
   * Measures time required to sort an array
   * @param proxy - object to proxy
   * @param method - sorting method
   * @param args - array of sorting method arguments. Contains one element - array to sort
   * @throws Throwable
   */
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    long start = System.nanoTime();
    Object o = method.invoke(t, args);
    long finish = System.nanoTime();
    long timeElapsed = TimeUnit.NANOSECONDS.toMillis(finish - start);
    writeTiming((Object[]) args[0], method, timeElapsed);
    return o;
  }
  
  /**
   * Writes elapsed time into OUTPUT_FILE_NAME
   * @param array - this is the argument array of the sorting methods. It is used to retrieve array length
   * @param method - this value is required to get the name of the sorting method
   * @param timeElapsed - elapsed time of the sorting method run
   */
  private void writeTiming(Object[] array, Method method, long timeElapsed) throws IOException {
    Path outputFilePath = Paths.get(OUTPUT_FILE_NAME);
    if (Files.notExists(outputFilePath)) Files.createFile(outputFilePath);
    String contentToAppend =
        "Array length: " + array.length + ", "
            + method.getName()
            + ", time elapsed: " + timeElapsed + "\n";
    Files.write(
          outputFilePath,
          contentToAppend.getBytes(),
          StandardOpenOption.APPEND);
  }
}
