package com.rostertwo;

import java.util.Objects;

public class Person implements Comparable<Person>{
  @Override
  public int compareTo(Person o) {
//    Sort by gender, by age in descending order and then by name
    int result = Integer.compare(this.sex.ordinal(), o.sex.ordinal());
    if (result == 0) {
      result = -this.age.compareTo(o.age);
    }
    if (result == 0) {
      result = this.name.compareTo(o.name);
    }
    
    return result;
  }
  
  enum Sex {MAN, WOMAN, UNSPECIFIED};
  
  private String name;
  private Integer age;
  private Sex sex;
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Person{name='" + name + '\'');
    if (age != -1) sb.append(", age=" + age);
    if (sex != Sex.UNSPECIFIED) sb.append(", sex='" + sex + '\'');
    sb.append('}');
    
    return sb.toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Person person = (Person) o;
    return age == person.age &&
        name.equals(person.name);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(name, age);
  }
  
  private Person(Builder builder) {
    this.name = builder.name;
    this.age = builder.age;
    this.sex = builder.sex;
  }
  
  public String getName() {
    return name;
  }
  
  public int getAge() {
    return age;
  }
  
  public void setAge(int age) {
    this.age = age;
  }
  
  public Sex getSex() {
    return sex;
  }
  
  public void setSex(Sex sex) {
    this.sex = sex;
  }
  
  public static class Builder {
    private final String name;
    private Integer age = -1;
    private Sex sex = Sex.UNSPECIFIED;
    
    public Builder(String name) {
      this.name = name;
    }
    
    public Builder withAge(int age) {
      this.age = age;
      return this;
    }
    
    public Builder withSex(Sex sex) {
      this.sex = sex;
      return this;
    }
    
    public Person build() {
      return new Person(this);
    }
  }
}
