public class Stack {
  
  private ListNode top;
  
  public Stack() {
    top = null;
  }

  public boolean isEmpty() {
    return top == null;
  }
  
  //add new value to stack
  public void push(Comparable newTop) {
    top = new ListNode(newTop, top);
  }

  //remove and return removed value
  public Comparable pop() {
    Comparable value = (Comparable) top.getValue();
    top = top.getNext();

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
    Stack s = new Stack();
    s.push("hello");
    s.push("hi");
    s.push("bye");

    System.out.println(s.pop());

    System.out.println(s);
  }
}