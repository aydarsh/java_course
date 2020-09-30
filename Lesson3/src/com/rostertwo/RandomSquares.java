package com.rostertwo;


import java.util.Random;
import java.util.stream.DoubleStream;

public class RandomSquares {
  static final int N = 1000;
  static final int MAX = 1000;
  static final int MIN = -100;
  
  public static void main(String[] args) {
    double[] randomDoubles = new double[N];
    for (int i = 0; i < N; i++) {
      randomDoubles[i] = (int) (Math.random() * (MAX - MIN) + MIN);
    }
    
    for (double randomDouble: randomDoubles) {
      System.out.print(randomDouble + " ");
    }
    System.out.println("");
    
    try {
      for (double randomDouble: randomDoubles) {
        if (randomDouble < 0.0) throw new IllegalArgumentException();
        double q = Math.sqrt(randomDouble);
        int qInt = (int) q;
        if (qInt * qInt == randomDouble) {
          System.out.print(randomDouble + " ");
        }
      }
    } catch (IllegalArgumentException ex) {
      System.out.println("Negative number in the array");
    }
    
    
    
  }
  
}
