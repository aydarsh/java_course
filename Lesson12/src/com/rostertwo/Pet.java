package com.rostertwo;

import java.util.UUID;

public record Pet(String petId, String petName, Person petOwner, int petWeight) {
  public Pet(String petName, Person petOwner, int petWeight) {
    this(UUID.randomUUID().toString(), petName, petOwner, petWeight);
  }
}
