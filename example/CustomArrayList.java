package example;

public class CustomArrayList<T> implements Container<T>{
  protected T[] arr;
  protected int capacity;
  protected int len;

  public CustomArrayList (int capacity) throws RuntimeException {
    if (capacity <= 0) {
      throw new RuntimeException("length must be positive");
    }

    this.arr = (T[]) new Object[capacity];
    this.capacity = capacity;
    this.len = 0;
  }

  public void add(T element) {
    if (element == null) {
      throw new IllegalArgumentException("Element can not be null");
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

  private void CheckIndex (int index){
    if (index < 0 || index >= this.len) {
      throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + this.len);
    }
  }

  public T get(int index) {
    CheckIndex(index);
    return arr[index];

  }

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
