package com.rostertwo;

import java.io.Serializable;

public class Word implements Serializable, Comparable<Word> {
  private static final long serialVersionUID = 1L;
  private String word;
  
  public Word() {}
  
  public Word(String word) {
    this.word = word;
  }
  
  public String getWord() {
    return word;
  }
  
  public void setWord(String word) {
    this.word = word;
  }
  
  public static long getSerialVersionUID() {
    return serialVersionUID;
  }
  
  @Override
  public int compareTo(Word o) {
    return this.word.compareTo(o.word);
  }
  
  @Override
  public String toString() {
    return "Word{" +
        "word='" + word + '\'' +
        '}';
  }
}
