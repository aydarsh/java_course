package com.rostertwo;

import java.util.Objects;
import java.util.UUID;

public class Pet implements Comparable<Pet> {
  private String petId;
  private String petName;
  private Person petOwner;
  private int petWeight;
  
  private Pet(Builder builder) {
    this.petId = UUID.randomUUID().toString();
    this.petName = builder.petName;
    this.petOwner = builder.petOwner;
    this.petWeight = builder.petWeight;
  }
  
  public String getPetId() {
    return petId;
  }
  
  public String getPetName() {
    return petName;
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
    return petId.equals(pet.petId);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(petId);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Pet{petId='" + petId + '\'' + ", name='" + petName + '\'');
    if (!petOwner.getName().equals("UNSPECIFIED")) sb.append(", petOwner=" + petOwner);
    if (petWeight != -1) sb.append(", petWeight='" + petWeight + '\'');
    sb.append("}\n");
  
    return sb.toString();
  }
  
  @Override
  public int compareTo(Pet p) {
    // sort by pet owner name (asc), then by pet name (asc), then by pet weight (desc)
    int result;
    if (this.petOwner.getName().equals("UNSPECIFIED") || p.petOwner.getName().equals("UNSPECIFIED")) {
      result = 0;
    } else {
      result = this.petOwner.getName().compareTo(p.getPetOwner().getName());
    }
    if (result == 0) {
      result = this.petName.compareTo(p.getPetName());
    }
    if (result == 0) {
      result = -Integer.compare(this.petWeight, p.petWeight);
    }
    
    return result;
  }
  
  public static class Builder {
    private final String petName;
    private Person petOwner = new Person.Builder("UNSPECIFIED").build();
    private int petWeight = -1;
    
    public Builder(String petName) {
      this.petName = petName;
    }
    
    public Builder withPetOwner(Person petOwner) {
      this.petOwner = petOwner;
      return this;
    }
    
    public Builder withPetWeight(int petWeight) {
      this.petWeight = petWeight;
      return this;
    }
    
    public Pet build() {
      return new Pet(this);
    }
  }
}

