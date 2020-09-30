package com.rostertwo;

public class Lesson4 {
  public static void main(String[] args) {
    System.out.println("*********** Task 1 **********");
    Number[] numArray = {5, 4.3, 3.14f, (byte) 127, 10};
    MathBox<Number> mb = new MathBox(numArray);
    
    System.out.println(mb);
    
    System.out.println(mb.summator());
    
    mb.remove(5);
    System.out.println(mb);
    
    mb.splitter(2);
    System.out.println(mb);
    
    System.out.println("*********** Task 2 **********");
    Object obj1 = new Object();
    Object obj2 = new Object();
    Object obj3 = new Object();
    
    ObjectBox<Object> objBox = new ObjectBox<>();
    objBox.addObject(obj1);
    objBox.addObject(obj2);
    objBox.addObject(obj3);
    System.out.println(objBox.dump());
    objBox.deleteObject(obj2);
    System.out.println(objBox.dump());
    
    Integer int1 = new Integer(1000);
    ObjectBox<Integer> intBox = new ObjectBox<>();
    intBox.addObject(int1);
    Integer int2 = new Integer(1001);
    intBox.addObject(int2);
    System.out.println(intBox.dump());
    intBox.deleteObject(int2);
    System.out.println(intBox.dump());
    
    System.out.println("*********** Task 3 **********");
    mb.addObject(6);
    mb.addObject(int1);
    mb.addObject(123.2);
    System.out.println(mb);
    mb.deleteObject(5.0);
    System.out.println(mb);

//    mb.addObject(obj3);     // Throws an exception
  }
}
