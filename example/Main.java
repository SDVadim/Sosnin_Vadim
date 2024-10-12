package org.example;

public class Main{
  public static void main(String[] args) {
    CustomArrayList<Integer> arr = new CustomArrayList<>(2);

    for (int i = 0; i<32; i++) {
      arr.add(i);
    }
    for (int i = 0; i < 32; i++) {
      System.out.print(arr.get(i) + " ");
    }
    System.out.println();
    arr.remove(1);
    arr.remove(5);

    for (int i = 0; i < 10; i++) {
      System.out.print(arr.get(i) + " ");
    }
  }
}