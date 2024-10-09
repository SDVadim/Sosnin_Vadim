package example;

public interface Container<T> {
  public void add(T element);

  public T get(int index);

  public void remove(int index);

}
