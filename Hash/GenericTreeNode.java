//modified to add counter -- 2023/03/22
public class GenericTreeNode<E extends Comparable<E>> implements Comparable<GenericTreeNode <E>> {
  private E value;
  private int freq;
  private GenericTreeNode<E> left;
  private GenericTreeNode<E> right;
  
  public GenericTreeNode(E initValue) { 
      value = initValue; 
      left = null; 
      right = null; 
  }
    
  public GenericTreeNode(E initValue, 
    GenericTreeNode<E> initLeft, GenericTreeNode<E> initRight) { 
    value = initValue; 
    left = initLeft; 
    right = initRight; 
  }
  public E getValue() { 
    return value; 
  }
  public GenericTreeNode<E> getLeft() { 
    return left; 
  }
  public GenericTreeNode<E> getRight() { 
    return right; 
  }
  public void setValue(E theNewValue) { 
    value = theNewValue; 
  }
  public void setLeft(GenericTreeNode<E> theNewLeft) { 
    left = theNewLeft; 
  }
  public void setRight(GenericTreeNode<E> theNewRight) { 
    right = theNewRight; 
  }
  public void inc() {
    freq += 1;
  }
  public int getFreq() {
    return freq;
  }

  public int compareTo (GenericTreeNode<E> other) {
    return value.compareTo(other.getValue());
  }
} 