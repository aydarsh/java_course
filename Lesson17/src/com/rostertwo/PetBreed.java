package com.rostertwo;

public class PetBreed extends PetDecorator {
  private final String breed;
  private final Animal pet;
  
  public PetBreed(String breed, Animal pet) {
    this.breed = breed;
    this.pet = pet;
  }
  
  @Override
  public String toString() {
    return "PetBreed{" +
        "breed='" + breed + '\'' +
        ", pet=" + pet +
        '}';
  }
}
