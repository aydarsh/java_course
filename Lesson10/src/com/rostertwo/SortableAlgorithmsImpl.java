package com.rostertwo;

import java.util.Arrays;

/**
 * 1. Необходимо создать класс, который выполняет 2 вида сортировки массива (коллекции):
 * - Сортировка “пузырьком”;
 * - Сортировка с помощью стандартной java реализации - Arrays.sort() (или Collections.sort())
 */

/**
 * This class implements bubbleSort and nativeSort methods of the SortableAlgorithms interface
 *
 * @param <T> the type of objects that sort method can sort
 */
public class SortableAlgorithmsImpl<T extends Comparable<T>> implements SortableAlgorithms<T> {
  
  @Override
  public void bubbleSort(T[] t) {
    boolean sorted = false;
    T temp;
    while(!sorted) {
      sorted = true;
      for (int i = 0; i < t.length - 1; i++) {
        if (t[i].compareTo(t[i+1]) > 0) {
          temp = t[i];
          t[i] = t[i+1];
          t[i+1] = temp;
          sorted = false;
        }
      }
    }
  }
  
  @Override
  public void nativeSort(T[] t) {
    Arrays.sort(t);
  }
}
