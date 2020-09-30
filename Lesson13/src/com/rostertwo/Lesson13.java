package com.rostertwo;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;


public class Lesson13 {

  public static void main(String[] args) throws Exception {
    // demonstrates OutOfMemoryError in HeapSpace thrown when trying to create a big number of arrays
    // with exponentially increasing number of elements
    task1();
    
    // demonstrates OutOfMemoryError in MetaSpace thrown when trying to load a big number of classes
    // Before running set the following parameter -XX:MaxMetaspaceSize=9M at command line and run as
    // java -XX:MaxMetaspaceSize=9M -cp <YOUR_CLASSPATH> com.rostertwo.Lesson13
    task2();
  }
  
  /**
   * demonstrates OutOfMemoryError thrown for Java Heap Space
   * @throws InterruptedException
   */
  private static void task1() throws InterruptedException {
    int multiplier = 1000;
    int end = 1000;
    for (int i = 0; i < end; i++) {
      int[] a = new int[multiplier];
      multiplier *= 10;
      // allow GC clean unused objects
      Thread.sleep(100);
    }
  }
  
  /**
   * demonstrates OutOfMemoryError thrown for MetaSpace
   */
  private static void task2() throws IOException, ClassNotFoundException {
    final String CLASS_NAME = "SomeClass";
    final String PACKAGE_NAME = "com.rostertwo";
    final int NUMBER_OF_CLASSES = 1000;
    for (int i = 0; i < NUMBER_OF_CLASSES; i++) {
      
      String src = String.format("package %s; public class %s { static { System.out.println(\"Hello from \" + %s.class.getName()); } }", PACKAGE_NAME, CLASS_NAME + i, CLASS_NAME + i);
      // Save source in .java file.
      File root = new File("/java");
      File sourceFile = new File(root, String.format("com/rostertwo/%s.java", CLASS_NAME + i));
      sourceFile.getParentFile().mkdirs();
      Files.write(sourceFile.toPath(), src.getBytes(StandardCharsets.UTF_8));
      
      // Compile source file.
      JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
      compiler.run(null, null, null, sourceFile.getPath());

      // Load and instantiate compiled class.
      URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
      Class<?> cls = Class.forName(String.format("com.rostertwo.SomeClass%d", i), true, classLoader); // Should print "Hello from com.rostertwo.SomeClass[0-9]".

    } // for i
  } // task2()
  
}