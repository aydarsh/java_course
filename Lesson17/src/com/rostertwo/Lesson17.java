package com.rostertwo;

import com.github.javafaker.Faker;

import java.util.List;
import java.util.Observer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Применить один из структурных паттернов для приложения «Картотека домашних животных»
 * Здесь в функции runTaskPattern показано применение паттерна Decorator для класса Pet
 */
public class Lesson17 {
  final static int COUNT = 10;
  
  public static void main(String[] args) {
    // creates a petCatalogue
    PetCatalogue<Pet> petCatalogue = (PetCatalogue<Pet>) PetCatalogue.getInstance();
    
//    // shows adding of pets to the catalogue
//    runTask1_1(petCatalogue);
//
//    // shows searching the catalogue by pet name
//    runTask1_2(petCatalogue);
//
//    // shows modifying a pet by its id
//    runTask1_3(petCatalogue);
//
//    // shows custom sorting of the pets catalogue
//    runTask1_4(petCatalogue);
    
    // shows Decorator pattern applied to Pet class
    runTaskDecorator(petCatalogue);
  }
  
  /**
   * Здесь показано применение паттерна Decorator для класса Pet
   * @param petCatalogue - картотека домашних животных
   */
  public static void runTaskDecorator(PetCatalogue<Pet> petCatalogue) {
    // generates a pet without an owner
    Animal pet17 = new Pet.Builder("Charlie")
        .build();
    pet17 = new PetBreed("Terrier", pet17);
    System.out.println(pet17);
  }
  
  /**
   * Реализовать метод добавления животного в общий список
   * (учесть, что добавление дубликатов должно приводить к исключительной ситуации)
   * @param petCatalogue - картотека домашних животных
   */
  public static void runTask1_1(PetCatalogue<Pet> petCatalogue) {
    // uses Faker to generate random name, age, gender
    Faker faker = new Faker();
    
    // provides a pet supplier for stream generator
    Supplier<Pet> initPet = () -> {
      // generate a pet owner name, age and gender
      String name = faker.name().firstName();
      int age = faker.number().numberBetween(0, 100);
      Person.Sex gender = faker.bool().bool() ? Person.Sex.MAN : Person.Sex.WOMAN;
      // build a pet owner
      Person petOwner = new Person.Builder(name)
          .withAge(age)
          .withSex(gender)
          .build();
      
      // generates a pet name and weight
      String petName = faker.dog().name();
      int petWeight = faker.number().numberBetween(1, 20);
      return new Pet.Builder(petName)
          .withPetOwner(petOwner)
          .withPetWeight(petWeight)
          .build();
    };
    // generates COUNT number of pets
    Stream.generate(initPet).limit(COUNT).forEach(petCatalogue::add);
    
    // builds additional pet owners with different optional parameter values
    Person petOwner1 = new Person.Builder("Aidar")
        .withAge(39)
        .withSex(Person.Sex.MAN)
        .build();
    
    Person petOwner3 = new Person.Builder("Brooks")
        .build();
    
    // shows that adding of duplicate pet generates an exception
    Pet pet1 = new Pet.Builder("Bobik")
        .withPetOwner(petOwner1)
        .withPetWeight(10)
        .build();
    petCatalogue.add(pet1);
//    petCatalogue.add(pet1);    // Throws a RuntimeException with "Same pet found" message
    
    // adds more pets to the catalogue
    Pet pet2 = new Pet.Builder("Bobik")
        .withPetWeight(5)
        .build();
    petCatalogue.add(pet2);
    Pet pet3 = new Pet.Builder("Harvey")
        .withPetOwner(petOwner3)
        .build();
    petCatalogue.add(pet3);
    Pet pet4 = new Pet.Builder("Minni")
        .withPetWeight(3)
        .build();
    petCatalogue.add(pet4);
    
    System.out.println(petCatalogue);
  }
  
  /**
   * Реализовать поиск животного по его кличке
   * @param petCatalogue - картотека домашних животных
   */
  private static void runTask1_2(PetCatalogue<Pet> petCatalogue) {
    List<Pet> petsBobik;
    petsBobik = petCatalogue.getByName("Bobik");
    
    System.out.println(petsBobik);
  }
  
  /**
   * Реализовать изменение данных животного по его идентификатору
   * @param petCatalogue - картотека домашних животных
   */
  private static void runTask1_3(PetCatalogue<Pet> petCatalogue) {
    // generates a pet without an owner
    Pet pet4 = new Pet.Builder("Buddy")
        .build();
    petCatalogue.add(pet4);
    
    // modifies the pet with an owner
    String petId = pet4.getPetId();
    Person petOwner4 = new Person.Builder("Kate")
        .build();
    petCatalogue.modify(petId, petOwner4, 15);
    
    System.out.println(petCatalogue);
  }
  
  /**
   * Реализовать вывод на экран списка животных в отсортированном порядке.
   * Поля для сортировки – хозяин(имя, по возрастанию),
   * если имена хозяев одинаковые - кличка животного.
   * Если и имя хозяина и кличка животного одинаковые - раньше вывести животное, у которого больше вес
   * @param petCatalogue - картотека домашних животных
   */
  private static void runTask1_4(PetCatalogue<Pet> petCatalogue) {
    Person petOwner5 = new Person.Builder("Marat")
        .build();
    Pet pet4 = new Pet.Builder("Laika")
        .withPetOwner(petOwner5)
        .withPetWeight(12)
        .build();
    Pet pet5 = new Pet.Builder("Belka")
        .withPetOwner(petOwner5)
        .withPetWeight(12)
        .build();
    Pet pet6 = new Pet.Builder("Laika")
        .withPetOwner(petOwner5)
        .withPetWeight(14)
        .build();
    Pet pet7 = new Pet.Builder("Strelka")
        .withPetOwner(petOwner5)
        .withPetWeight(13)
        .build();
    petCatalogue.add(pet4);
    petCatalogue.add(pet5);
    petCatalogue.add(pet6);
    petCatalogue.add(pet7);
    
    System.out.println(petCatalogue.getSorted());
  }
}
