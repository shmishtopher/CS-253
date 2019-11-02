public class Queue<T> {
  private LinkedList<T> list;

  public Queue() {
    this.list = new LinkedList<T>();
  }

  public void enqueue(T data) {
    list.append(data);
  }

  public void enqueue(T ...data) {
    for (T datum : data) {
      list.append(datum);
    }
  }

  public T dequeue() {
    T value = list.getHead();
    list.popHead();
    return value;
  }

  public T peek() {
    return list.getHead();
  }
}