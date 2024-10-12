package org.example;


/**
 * CustomArrayList – собственный ArrayList с ограниченным интерфейсом.
 * Создание динамического массива.
 *
 * @param <T>
 */
public class CustomArrayList<T> implements Container<T>{
  protected T[] arr;
  protected int capacity;
  protected int len;

  /**
   * Метод создающий динамический массив.
   * @param capacity – максимальный размер массива (не равен длине)
   * @throws RuntimeException – исключение, выбрасываемое при превышении capacity
   */
  public CustomArrayList (int capacity) throws RuntimeException{
    if (capacity <= 0) {
      throw new RuntimeException("length must be positive");
    }
    this.arr = (T[]) new Object[capacity];
    this.capacity = capacity;
    this.len = 0;
  }

  /**
   * Добавление элемента в массив. При превышении максимальной
   * длины (capacity) происходит увеличение массива
   * @param element – элемент типа <T>
   * @throws NullPointerException – проверка element на null
   */
  @Override
  public void add(T element) {
    if (element == null) {
      throw new NullPointerException("Element can not be null");
    }
    if (this.len == this.capacity - 1) {
      this.capacity += 1;
      T[] new_arr = (T[]) new Object[this.capacity];
      System.arraycopy(this.arr, 0, new_arr, 0, this.len);
      this.arr = new_arr;
    }
    this.arr[len] = element;
    this.len++;
  }

  /**
   * Проверка вводимого индекса.
   * @param index индекс элемента
   */
  private void CheckIndex (int index){
    if (index < 0 || index >= this.len) {
      throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + this.len);
    }
  }

  /**
   * Возвращаем элемент массива с индексом index
   * @param index индекс элемента массива
   * @return возвращает число типа <T> – элемент массива
   */
  @Override
  public T get(int index) {
    CheckIndex(index);
    return arr[index];

  }

  /**
   * Удаление элемента по ииндексу
   * @param index индекс элемента
   */
  @Override
  public void remove(int index) {
    System.out.println("element " + this.get(index) + " deleted");
    CheckIndex(index);
    T[] new_arr = (T[]) new Object[this.capacity];
    System.arraycopy(this.arr, 0, new_arr, 0, index);
    System.arraycopy(this.arr, index+1, new_arr, index, this.len - index);
    this.arr = new_arr;
    this.len--;
  }
}
