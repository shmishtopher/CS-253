package debruijn;


public interface Splittable<T> {
  public T getPrefix();
  public T getSuffix();
  public int size();
}