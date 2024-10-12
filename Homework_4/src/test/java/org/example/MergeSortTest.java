package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

  @Test
  public void TestSortedElement() {
    MergeSort mergeSort = new MergeSort(50);
    List<Integer> sorted = mergeSort.sort(Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1));
    assertEquals(sorted, Arrays.asList(1,2,3,4,5,6,7,8,9));
  }

  @Test
  public void TestCheckSize() {
    MergeSort mergeSort = new MergeSort(5);
    assertThrows(IllegalArgumentException.class, () -> mergeSort.sort(Arrays.asList(9,8,7,6,5,4,3,2,1)));
  }
}