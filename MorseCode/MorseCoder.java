// Class used to represent morse code, and encode and decode 2/9/2023
import java.util.Scanner;
import java.util.ArrayList;

public class MorseCoder {
	// mtree represents morse code
	private GenericBSTree<MorseItem> mtree;
  private static String currentLetter = "";
	private static final String MAKESTR = "[ETIANMSURWDKGOHVFLPJBXCYZQ";
	public MorseCoder() {
		mtree = new GenericBSTree<MorseItem>();
		MorseItem x;
		for (int k = 0; k < 27; k++) {	
			x = new MorseItem(MAKESTR.charAt(k));
			mtree.add(x);
		}
	}
	public GenericBSTree<MorseItem> getTree() {
		return mtree;
	}
	// write encode and decode, 
	public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Sentence to encode: ");
    String original = input.nextLine();

    input.close();
		MorseCoder m = new MorseCoder();

    String coded = m.encode(original.toUpperCase());
    System.out.println("Written in Morse Code: " + coded);
    System.out.println("Decoded: " + m.decode(coded));
		//m.getTree().print();
	}

  public String encode(String theWord) {
    String toReturn = "";
    for (int i = 0; i < theWord.length(); i++) {
      String letter = theWord.substring(i, i+1);
      if (letter.equals(" ")) {
        toReturn += "/";
      } else if (!(letter.equals("."))) {
        encode(this.mtree.getRoot(), letter);
        toReturn += currentLetter;
      }
      
      toReturn += (" ");
      currentLetter = "";
    }
    
    return toReturn;
  }

  
  private boolean encode(GenericTreeNode pt, String letter) {
    if (pt == null) {
      return false;
    } else if (((MorseItem) pt.getValue()).compareLetter(letter) == 0) {
    //} else if (((String) pt.getValue()).compareTo(letter) == 0) {
      return true;
    } else {
      if (encode(pt.getLeft(), letter) == true) {
        currentLetter = "." + currentLetter;
        return true;
      } else if (encode(pt.getRight(), letter) == true) {
        currentLetter = "-" + currentLetter;
        return true;
      } else {
        //System.out.println("failed");
        return false;
      }
    }
  }

  public String decode(String code) {
    String decoded = "";
    String[] theWords = code.split("/");
    for (String word: theWords) {
      String[] theLetters = word.split(" ");
      for (String letter: theLetters) {
        GenericTreeNode<MorseItem> position = mtree.getRoot();
        for (int i = 0; i < letter.length(); i++) {
          String active = letter.substring(i, i+1);
          if (active.equals(".")) {
            position = position.getLeft();
          } else if (active.equals("-")) {
            position = position.getRight();
          }
        }

        // [ is how spaces are kept track of
        if (position.getValue().getChar() == '[') {
          decoded += " ";
        } else {
          decoded += position.getValue();
        }
      }
    }
    return decoded;
  }
}