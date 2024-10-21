package org.example;

import java.util.Collections;
import java.util.List;

public class MergeSort implements Action{
  final private int max_size;
  
  public MergeSort(int max_size) {
    this.max_size = max_size;
  }

  public TypeSort type() {
    return TypeSort.Merge;
  }
  public List<Integer> sort(List<Integer> list) throws MaxSizeException {
    if (this.max_size < list.size()) {
      throw new MaxSizeException(list.size(), this.type());
    }

    Collections.sort(list);
    return list;
  }
}
