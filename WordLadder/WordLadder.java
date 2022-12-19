import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class WordLadder {
  
  public static boolean isOneAway(String word, String test) {
    //sanity check out of the gate
    if(word.length() != test.length()){
        return false;
    }        

    int non = 0;
    if (word.equalsIgnoreCase(test)) {
        return false;
    }

    for (int i = 0; i < word.length(); i++) {
        if (!(word.substring(i, i + 1).equalsIgnoreCase(test.substring(i, i + 1)))) {
            non++;
        }

        if (non > 1) {
          return false;
        }
    }

    return true;

  }
  
  public static void main(String[] args) {
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
    
    EasyReader inFile = new EasyReader("dictionary3.txt");
    if (inFile.bad()) {
     System.err.println("Can't open dictionary3.txt");
     System.exit(1);
    }

    //add to dictionary to ease burden of processing later
    HashMap<Integer, ArrayList<String>> words = new HashMap<Integer, ArrayList<String>>();
    
    ArrayList<String> three = new ArrayList<String>();
    ArrayList<String> four = new ArrayList<String>();
    ArrayList<String> five = new ArrayList<String>();
    ArrayList<String> six = new ArrayList<String>();
    ArrayList<String> seven = new ArrayList<String>();
    ArrayList<String> eight = new ArrayList<String>();
    ArrayList<String> nine = new ArrayList<String>();
    ArrayList<String> other = new ArrayList<String>();
    
    while (!inFile.eof()) {
      String word = inFile.readWord();
      
      //sanity check
      if (word == null) {
        continue;
      } else if (word.length() == 3) {
        three.add(word);
      } else if (word.length() == 4) {
        four.add(word);
      } else if (word.length() == 5) {
        five.add(word);
      } else if (word.length() == 6) {
        six.add(word);
      } else if (word.length() == 7) {
        seven.add(word);
      } else if (word.length() == 8) {
        eight.add(word);
      } else if (word.length() == 9) {
        nine.add(word);
      } else {
        other.add(word);
      }
    }

    words.put(3, three);
    words.put(4, four);
    words.put(5, five);
    words.put(6, six);
    words.put(7, seven);
    words.put(8, eight);
    words.put(9, nine);
    words.put(-1, other);

    int len = first.length();
    if (len > 9 || len < 3) {
      len = -1;
    }

    ArrayList<String> workable = words.get(len);

    Queue<Stack> wordLadders = new Queue<Stack>();

    //this first method works competely -- tested 2022-12-13
    for (String word: workable) {
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
      //for loop -- queue updates as it goes
      
      //for (int i = 0; i < wordLadders.length(); i++) {
        Stack<String> z = wordLadders.dequeue();
        Stack<String> y = z.clone();
        
        for (String word: workable) {
          if (isOneAway(y.fetch(), word)) {
            if (!(y.traverse(word))) {
              Stack<String> x = y.clone();
              y.push(word);
              
              if (word.equals(last)) {
                System.out.println();
                System.out.println("Found Word Ladder!");
                System.out.println(y);
                System.exit(1);
              } else {
                wordLadders.enqueue(y);
                //System.out.println(y);
                //System.out.println();
                y = x;
                //System.out.println(y);
              }
            }
          }
        }
      //}
    }
    
    System.out.println("No word ladders exist between " + first + " and " + last);
  }
}
