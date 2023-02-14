// Modified by Mark Howell for generics, 3/1/2021

public class GenericTreeNode<E extends Comparable<E>> {
  public GenericTreeNode(E initValue)
    { value = initValue; left = null; right = null; }
    
  public GenericTreeNode(E initValue, 
    GenericTreeNode<E> initLeft, GenericTreeNode<E> initRight) { 
    value = initValue; 
    left = initLeft; 
    right = initRight; 
  }
  
  public E getValue() { return value; }
  public GenericTreeNode<E> getLeft() { return left; }
  public GenericTreeNode<E> getRight() { return right; }
  public void setValue(E theNewValue) { value = theNewValue; }
  public void setLeft(GenericTreeNode<E> theNewLeft) { left = theNewLeft; }
  public void setRight(GenericTreeNode<E> theNewRight) { right = theNewRight;}
  
  public int compareTo (GenericTreeNode<E> other) {
    return value.compareTo(other.getValue());
  }

  private E value;
  private GenericTreeNode<E> left;
  private GenericTreeNode<E> right;
} 