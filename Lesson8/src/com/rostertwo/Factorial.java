package com.rostertwo;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Computes factorial of an integer
 * factorial(int n) method performs computations iteratively,
 * additionally it uses a cache of computed values
 */
public class Factorial {
  private static Map<Integer, BigInteger> cache = new ConcurrentHashMap<>();
  
  /**
   * computes factorial of n
   * @param n - positive integer
   * @return - returns result in BigInteger format
   */
  public static BigInteger factorial(int n) {
    BigInteger result, temp;
    
    if (n == 0) return BigInteger.ONE;
    
    // return from cache if it is already computed
    if (null != (result = cache.get(n))) return result;
    
    // compute factorial iteratively
    // uses cached values if exist
    result = BigInteger.ONE;
    for (int i = n; i > 1 ; i--) {
      if (null != (temp = cache.get(i))) {
        result = result.multiply(temp);
        cache.put(n, result);
        return result;
      } else {
        result = result.multiply(BigInteger.valueOf(i));
      }
    }
    // put computed value to cache
    cache.put(n, result);
    return result;
  }
  
}
