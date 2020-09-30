package com.rostertwo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PetCatalogue<T extends Pet> {
  private Map<String, T> petsById;
  
  public PetCatalogue() {
    petsById = new HashMap<>();
  }
  
  public boolean add(T t) {
    if (petsById.containsValue(t)) {
      throw new MyException("Same pet found");
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
