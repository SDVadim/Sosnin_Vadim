package org.example;

import java.util.List;

public interface Action {
  TypeSort type();
  List<Integer> sort(List<Integer> list) throws IllegalArgumentException;
}
