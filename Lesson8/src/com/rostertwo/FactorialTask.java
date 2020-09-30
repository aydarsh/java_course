package com.rostertwo;

import java.security.InvalidParameterException;
import java.util.concurrent.Callable;

public class FactorialTask implements Callable<String> {
  int number;
  
  public FactorialTask(int number) {
    this.number = number;
  }
  
  @Override
  public String call() throws InvalidParameterException {
    if(number < 0) {
      throw new InvalidParameterException("Number should be positive");
    }
    // produce a ready to print string
    return "" + number + "! = " + Factorial.factorial(number);
  }
}
