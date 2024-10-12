package org.example;

import java.util.ArrayList;
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

    System.out.println("Введите тип сортиировки (1 - сортировка " +
            "пузырьком; 2 - сортировка слиянием): ");
    boolean flag;
    do {
      int type_sort = input.nextInt();
      flag = false;
      if (type_sort == 1) {
        BubbleSort bubbleSort = new BubbleSort(10);
        List<Integer> sorted_list = bubbleSort.sort(array);
        System.out.println(sorted_list);
      } else if (type_sort == 2) {
        MergeSort mergeSort = new MergeSort(50);
        List<Integer> sorted_list = mergeSort.sort(array);
        System.out.println(sorted_list);

      } else {
        System.out.println("Неверно введено значение");
        flag = true;
      }
    } while (flag);
  }
}