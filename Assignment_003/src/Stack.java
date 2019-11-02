public class Stack<T> {
  private LinkedList<T> list;

  public Stack() {
    this.list = new LinkedList<T>();
  }

  public void push(T data) {
    list.prepend(data);
  }

  public T pop() {
    T value = list.getHead();
    list.popHead();
    return value;
  }

  public T peek() {
    return list.getHead();
  }
}