package com.rostertwo;

import java.util.Objects;

public class Person implements Comparable<Person>{
  @Override
  public int compareTo(Person o) {
//    Sort by gender, by age in descending order and then by name
    int result = this.sex.compareTo(o.sex);
    if (result == 0) {
      result = -this.age.compareTo(o.age);
    }
    if (result == 0) {
      result = this.name.compareTo(o.name);
    }
    
    return result;
  }
  
  enum Sex {MAN, WOMAN};
  
  private String name;
  private Integer age;
  private Sex sex;
  
  @Override
  public String toString() {
    return "Person{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", sex='" + sex + '\'' +
        '}';
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
  
  public Person(String name, int age, Sex sex) {
    this.name = name;
    this.age = age;
    this.sex = sex;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
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
}
