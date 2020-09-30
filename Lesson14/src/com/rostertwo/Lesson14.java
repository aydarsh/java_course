package com.rostertwo;

import java.util.*;

public class Lesson14 {
  private static final int COUNT = 100;   // Number of integers
  private static final int UPPER_BOUND = 10_000;   // upper bound (inclusive) of integers to generate
  public static void main(String[] args) {
    // generate an array of random integers
    int[] arrayIntegers = generateIntegersArray(COUNT);
    // each element of this list is the sum of three integers
    List<Integer> listOfIntegerSums = calculateIntegerSums(arrayIntegers);
  
    // sorts the list of integer sums
    // backed by mergesort with O(n * lg n)
    Collections.sort(listOfIntegerSums);

    // print out sorted list
    listOfIntegerSums
        .forEach(System.out::println);
  }
  
  /**
   * Calculates sum of each three integers
   * Three nested loops, that is why O(n^3)
   * @param arrayIntegers - input integer array
   * @return - list of integers sums
   */
  private static List<Integer> calculateIntegerSums(int[] arrayIntegers) {
    List<Integer> listOfIntegerSumValues = new LinkedList<>();
    for (int i = 0; i < arrayIntegers.length - 2; i++) {
      for (int j = i + 1; j < arrayIntegers.length - 1; j++) {
        for (int k = j + 1; k < arrayIntegers.length; k++) {
          Integer sum = arrayIntegers[i] + arrayIntegers[j] + arrayIntegers[k];
          listOfIntegerSumValues.add(sum);
        } // for k
      } // for j
    } // for i
    
    return listOfIntegerSumValues;
  }
  
  
  /**
   * Generates an array of count number random integers between 0 (inclusive) and UPPER_BOUND (inclusive)
   * @return int[]
   */
  public static int[] generateIntegersArray(int count) {
    Random random = new Random();

    // generate IntStream of random integers and return as an array
    return random
        .ints(count, 0, UPPER_BOUND)
        .toArray();
  }
}
