package com.rostertwo;

import java.util.*;

public class PetCatalogue<T extends Pet> {
//  Comparator<T> comparator = new Comparator<>() {
//    @Override
//    public int compare(T t1, T t2) {
//      return t1.compareTo(t2);
//    }
//  };
  private Map<String, T> petsById;
  private Set<T> petsSorted;
  private Map<String, List<T>> petsByName;
  private List<T> petsList;
  
  public PetCatalogue() {
    petsById = new HashMap<>();
    petsSorted = new TreeSet<>();
    petsByName = new HashMap<>();
  }
  
  public boolean add(T t) {
    if (petsById.containsValue(t)) {
      throw new MyException("Same pet found");
    } else {
      petsById.put(t.getPetId(), t);
      petsSorted.add(t);
      petsByName.putIfAbsent(t.getPetName(), new ArrayList<T>());
      petsList = petsByName.get(t.getPetName());
      petsList.add(t);
      petsByName.put(t.getPetName(), petsList);
    }
    return true;
  }
  
  public List<T> getByName(String petName) {
    return petsByName.get(petName);
  }
  
  public boolean modify(String petId, Person petOwner, int petWeight) {
    T petById = petsById.get(petId);
    petById.setPetOwner(petOwner);
    petById.setPetWeight(petWeight);
    petsSorted.add(petById);
    return true;
  }
  
  public Set<T> getSorted() {
    return petsSorted;
  }
  
  @Override
  public String toString() {
    return "PetCatalogue{" +
        "pets=" + petsById +
        '}';
  }
  
}
