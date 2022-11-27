public class Queue<T> {
  private ListNode<T> top;
  private ListNode<T> back;

  public Queue () {
    top = null;
  }

  public boolean isEmpty() {
    return top == null && back == null;
  }

  public int length() {
    int count = 1;
    if (this.isEmpty()) {
      return 0;
    } else if (top.getNext() == null && back.getNext() == null) {
      return 1;
    } else {
      ListNode<T> temp = top;
      while (temp.getNext() != null) {
        count++;
        temp = temp.getNext();
      }
      return count;
    }
  }

  public void enqueue(T toAdd) {
    if (this.isEmpty()) {
      top = new ListNode<T>(toAdd,top);
      back = top;
    } else {
      top = new ListNode<T>(toAdd, top);
    }
  }

  public T dequeue() {
    back = top;

    if (this.length() == 0) {
      System.out.println("This queue is empty!");
      return null;
    } else if (this.length() == 1) {
      T value = back.getValue();
      top = null;
      back = null;
      return value;
    } else {
      while (back.getNext().getNext() != null) {
        back = back.getNext();
      }
  
      T value = (back.getNext().getValue());
      //pop out
      back.setNext(null);
      
      return value;
    }
  }

  public T fetch() {
    if (!(this.isEmpty())) {
      return top.getValue();
    } else {
      System.out.println("There is nothing here!");
      return null;
    }
  }

  public String toString() {
    String whole = "";
    ListNode<T> a = top;
    while(a != null) {
      whole += a.toString();
      a = a.getNext();
    }
    return whole;
  }

  public static void main(String[] args) {
    Queue<String> q = new Queue<String>();
    q.enqueue("hello");
    q.enqueue("hi");
    //q.enqueue("bye");

    System.out.println(q);
    System.out.println(q.dequeue());
    System.out.println(q);
    System.out.println(q.dequeue());
    System.out.println(q);
  }
}