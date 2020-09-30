package com.rostertwo;

public class BubbleSort implements Sortable{
  private final Object[] a;
  
  public BubbleSort(Object[] a) {
    this.a = a;
  }
  
  @Override
  public void sort(Object[] a) {
    boolean sorted = false;
    Comparable temp;
    while(!sorted) {
      sorted = true;
      for (int i = 0; i < a.length - 1; i++) {
        if (((Comparable)a[i]).compareTo(a[i+1]) > 0) {
          temp = (Comparable) a[i];
          a[i] = a[i+1];
          a[i+1] = temp;
          sorted = false;
        }
      }
    }
  }
  
 
}
