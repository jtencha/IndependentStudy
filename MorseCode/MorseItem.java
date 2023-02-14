
public class MorseItem implements Comparable<MorseItem>
{
  private static final String MSTR = "HSVIFUELRAPWJ[BDXNCKYTZGQMO";
  private static int[] morse_order;
  private char ch;
  public MorseItem(char thech) { 
    int loc;
    morse_order = new int[27];
    for (int k=0; k<27; k++) {
      loc = (int)(MSTR.charAt(k))-(int)('A');
      morse_order[loc] = k;  // loc is postion in the alphabet of each letter
                  // k is the morse order from mstr
    }
    ch=thech;
		
	}
	public char getChar() {
		return ch;
	}

  public String getString() {
    return String.valueOf(ch);
  }
  
	public void setChar(char theNewChar) {
		ch = theNewChar;
	}

  public int compareLetter(String other) {
    char otherch = other.charAt(0);

    if (this.ch == otherch) {
      return 0;
    } else {
      return -1;
    }
  }
  
  public int compareTo(MorseItem other) {
    char otherch = other.getChar();
    int otherchloc = otherch-'A';
    int chloc =ch-'A';
    //System.out.println("chloc = "+ chloc);
    //System.out.println("otherchloc = "+ otherchloc);
    
    return morse_order[chloc]-morse_order[otherchloc];
	} 
  
	public String toString() {
		return ""+ ch;
	}
  
} 