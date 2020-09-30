package com.rostertwo;

import com.github.javafaker.Faker;

import java.util.*;

public class Lesson5 {
  final static int COUNT = 10;
  
  public static void main(String[] args) {
    PetCatalogue<Pet> pets = new PetCatalogue<>();
    Faker faker = new Faker();
    
    System.out.println("****************** Task 1 ******************");
    for (int i = 0; i < COUNT; i++) {
      String name = faker.name().firstName();
      int age = faker.number().numberBetween(0, 100);
      Person.Sex gender = faker.bool().bool() ? Person.Sex.MAN : Person.Sex.WOMAN;
      Person petOwner = new Person(name, age, gender);
      
      String petName = faker.dog().name();
      int petWeight = faker.number().numberBetween(1, 20);
      pets.add(new Pet(petName, petOwner, petWeight));
    }
    Pet pet1 = new Pet("Bobik", new Person("Aidar", 39, Person.Sex.MAN), 10);
    pets.add(pet1);
//    pets.add(pet1);    // Throws MyException
    Pet pet2 = new Pet("Bobik", new Person("Airat", 30, Person.Sex.MAN), 5);
    pets.add(pet2);
    Pet pet3 = new Pet("Harvey", new Person("Brooks", 20, Person.Sex.MAN), 8);
    pets.add(pet3);
    System.out.println(pets);
    
    System.out.println("****************** Task 2 ******************");
    List<Pet> petHarvey = pets.getByName("Harvey");
    System.out.println(petHarvey);
    
    System.out.println("****************** Task 3 ******************");
    String petId = pet1.getPetId();
    System.out.println(petId);
    pets.modify(petId, new Person("Aigul", 29, Person.Sex.WOMAN), 15);
    System.out.println(pets);
    
    System.out.println("****************** Task 4 ******************");
    Person person1 = new Person("Marat", 30, Person.Sex.MAN);
    Pet pet4 = new Pet("Laika", person1, 12);
    Pet pet5 = new Pet("Belka", person1, 12);
    Pet pet6 = new Pet("Laika", person1, 14);
    Pet pet7 = new Pet("Strelka", person1, 13);
    pets.add(pet4);
    pets.add(pet5);
    pets.add(pet6);
    pets.add(pet7);
    System.out.println(pets.getSorted());
  }
}
