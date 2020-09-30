package com.rostertwo;

import java.util.*;

public class MathBox<T extends Number> extends ObjectBox<T> {
  
  Set<T> numbersWithoutDuplicates;
  
  public MathBox(T[] numbersArray) {
    this.elementData = new ArrayList<>(Arrays.asList(numbersArray));
    this.numbersWithoutDuplicates = new HashSet<>(Arrays.asList(numbersArray));
    if (this.elementData.size() != this.numbersWithoutDuplicates.size()) {
      throw new MyException("Duplicate numbers in input");
    }
  }
  
  public double summator() {
    double sum = 0;
    for (T num : elementData) {
      sum = sum + num.doubleValue();
    }
    return sum;
  }
  
  public void splitter(Number divisor) {
    T quotient;
    for (int i = 0; i < elementData.size(); i++) {
      quotient = (T) new Double(elementData.get(i).doubleValue() / divisor.doubleValue());
      elementData.set(i, quotient);
    }
  }
  
  @Override
  public String toString() {
    return "MathBox{" +
        "numbersArrayList=" + elementData +
        '}';
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MathBox mathBox = (MathBox) o;
    return elementData.equals(mathBox.elementData);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(elementData);
  }
  
  public void remove(Integer value) {
    elementData.remove(value);
  }
  
  @Override
  public String dump() {
    return toString();
  }
}
