package com.rostertwo;

import java.util.*;
import java.util.stream.Collectors;

public class PetCatalogue<T extends Pet> {
  private static PetCatalogue<?> petCatalogue = null;
  private final Map<String, T> petsById;
  
  private PetCatalogue() {
    petsById = new HashMap<>();
  }
  
  public static PetCatalogue<?> getInstance() {
    if (petCatalogue == null) {
      petCatalogue = new PetCatalogue<>();
    }
    return petCatalogue;
  }
  
  public boolean add(T t) {
    if (petsById.containsValue(t)) {
      throw new RuntimeException("Same pet found");
    } else {
      petsById.put(t.getPetId(), t);
    }
    return true;
  }
  
  public List<T> getByName(String petName) {
    return petsById
        .values()
        .stream()
        .filter((s) -> petName.equals(s.getPetName()))
        .collect(Collectors.toList());
  }
  
  public boolean modify(String petId, Person petOwner, int petWeight) {
    T petById = petsById.get(petId);
    petById.setPetOwner(petOwner);
    petById.setPetWeight(petWeight);
    return true;
  }
  
  public List<T> getSorted() {
    return petsById
        .values()
        .stream()
        .sorted()
        .collect(Collectors.toList());
  }
  
  @Override
  public String toString() {
    return "PetCatalogue{" +
        "pets=" + petsById +
        '}';
  }
  
}
