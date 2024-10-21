package org.example;

public class MaxSizeException extends RuntimeException {

  public MaxSizeException(int size, TypeSort type) {
    super("Этот алгоритм сортировки: "+ type + " не может обработать такое" +
            " количество элементов: " + size);
  }
}
