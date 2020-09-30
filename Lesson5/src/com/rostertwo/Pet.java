package com.rostertwo;

import java.util.Objects;
import java.util.UUID;

public class Pet implements Comparable<Pet>{
  private String petId;
  private String petName;
  private Person petOwner;
  private int petWeight;
  
  public Pet(String petName, Person petOwner, int petWeight) {
    this.petId = UUID.randomUUID().toString();
    this.petName = petName;
    this.petOwner = petOwner;
    this.petWeight = petWeight;
  }
  
  public String getPetId() {
    return petId;
  }
  
  public void setPetId(String petId) {
    this.petId = petId;
  }
  
  public String getPetName() {
    return petName;
  }
  
  public void setPetName(String petName) {
    this.petName = petName;
  }
  
  public Person getPetOwner() {
    return petOwner;
  }
  
  public void setPetOwner(Person petOwner) {
    this.petOwner = petOwner;
  }
  
  public int getPetWeight() {
    return petWeight;
  }
  
  public void setPetWeight(int petWeight) {
    this.petWeight = petWeight;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pet pet = (Pet) o;
    return petWeight == pet.petWeight &&
        petName.equals(pet.petName) &&
        petOwner.equals(pet.petOwner);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(petName, petOwner, petWeight);
  }
  
  @Override
  public String toString() {
    return "Pet{" +
        "petId='" + petId + '\'' +
        ", petName='" + petName + '\'' +
        ", petOwner=" + petOwner +
        ", petWeight=" + petWeight +
        '}' + '\n';
  }
  
  @Override
  public int compareTo(Pet p) {
    int result = this.petOwner.compareTo(p.getPetOwner());
    if (result == 0) {
      result = this.petName.compareTo(p.getPetName());
    }
    if (result == 0) {
      result = ((Integer)this.petWeight).compareTo(p.petWeight);
    }
    
    return result;
  }
  
}
