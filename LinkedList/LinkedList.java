//2022-09-21
//Benjamin C
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
    } else if (((Comparable) a.getValue()).compareTo(x) > 0) {
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
    
  public String toString() {
    String whole = "";
    ListNode a = front;
    while(a != null) {
      whole += a.toString();
      a = a.getNext();
    }
    return whole;
  }

}

