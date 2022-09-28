//2022-09-21
//Benjamin C
//LinkList Project 2

public class ListNode
{
  private Comparable value;
  private ListNode next;

  public ListNode()
  {
    value = null;
    next = null;
  }
  public ListNode(Comparable x, ListNode pt)
  {
  	value = x;
  	next = pt;
  }
	
  public Object getValue()
  {
    return value;
  }

  public ListNode getNext()
  {
    return next;
  }

  public void setValue(Comparable theNewValue)
  {
    value = theNewValue;
  }

  public void setNext(ListNode theNewNext)
  {
    next = theNewNext;
  }
  public String toString()
  {
  	String result = "";
  	result += value.toString();
  	if (next==null) result += "-//";
  	else result += "->";
  	return result;
  }
  // test it here
  public static void main(String[] args)
  {
  	/*ListNode head = new ListNode(new Integer(17),null);
  	head = new ListNode(new Integer(37),head);
  	ListNode pt = head;
  	while (pt!=null)
  	{
  		System.out.print(pt+"  ");
  		pt = pt.getNext();
  	}
  	System.out.println();*/

    LinkedList list = new LinkedList();
    list.insert("test");
    list.insert("cat");
    list.insert("bus");
    list.insert("table");
    //list.insert("hi");
    System.out.println(list);
    System.out.println("Reverse Order: ");
    System.out.println(list.printInReverseOrder());
    System.out.println("Reversed List and Ordinary List: ");
    System.out.println(list.reverseList() + " & " + list);
    //System.out.println("Middle ListNode: " + list.middleNode());

    
  }
}
