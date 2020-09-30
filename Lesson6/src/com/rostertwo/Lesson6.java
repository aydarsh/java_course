package com.rostertwo;

import com.github.javafaker.Faker;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Lesson6 {
  final static int COUNT = 10;
  
  public static void main(String[] args) {
    PetCatalogue<Pet> pets = new PetCatalogue<>();
    Faker faker = new Faker();
  
    System.out.println("****************** Task 1 ******************");
    Supplier<Pet> initPet = () -> {
      String name = faker.name().firstName();
      int age = faker.number().numberBetween(0, 100);
      Person.Sex gender = faker.bool().bool() ? Person.Sex.MAN : Person.Sex.WOMAN;
      Person petOwner = new Person(name, age, gender);
  
      String petName = faker.dog().name();
      int petWeight = faker.number().numberBetween(1, 20);
      return new Pet(petName, petOwner, petWeight);
    };
    Stream.generate(initPet).limit(COUNT).forEach(pets::add);
 
    Person petOwner1 = new Person("Aidar", 39, Person.Sex.MAN);
    Person petOwner2 = new Person("Airat", 30, Person.Sex.MAN);
    Person petOwner3 = new Person("Brooks", 20, Person.Sex.MAN);
    Pet pet1 = new Pet("Bobik", petOwner1, 10);
    pets.add(pet1);
//    pets.add(pet1);    // Throws MyException
    Pet pet2 = new Pet("Bobik", petOwner2, 5);
    pets.add(pet2);
    Pet pet3 = new Pet("Harvey", petOwner3, 8);
    pets.add(pet3);
    System.out.println(pets);
  
    System.out.println("****************** Task 2 ******************");
    List<Pet> petsBobik;
    petsBobik = pets.getByName("Bobik");
    System.out.println(petsBobik);
  
    System.out.println("****************** Task 3 ******************");
    String petId = pet1.getPetId();
    Person petOwner4 = new Person("Aigul", 29, Person.Sex.WOMAN);
    pets.modify(petId, petOwner4, 15);
    System.out.println(pets);
  
    System.out.println("****************** Task 4 ******************");
    Person petOwner5 = new Person("Marat", 30, Person.Sex.MAN);
    Pet pet4 = new Pet("Laika", petOwner5, 12);
    Pet pet5 = new Pet("Belka", petOwner5, 12);
    Pet pet6 = new Pet("Laika", petOwner5, 14);
    Pet pet7 = new Pet("Strelka", petOwner5, 13);
    pets.add(pet4);
    pets.add(pet5);
    pets.add(pet6);
    pets.add(pet7);
    System.out.println(pets.getSorted());
    System.out.println(pets.getByName("Laika"));
  }
}
