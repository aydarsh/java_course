package com.rostertwo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * 1. Установить JDK 14, создать объект Record, а также воспроизвести “разговорчивый NPE”.
 * 2. Создать программу, которая на вход получает дату рождения человека в виде ДД-ММ-ГГГГ, необходимо вывести, родился ли человек в выходной день или в будний.
 */
public class Lesson12 {
  public static void main(String[] args) {
    // creates record objects for Person and Pet Records, prints helpful NPE message
//    task1();
    
    // gets birthday from console and prints if it was a weekday or weekend day
    task2();
  }
  
  /**
   * gets birthday from console and prints if it was a weekday of weekend day
   */
  private static void task2() {
    System.out.print("Enter your birthday in dd-MM-yyyy format: ");
    Scanner scanner = new Scanner(System.in);
    String dateInput = scanner.nextLine();
    String datePattern = "dd-MM-yyyy";
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(datePattern);
    LocalDate localDate = LocalDate.parse(dateInput, dateTimeFormatter);
    DayOfWeek dayOfWeek = localDate.getDayOfWeek();
    int dayOfWeekInt = dayOfWeek.getValue();
    if (dayOfWeekInt == 6 || dayOfWeekInt == 7) {
      System.out.println("You were born at the week end: " + dayOfWeek);
    } else {
      System.out.println("You were born at a week day: " + dayOfWeek);
    }
  }
  
  /**
   * creates record objects for Person and Pet Records
   * prints helpful NPE message
   * IDE configuration: Edit Configurations -> VM Options: -XX:+ShowCodeDetailsInExceptionMessages -> OK
   */
  private static void task1() {
    // create an instance of Person record
    Person person1 = new Person("Aydar", 39, Sex.MAN);
    System.out.println(person1);
    
    // create an instance of Pet record with null for petOwner
    Pet pet1 = new Pet("Belka", null, 12);
    System.out.println(pet1);
    
    // generate NPE
    System.out.println(pet1.petOwner().sex());
  }
}



