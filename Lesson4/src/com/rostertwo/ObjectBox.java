package com.rostertwo;

import java.util.ArrayList;
import java.util.List;

public class ObjectBox<E> {
  protected List<E> elementData;
  
  public ObjectBox() {
    this.elementData = new ArrayList<>();
  }
  
  public void addObject(E e){
    elementData.add(e);
  }
  
  public void deleteObject(E e){
    elementData.remove(e);
  }
  
  public String dump() {
    return "ObjectBox{" +
        "elementData=" + elementData +
        '}';
  }
}
