package org.example;

import java.util.ArrayList;
import java.util.List;

public class SortManager {
  private final List<Action> actions;

  public SortManager(List<Action> actions) throws IllegalArgumentException {
    if (actions == null) {
      throw new IllegalArgumentException("null не может быть типом сортировки");
    }
    this.actions = actions;
  }

  public List<Integer> sort(List<Integer> list, TypeSort type) throws NoAlgorithmExeption{
    List<Integer> newList = new ArrayList<>(list);
    for (Action action: actions) {
      if (action.type().equals(type)) {
        try {
          return action.sort(newList);
        } catch (IllegalArgumentException e) {
          System.out.println(e.getMessage() + "Данный алгоритм не может отсортировать данное количество элементов:" + newList.size() +"." +
                  "Перехожу к следующему алгоритму с типом: " + type);
        }
      }
    }
    throw new NoAlgorithmExeption("Не найдено алгоритмов сортировки типа: " + type + ". " +
            "Или они не подходят для сортировки " + newList.size() + " элементов");
  }
}
