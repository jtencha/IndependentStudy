import java.util.ArrayList;

public class ProcessBibleWords {
	public ArrayList<String> wordList;
	
	// create wordList and fill it with all the words
	public void loadWords()
	{
    
	  wordList = new ArrayList<String>();
		// use EasyReader to load all the words into wordList
    ///*
    String fileName = "BibleKingJames.txt";    
    EasyReader inFile = new EasyReader(fileName);
    if (inFile.bad()) {
      System.err.println("Can't open " + fileName);    
      System.exit(1);
    }

    String word;
    while ((word = inFile.readWord()) != null) {
      wordList.add(word);
    } 	//*/
    
 /*
    //testing values
    wordList.add("car");
    wordList.add("apple");
    wordList.add("car");
    wordList.add("dog");
    wordList.add("banana");
    wordList.add("frog");
    wordList.add("1.1");
    wordList.add("Boat");
    */

	}


  
	public void processByArray() {
		String[] wordArray = new String[wordList.size()];

    int i; 
    int current;
    String value;

    for (i = 1; i < wordArray.length; i++) {
       value = wordList.get(i);
       current = i - 1;

      if (wordArray[current] == null) {
        //first case
        wordArray[current] = wordList.get(current);
        i = 0;
      } else {
        //sanity check for the first two, then check if value is smaller
        while (current >= 0 && wordArray[current] != null && wordArray[current].compareToIgnoreCase(value) >= 0) {
        wordArray[current + 1] = wordArray[current];
        current--;
        }
     }

    //bounce out from loop and insert into array
    wordArray[current + 1] = value;
      
    }

    //HIGHEST VALUE- no method because the other one is made for LinkedList

    int highest = 0;
    int count = 1;
    String the_highest = "";
    String current_word = "";

    for (int j = 0; j < wordArray.length - 1; j++) {
      if (wordArray[j].compareToIgnoreCase(wordArray[j + 1]) == 0) {
         count++;
        current_word = wordArray[j];
      } else {
        if (count >= highest) {
          highest = count;
          the_highest = current_word;
        }
  
        count = 1;
      }
    }
    
    System.out.println("Most frequent word: " + the_highest);
    System.out.println("Frequency: " + highest);
  }

	public void processByLLRecursive()
	{
		LinkedList ll = new LinkedList();
    ll.setFront(new ListNode(wordList.get(0), null));

    for (int i = 1; i < wordList.size(); i++) {
      ll.insert(wordList.get(i));
      //System.out.println(ll);
    }

    ll.getMostFrequent();
    
	}
	public void processByLLIterative()
	{
		LinkedList ll = new LinkedList();
		// traverse wordList and place each word into ll iteratively
    for (int i = 0; i < wordList.size(); i++) {
      ListNode a = ll.getFront();
      if (a == null) {
        ll.setFront(new ListNode(wordList.get(i), null));
      } else {
        for (int j = 0; j <= i; j++) {
          if (a == null) {
            //at the end of the list
            a = new ListNode(wordList.get(i), null);
          } else if (((String) a.getValue()).compareToIgnoreCase(wordList.get(i)) > 0 && j == 0) {
            //rare case in where word in question is bigger than everything else
            ll.setFront(new ListNode(wordList.get(i), ll.getFront()));
            break;
          } else {
            while (a.getNext() != null && ((String) a.getNext().getValue()).compareToIgnoreCase(wordList.get(i)) <= 0) {
              a = a.getNext();
            } 

            a.setNext(new ListNode(wordList.get(i), a.getNext()));
            break;
          } 
        }   
      }
      //System.out.println(ll);
    }
    
    ll.getMostFrequent();

  }
  
  
	public static void main(String[] args) {
		
		ProcessBibleWords pbw = new ProcessBibleWords();	
    Timer clock = new Timer();
		pbw.loadWords();
		System.out.println("Elapsed time (seconds): " + clock.elapsedSeconds());
    clock.reset();
		// uncomment one of the following before executing
		pbw.processByArray();
		//pbw.processByLLRecursive();
		//pbw.processByLLIterative();
		// output the time
    System.out.println("Elapsed time: " + clock.elapsedSeconds());
		
	}

}
