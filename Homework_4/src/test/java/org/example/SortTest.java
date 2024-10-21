package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SortTest {

  @Test
  public void TestNullTypeException() throws IllegalArgumentException{
    IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
            ()-> new SortManager(null));
    assertEquals("null не может быть типом сортировки", illegalArgumentException.getMessage());
  }

  @Test
  public void TestNoBubbleSortedType() {
    SortManager sortManager = new SortManager(List.of(new MergeSort(5)));
    NoAlgorithmExeption noBubbleAlgorithmExeption = assertThrows(NoAlgorithmExeption.class, ()-> sortManager.sort(Arrays.asList(1, 2 , 3, 4), TypeSort.Bubble));
    assertEquals("Не найдено алгоритмов сортировки типа: Bubble. Или они не подходят для сортировки 4 элементов", noBubbleAlgorithmExeption.getMessage());
  }

  @Test
  public void TestNoMergeSortedType() {
    SortManager sortManager = new SortManager(List.of(new BubbleSort(2)));
    NoAlgorithmExeption noMergeAlgorithmExeption = assertThrows(NoAlgorithmExeption.class, ()-> sortManager.sort(Arrays.asList(1, 2 , 3, 4, 5, 6), TypeSort.Merge));
    assertEquals("Не найдено алгоритмов сортировки типа: Merge. Или они не подходят для сортировки 6 элементов", noMergeAlgorithmExeption.getMessage());
  }
}
