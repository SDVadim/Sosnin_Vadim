package org.example;

public interface Container<T> {
  /**
   * Добавление элемента в массив. При превышении максимальной
   * длины (capacity) происходит увеличение массива
   * @param element – элемент типа <T>
   * @throws NullPointerException – проверка element на null
   */
  void add(T element);

  /**
   * Возвращаем элемент массива с индексом index
   * @param index индекс элемента массива
   * @return возвращает число типа <T> – элемент массива
   */
  T get(int index);

  /**
   * Удаление элемента по ииндексу
   * @param index индекс элемента
   */
  void remove(int index);

}
