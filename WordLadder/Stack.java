import java.util.ArrayList;

public class Stack<T> {

  private ListNode<T> top;
  
  public Stack() {
    top = null;
  }

  public boolean isEmpty() {
    return top == null;
  }
  
  //add new value to stack
  public void push(T newTop) {
    top = new ListNode<T>(newTop, top);
  }

  //remove and return removed value
  public T pop() {
    //have to do this in a roundabout way because it doesn't like something
    if (!(this.isEmpty())) {
      ListNode<T> a = top;
      T value = (a.getValue());
      top = top.getNext();

      return value;
    } else {
      System.out.println("There is nothing here!");
      return null;
    }
  }

  //fetch top value
  public T fetch() {
    if (!(this.isEmpty())) {
      return top.getValue();
    } else {
      System.out.println("There is nothing here!");
      return null;
    }
  }

  //iterate through stack and return true if value is already inside
  public boolean traverse(String uWord) {
    ArrayList<String> words = new ArrayList<String>();
    words.add((String) this.fetch());
    ListNode temp = new ListNode();
    temp = this.top;
    
    while (temp.getNext() != null) {
      words.add((String) temp.getNext().getValue());
      temp = temp.getNext();
    }

    for (String word: words) {
      if (word.equals(uWord)) {
        return true;
      }
    }
    return false;
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
    Stack<String> s = new Stack<String>();
    s.push("hello");
    s.push("hi");
    s.push("bye");

    System.out.println(s.pop());

    System.out.println(s);
  }
}