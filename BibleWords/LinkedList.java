//2022-09-21
//Benjamin C
//LinkedList Project 2
import java.util.ArrayList;

public class LinkedList
{
  private ListNode front;

  public LinkedList() {
    front = null;
  }
  public ListNode getFront() {
    return front;
  }
  public void setFront( ListNode newFront) {
    front = newFront;
  }

  public boolean isEmpty() {
    return front == null;
  }

  public void addFirst(Comparable x ) {
    //assign pointer to previous front
    front = new ListNode(x, front);
  }

  public void addLast(Comparable x) {
    //no list, point to null
    if (front == null) {
      front = new ListNode (x, null);
    } else {
      ListNode y = front;
      //still items to go
      while (y.getNext() != null) {
          //fetch next
          y = y.getNext();
      }
      ListNode n = new ListNode(x, null);
      y.setNext(n);
    }
    
  }

  //I changed this to return as a ListNode, since the starter code said to return the
  //value that was removed
  public ListNode remove(Comparable x) {
    //front of list is equal, so previously second becomes first
    if (front.getValue().equals(x)) {
      front = front.getNext();
    } else {
      ListNode y = front;
      while (y.getNext() != null) {
        if (y.getNext().getValue().equals(x)) {
          //get one after to take out of linkedList
          y.setNext(y.getNext().getNext());
          return y;
        }
        y = y.getNext();
      }
    }
 		return null;  // return null if x not found, otherwise return x
  }
  
  // here are the recursive methods:
  
  public void insert(Comparable x) {
  	front = insertHelper(x, front);
  }
  private ListNode insertHelper(Comparable x, ListNode a) {
    //front of list
    if (a ==null) {
      return new ListNode(x, a);
    } else if (((String) a.getValue()).compareToIgnoreCase((String) x) > 0) {
      return new ListNode(x, a);
    } else {
      a.setNext(insertHelper(x, a.getNext()));
      return a;
    }
  }
  public void delete(Comparable x) {
  	front = deleteHelper(x, front);
  }
  private ListNode deleteHelper(Comparable x, ListNode a) {
    //this one is different from the remove method above- remove() deletes and returns
    //delete() just deletes it without returning the value
  	if (a == null) {
      return a;
    } else if (((Comparable) a.getValue()).compareTo(x) < 0 ) {
      a.setNext(deleteHelper(x,a.getNext()));
      return a;
    } else if (((Comparable) a.getValue()).compareTo(x) >= 0 ) { 
      return a.getNext();
    } else {
      //this line should never run
      return a;
    }
  }

  //project 2:
  public Object printInReverseOrder() {
    ListNode a = this.front;
    String in_reverse = reverseRecursion(a) + "-//";
    return in_reverse;
  }

  private String reverseRecursion(ListNode a) {
    //check for next one so that NullPointerException does not get
    //thrown when at the end case
    while (a.getNext() != null) {
      return reverseRecursion(a.getNext()) + "->" + a.getValue();
    }
    
    //end case
    return String.valueOf(a.getValue());
  }

  public LinkedList reverseList() {
    LinkedList x = new LinkedList();

    ListNode a = this.front;
    
    while (a != null) {
      x.setFront(new ListNode((Comparable) a.getValue(), x.getFront()));

      a = a.getNext();
    }

    return x;
  }

  public Object middleNode() {
    int length = this.getLength();
    ListNode a = front;
    int middle = 0;
    int i = 0;

    if (length % 2 == 0) {
      //if imbalanced, take the one before
      middle = length / 2;
    } else {
      middle = (int) ((length / 2) - 0.5);
    }

    while (i != middle) {
      a = a.getNext();
      i++;
    }

    return a.getValue();
    
  }

  //my extra methods:
  public int getLength() {
    ListNode a = this.front;
    int count = 0;

    if (a == null) {
      return 0;
    } else {
      while (a != null) {
        a = a.getNext();
        count++;
      }
    }

    return count;
  }

    
  public String toString() {
    String whole = "";
    ListNode a = front;
    while(a != null) {
      whole += a.toString();
      a = a.getNext();
    }
    return whole;
  }

  public void getMostFrequent() {
    int highest = 0;
    int count = 1;
    String the_highest = "";
    String current_word = "";

    ListNode a = this.getFront();
    while (a.getNext() != null) {
      if (((String) a.getValue()).compareToIgnoreCase((String) a.getNext().getValue()) == 0) {
        count++;
        current_word = (String) a.getValue();
      } else {
        if (count >= highest) {
          highest = count;
          the_highest = current_word;
        }
  
        count = 1;
      }
      
      a = a.getNext();
    }
    
    System.out.println("Most frequent word: " + the_highest);
    System.out.println("Frequency: " + highest);
  }
}

