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


}
