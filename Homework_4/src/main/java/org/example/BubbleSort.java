package org.example;

import java.util.List;

public class BubbleSort {
  private int max_size;

  public BubbleSort(int max_size) {
    this.max_size = max_size;
  }

  public List<Integer> sort(List<Integer> list ) throws IllegalArgumentException{
    if (this.max_size < list.size()) {
      throw new IllegalArgumentException("Этот алгоритм сортировки не может обработать такое" +
              " количество элементов");
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
