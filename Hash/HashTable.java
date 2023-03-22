//Benjamin C -- 2023/03/22
import java.lang.Math;

public class HashTable <E extends Comparable<E>> {
    private GenericBSTree<E>[] table;
    private int size;
    
    public HashTable(int size) {
        table = new GenericBSTree[size];
        this.size = size;
        for (int i = 0; i < size; i++) {
            table[i] = new GenericBSTree();
        }
    }

    //Sorting
    private int hashFunction(String theItem) {
        int toReturn = 0;
        for (int i = 0; i < theItem.length(); i++) {
            toReturn = 3 * toReturn + theItem.charAt(i);
        }

        toReturn = toReturn % size;

        //sanity checks -- out of bounds of the table
        if (toReturn >= size) {
            toReturn = size - 1;
        } else if (toReturn < 0) {
            toReturn = 0;
        }

        return toReturn;
    }

    //Add value to hashtable
    public void add(E toAdd) {
        int i =  hashFunction((String) toAdd);
        table[i].add(toAdd);  
    }

    //Prints out values of HashTable accompanied by index
    public void print() {
        for (int i = 0; i < table.length; i++) {
            System.out.print(i + ": " + table[i]);
        }
    }

    //Return raw counts of nodes at each hash
    public void census() {
        for (int i = 0; i < size; i++) {
            System.out.print(i + ": " + table[i].numNodes() + " ");
        }
    }


    
    public static void main(String[] args) {
        //HashTable<String> x = new HashTable(100);

        String fileName = "HashTest.txt";    
        EasyReader inFile = new EasyReader(fileName);
        if (inFile.bad()) {
            System.err.println("Can't open " + fileName);    
            System.exit(1);
        }

        HashTable<String> theTable = new HashTable(500);
        String word;
        while ((word = inFile.readWord()) != null) {
            int theIndex = theTable.hashFunction(word);
            theTable.add(word);
        } 

        //theTable.print();
        theTable.census();
    }
}