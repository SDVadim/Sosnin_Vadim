package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class BubbleSortTest {
  @Test
  public void TestSortedElement() {
    BubbleSort bubleSort = new BubbleSort(10);
    List<Integer> sorted = bubleSort.sort(Arrays.asList(8, 9, 4, 3, 2, 1, 6, 7, 5));
    List<Integer> answer = Arrays.asList(1,2,3,4,5,6,7,8,9);
    assertEquals(sorted, answer);
  }
  @Test
  public void TestCheckSize() {
    BubbleSort bubleSort = new BubbleSort(5);
    List<Integer> list = Arrays.asList(10,0 , 1, 2, 3, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
    assertThrows(IllegalArgumentException.class, () -> bubleSort.sort(list));
  }
}