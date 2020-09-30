package com.rostertwo;

public class InsertionSort implements Sortable {
  private final Object[] a;
  
  public InsertionSort(Object[] a) {
    this.a = a;
  }
  
  @Override
  public void sort(Object[] a) {
    for (int i = 1; i < a.length; i++) {
      Comparable current = (Comparable) a[i];
      int j = i - 1;
      while(j >= 0 && current.compareTo(a[j]) < 0) {
        a[j+1] = a[j];
        j--;
      }
      a[j+1] = current;
    }
  }

}
