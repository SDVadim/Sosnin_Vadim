package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.println("Введите количество элементов");
    int count = input.nextInt();
    List<Integer> array = new ArrayList<>();
    System.out.print("Вводите элементы через пробел: ");
    for (int i = 0; i < count; i++) {
      array.add(input.nextInt());
    }

    System.out.println("Доступны следующие сортировки:");
    for (TypeSort typeSort: TypeSort.values()) {
      System.out.print(typeSort + " ");
    }

    System.out.println("\nВведите тип сортировки:");
    TypeSort typeSort = TypeSort.valueOf(input.next());

    SortManager sortManager = new SortManager(Arrays.asList(new BubbleSort(10), new MergeSort(50)));
    List<Integer> sorted = sortManager.sort(array, typeSort);

    for (int i: sorted) {
      System.out.print(i + " ");
    }
  }
}