package com.rostertwo;

import java.util.*;

public class Lesson3 {
  final static int COUNT = 10_000;
  
  public static void main(String[] args) {
    List<Person> people = new ArrayList<Person>();
    
    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 5;
    Random random = new Random();
    
    for (int i = 0; i < COUNT; i++) {
      String name = random.ints(leftLimit, rightLimit + 1)
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();
      
      int age = random.nextInt(100);
      Person.Sex gender = random.nextBoolean() ? Person.Sex.MAN : Person.Sex.WOMAN;
      people.add(new Person(name, age, gender));
    }
    
    Person p1 = new Person("aydar", 39, Person.Sex.MAN);
    Person p2 = new Person("aydar", 41, Person.Sex.MAN);
    people.add(p1);
    people.add(p2);
    
    System.out.println("********** By Gender, then by Age Reversed, then by Name **********");
    Person[] peopleArray = new Person[people.size()];
    peopleArray = people.toArray(peopleArray);
    InsertionSort insertionSort = new InsertionSort(peopleArray);
    long start = System.nanoTime();
    insertionSort.sort(peopleArray);
    long finish = System.nanoTime();
    long timeElapsed = (finish - start)/1_000_000;
//    for (Person person: peopleArray) {
//      System.out.println(person);
//    }
    System.out.println("Insertion sort, elapsed time: " + timeElapsed + " ms");
    
    peopleArray = people.toArray(peopleArray);
    BubbleSort bubbleSort = new BubbleSort(peopleArray);
    start = System.nanoTime();
    bubbleSort.sort(peopleArray);
    finish = System.nanoTime();
    timeElapsed = (finish - start)/1_000_000;
//    for (Person person: peopleArray) {
//      System.out.println(person);
//    }
    System.out.println("Bubble sort, elapsed time: " + timeElapsed + " ms");
    
    List<Person> peopleWithoutDuplicates = new ArrayList<>(new HashSet<>(people));
    if (people.size() != peopleWithoutDuplicates.size()) throw new MyException("People with the same name and age found");
    
  }
}
