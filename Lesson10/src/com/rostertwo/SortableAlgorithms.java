package com.rostertwo;

/**
 * This interface imposes a sort method on the objects of each class that
 * implements it.
 *
 * @param <T> the type of objects that sort method can sort
 */
interface SortableAlgorithms<T extends Comparable<T>> {
  
  /**
   * Sorts an array of objects with BubbleSort algorithm
   * @param t
   */
  void bubbleSort(T[] t);
  
  /**
   * Sorts an array of objects with Arrays.sort() provided algorithm
   * @param t
   */
  void nativeSort(T[] t);
}
