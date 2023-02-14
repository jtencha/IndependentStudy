import java.util.Queue;
import java.util.LinkedList;

public class GenericBSTree<E extends Comparable<E>> {
	private GenericTreeNode<E> root;

  	//constructor -- initialize and start tree
	public GenericBSTree() {
		root = null;
	}

  	public GenericTreeNode getRoot() {
		return root;
	}

  	//manual override
  	public void setRoot(GenericTreeNode x) {
    	this.root = x;
  	}

  	//add to tree
	public void add(E toAdd) {
		GenericTreeNode<E> x = new GenericTreeNode(toAdd);
		if (this.root == null) {
			root = x;
		} else {
			addNode(root, x);
    	}
	}
  
	// helper method to add a newNode to the tree -- recursive call
	// Precondition:  pt != null && newNode != null
	private void addNode(GenericTreeNode pt, GenericTreeNode newNode) {
    	//check for null --> space for newNode found
		if (newNode.getValue().compareTo(pt.getValue()) < 0) {
			GenericTreeNode theLeft = pt.getLeft();
      		if (theLeft == null) {
       			pt.setLeft(new GenericTreeNode(newNode.getValue()));
      		} else {
        		addNode(theLeft, newNode);
      		}
      	} else if (newNode.getValue().compareTo(pt.getValue()) > 0) {
			GenericTreeNode theRight = pt.getRight();
      		if (theRight == null) {
        		pt.setRight(new GenericTreeNode(newNode.getValue()));
      		} else {
        		addNode(theRight, newNode);
      		}
		} else { // error - duplicate node -- equals 0
			throw new IllegalArgumentException(newNode.getValue()+"value already in tree");
    	}
	}

  	//returns duplicate seperate object with integrity maintained 
	public GenericBSTree<E> mirrorTree() {
		GenericBSTree<E> x = new GenericBSTree<E>();
		x.root = mirrorTree(root);
		return x;
	}

  	//recursive helper method
	private GenericTreeNode<E> mirrorTree(GenericTreeNode pt) {
		if (pt == null) return pt;
    	else {
      		GenericTreeNode<E> cloned = new GenericTreeNode(pt.getValue());
			 // construct and return the mirrored tree
      		cloned.setLeft(mirrorTree(pt.getLeft()));
      		cloned.setRight(mirrorTree(pt.getRight()));
      
      		return cloned;
		}
	}

	public void remove(Comparable x) {
		root = remove(x, root);
	}
  
	private GenericTreeNode remove(Comparable x, GenericTreeNode pt) {// ***
		if (pt == null) throw new IllegalArgumentException();
		if (x.compareTo(pt.getValue()) < 0) {
			pt.setLeft(remove(x, pt.getLeft()));
		} else if (x.compareTo(pt.getValue()) > 0) {
			pt.setRight(remove(x, pt.getRight()));
    	//} else if (pt.getLeft() == null && pt.getRight() == null) {
      		//pt.setValue(null);
		} else if (pt.getLeft() == null) { // at the node to delete with empty leftsubtree 
			pt = pt.getRight();
		} else if (pt.getRight() == null) { // at the node to delete with empty right subtree
			pt = pt.getLeft();
    	} else { // at the node to delete and it has two children
			pt.setValue(findMin(pt.getRight()).getValue());
			pt.setRight(removeMin(pt.getRight()));
		}
		return pt;
	}

  	//returns smallest value by assuming that the bottom left-most node is smallest,
 	//as established by the add() method
	public Comparable getMin(){
		return findMin(root).getValue();
	}
  
	private GenericTreeNode findMin(GenericTreeNode pt) {
		if (pt == null) return null;
    	else if (pt.getLeft()==null) {
     		return pt;
    	} else {
      		return findMin(pt.getLeft());
    	}
	}


	public void removeMin() {
		root = removeMin(root);
	}
	// Remove the node with the smallest value
	// and return that node.
	// Used as helper for remove
	private GenericTreeNode removeMin(GenericTreeNode pt) {
		if (pt == null) throw new IllegalArgumentException();
    	else if (pt.getLeft() != null) {
			pt.setLeft(removeMin(pt.getLeft()));
			return pt;
		} else {
			return (pt.getRight());
		}
	}

  
	public boolean balanced() {
		return balanced(root);
	}
  
	private boolean balanced(GenericTreeNode pt) {
		if (pt==null) return true;
    	else {
			int l = height(pt.getLeft());
			int r = height(pt.getRight());
			if (Math.abs(l-r) > 1) { 
        		return false;
      		} else { 
        		return (balanced(pt.getLeft()) && balanced(pt.getRight()));
      		}
		}
	}

	public void print() {
		print(root,0);
	}
	
  	public void print(GenericTreeNode pt,int indent) {
		if (pt != null) {
			print(pt.getRight(),indent+5);
			for (int k = 0; k < indent; k++) System.out.print(" ");
			System.out.println(pt.getValue());
			print(pt.getLeft(),indent+5);
		}	
	}

	public int height() {
    	return height(root);
	}
	
	public int height(GenericTreeNode pt) {
    	if (pt == null) return 0;
    	else {
    		int theLeft = height(pt.getLeft()) + 1;
    		int theRight = height(pt.getRight()) + 1;

    		if (theLeft > theRight) {
       			return theLeft;
      		} else { // heighest is greater than or equal to left value
       			return theRight;
      		}	
   		}  
  	}

  	public int numNodes() {
    	return (numNodes(this.root));
  	}

  	private int numNodes(GenericTreeNode y) {
		if (y == null) {
    	//fall out
    	return 0;
    	} else {
    		return (1 + numNodes(y.getLeft()) + numNodes(y.getRight()));
    	}
  	}
	
  	public String toString() {
		return toString(root);
	}
	
  	private String toString(GenericTreeNode pt) {
		String str = "";
		if (pt != null) {
			str += toString(pt.getLeft()) + "\n";
			str += pt.getValue();
			str += "\n"+ toString(pt.getRight());
		}
		return str;
	}

	public String preOrderToString() {
		return preOrderToString(root);
	}

	private String preOrderToString(GenericTreeNode pt) {
		String str = "";
		if (pt != null) {
			str += pt.getValue()+ "\n";
			str += preOrderToString(pt.getLeft()) ;
			
			str +=  preOrderToString(pt.getRight());
		}
		return str;
	}
	
	public void breadthTraversal() {
		Queue<GenericTreeNode> q = new LinkedList();
		q.add(root);
		while (!q.isEmpty()) {
			GenericTreeNode x = q.remove();
			if (x!=null) {
				System.out.print(x.getValue()+"\t");
				q.add(x.getLeft());
				q.add(x.getRight());
			}
		}
		System.out.println();
		
	}

  	private boolean isLeaf(GenericTreeNode pt) {
    	return (pt.getLeft() == null && pt.getRight() == null);
  	}	

  	public void removeLeaves() {
    	if (isLeaf(root)) root = null;
    	else removeLeaves(root);
  	}

  	private void removeLeaves(GenericTreeNode pt) {
		//not needed as of now
  	}
   

	
}