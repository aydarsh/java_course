package com.rostertwo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PetCatalogue<T extends Pet> {
  private static PetCatalogue<?> petCatalogue = null;
  private final Map<String, T> petsById;
  
  protected PetCatalogue() {
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
  
  public List<T> getSortedByPetName() {
    Comparator<T> petNameComparator
        = Comparator.comparing(T::getPetName);
    return petsById
        .values()
        .stream()
        .sorted(petNameComparator)
        .collect(Collectors.toList());
  }
  
  @Override
  public String toString() {
    return "PetCatalogue{" +
        "pets=" + petsById +
        '}';
  }
  
}
