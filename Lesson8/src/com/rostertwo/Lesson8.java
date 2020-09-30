package com.rostertwo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * Дан массив случайных чисел. Написать программу для вычисления факториалов всех элементов массива в многопоточном режиме. Использовать пул потоков для решения задачи.
 * Особенности выполнения:
 * Для данного примера использовать рекурсию - не очень хороший вариант, т.к. происходит большое выделение памяти, очень вероятен StackOverFlow. Лучше перемножать числа в простом цикле при этом создавать объект типа BigInteger
 * По сути, есть несколько способа решения задания:
 * 1) распараллеливать вычисление факториала для одного числа
 * 2) распараллелить вычисления для разных чисел
 * 3) комбинированный
 * При чем вычислив факториал для одного числа, можно запомнить эти данные и использовать их для вычисления другого, что будет гораздо быстрее .
 */

public class Lesson8 {
  private static final int COUNT = 10;   // Number of integers to compute factorial
  private static final int UPPER_BOUND = 1000;   // upper bound (inclusive) of integers to generate
  private static final int POOL_SIZE = 4;  // number of threads
  
  public static void main(String[] args) {
    Random random = new Random();
    // generate an array of random integers between 0 (inclusive) and UPPER_BOUND (inclusive)
    Integer[] arrayIntegers = Stream
        .generate(() -> random.nextInt(UPPER_BOUND + 1))
        .limit(COUNT)
        .toArray(Integer[]::new);
    
    // create a pool of threads
    ExecutorService executorService =
        Executors.newFixedThreadPool(POOL_SIZE);
    
    // create a collection of callable tasks
    List<Callable<String>> callableTasks = new ArrayList<>();
    Arrays.stream(arrayIntegers)
        .forEach((n) -> callableTasks.add(new FactorialTask(n)));
  
    try {
      // submit tasks to ExecutorService instance
      List<Future<String>> futures = executorService.invokeAll(callableTasks);
      // retrieve results and print them
      futures.forEach((n) -> {
        try {
          System.out.println(n.get());
        } catch (InterruptedException | ExecutionException e) {
          e.printStackTrace();
        }
      });

      // shutdown the executorService
      executorService.shutdown();
      executorService.awaitTermination(5, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
  }
}
