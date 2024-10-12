package org.example;

import java.util.Collections;
import java.util.List;

public class MergeSort {
  final private int max_size;
  public MergeSort(int max_size) {
    this.max_size = max_size;
  }

  public List<Integer> sort(List<Integer> list) throws IllegalArgumentException{
    if (this.max_size < list.size()) {
      throw new IllegalArgumentException("Этот лгоритм мортировки не может обработать " +
              "такое количество элементов");
    }

    Collections.sort(list);
    return list;
  }
}
