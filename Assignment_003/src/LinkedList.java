public class LinkedList<T> {
  private Node<T> head;
  private Node<T> last;

  
  public LinkedList() {
    this.head = null;
    this.last = null;
  }


  public T getHead() {
    return head == null ? null : head.data;
  }


  public T getLast() {
    return last == null ? null : last.data;
  }


  public void append(T data) {
    Node<T> node = new Node<T>();
    node.data = data;
    node.next = null;
    node.prev = last;

    if (head == null && last == null) {
      head = node;
      last = node;
    }
    else {
      last.next = node;
      last = node;
    }
  }


  public void prepend(T data) {
    Node<T> node = new Node<T>();
    node.data = data;
    node.next = head;
    node.prev = null;

    if (head == null && last == null) {
      head = node;
      last = node;
    }
    else {
      head.prev = node;
      head = node;
    }
  }


  public void popHead() {
    if (head == last) {
      head = null;
      last = null;
    }
    else {
      head = head.next;
    }
  }


  public void popLast() {
    if (head == last) {
      head = null;
      last = null;
    }
    else {
      last = last.prev;
    }
  }


  private class Node<T> {
    public T data;
    public Node<T> next;
    public Node<T> prev;
  }
}