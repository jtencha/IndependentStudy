public class Queue {
  private ListNode top;
  private ListNode back;

  public Queue () {
    top = null;
  }

  public boolean isEmpty() {
    return top == null && back == null;
  }

  public void add(Comparable toAdd) {
    top = new ListNode(toAdd, top);
  }

  public Comparable remove() {
    ListNode back = top;

    //need to access both of these for this to work correctly 
    while (back.getNext().getNext() != null) {
      back = back.getNext();
    }

    Comparable value = (Comparable) back.getNext().getValue();
    //pop out
    back.setNext(null);
    
    return value;
  }

  public String toString() {
    String whole = "";
    ListNode a = top;
    while(a != null) {
      whole += a.toString();
      a = a.getNext();
    }
    return whole;
  }

  public static void main(String[] args) {
    Queue q = new Queue();
    q.add("hello");
    q.add("hi");
    q.add("bye");

    System.out.println(q.remove());
    System.out.println(q);
  }
}