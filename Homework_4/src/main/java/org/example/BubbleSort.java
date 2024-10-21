package org.example;

import java.util.List;

public class BubbleSort implements Action{
  private final int max_size;

  public BubbleSort(int max_size) {
    this.max_size = max_size;
  }

  public TypeSort type() {
    return TypeSort.Bubble;
  }

  public List<Integer> sort(List<Integer> list) throws MaxSizeException {
    if (this.max_size < list.size()) {
      throw new MaxSizeException(list.size(), this.type());
    }
    for (int i = 0; i < list.size(); i++) {
      for (int j = i + 1; j < list.size(); j++) {
        if (list.get(i) > list.get(j)) {
          int temp = list.get(i);
          list.set(i, list.get(j));
          list.set(j, temp);
        }
      }
    }
    return list;
  }
}
