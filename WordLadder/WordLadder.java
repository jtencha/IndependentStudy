import java.util.ArrayList;
import java.util.Scanner;

public class WordLadder {
  
  public static boolean isOneAway(String word, String test) {
    //sanity check out of the gate
    if(word.length() != test.length()){
        return false;
    }        

    int matches = 0;
    if (word.equalsIgnoreCase(test)) {
        return false;
    }

    for (int i = 0; i < word.length(); i++) {
        if (word.substring(i, i + 1).equals(test.substring(i, i + 1))) {
            matches++;
        }
    }
    if (matches == (word.length() - 1)) {
        return true;
    }
    
    return false;
  }
  
  public static void main(String[] args) {
    EasyReader inFile = new EasyReader("dictionary3.txt");
    if (inFile.bad()) {
     System.err.println("Can't open dictionary3.txt");
     System.exit(1);
    }

    ArrayList<String> words = new ArrayList<String>();
    
    while (!inFile.eof()) {
      words.add(inFile.readWord());
    }

    Scanner in = new Scanner(System.in);
    
    System.out.println("Enter a word: ");
    String first = in.nextLine();
    System.out.println("Enter a word to path to: ");
    String last = in.nextLine();

    in.close();

    if (first.length() != last.length()) {
      System.out.println("Invalid input -- the two words need to be an equal length.");
      System.exit(1);
    }

    Queue<Stack> wordLadders = new Queue<Stack>();

    //first go-around
    for (String word: words) {
      if (word == null) {
        continue;
      } else {
        if (isOneAway(first, word)) {
          Stack<String> x = new Stack<String>();
          x.push(first);
          x.push(word);
          wordLadders.enqueue(x);
        }
      }
    }

    while (!(wordLadders.isEmpty())) {
      Stack y = wordLadders.dequeue();
      //System.out.println(y);
      if (((String) y.fetch()).equals(last)) {
          System.out.println("Found word ladder from " + first + " to " + last);
          System.out.println(y);
          System.exit(1);
      } else {
        for (String word: words) {
          //check for craziness
          if (word != null) {
            if (isOneAway((String) y.fetch(), word)) {
              if (y.traverse(word) == false) { 
                y.push(word);
                wordLadders.enqueue(y);
              }
            } 
          }
        }
      }
    }
    System.out.println("No word ladders exist between " + first + " and " + last);
  }
}