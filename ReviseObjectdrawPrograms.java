import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
//import EasyClasses.*;
public class ReviseObjectdrawPrograms  {
	
	private String file;
  private String current_file;
	public ReviseObjectdrawPrograms(String file, int count) {
      this.file = file;    
      this.current_file = "fileList.txt";
      if (file.substring(file.length() - 5).equals(".java")) {
          processFile(file, count);
      } else {
          System.out.println(file + " is not a java file.");
      }
          
	}
	public void processFile(String nm, int count){

		WordStreamIterator ws = new WordStreamIterator(nm);
		String word;
	
		while (ws.hasNext())
	   		{
	   			word = (String) (ws.next());
	   			
	   			if (word != null)
	   			{
            EasyReader toRead = new EasyReader(file);
            EasyWriter outputFile = new EasyWriter("revised" + count + ".java");
            current_file = "revised" + count + ".java";
        
            if (toRead.bad()) {
                System.err.println("Can't open " + toRead);    
                System.exit(1);
            } else if (outputFile.bad()) {
                System.err.println("Can't open " + outputFile);
                System.exit(1);
            }

            ArrayList<String> lines_of_file = new ArrayList<String>();
            while (!toRead.eof()){
                lines_of_file.add(toRead.readLine());
            }

            int i = 0;  
            for (String l: lines_of_file) {
              if (l == null) {
                  continue;
                }
                else if (l.length() > 14 && l.substring(0, 12).equals("public class")) {
                    if (l.substring(13).equals(file.substring(0, file.length() - 5))) {
                        outputFile.println(l);
                        i++;

                        if (i == lines_of_file.size()) {
                            outputFile.println("public static void main(String[] args) {");
                            outputFile.println("new " + current_file.substring(0, current_file.length() - 5) + "().startController(400,400);");
                            outputFile.println("}");
                            outputFile.println("}");
                            return;
                        }
                    } else {
                        System.out.println("Incorrect File: " + l.substring(13) + " is not equal to " + file.substring(0, file.length() - 5));
                    }
                  }
                  outputFile.println(l);
            }
	   				// copy everything into new file using outputfile.print(word)
	   				// determine if we are at class heading, if so, get class name (or get this from file name)
	   				// also determine is we are at the end (or right before the last "}")
	   				// is so, insert the four lines from the main method
	   			}
	 		}
	 		
	 	//outputFile.close(); 
    
	 }
	
	 public static void main(String[] args)
	 {

     String[] names = {"one.java", "junk.txt", "two.java"};
    int count = 0;
	 	for (String nm:names)
		{
      count++;
      ReviseObjectdrawPrograms revOD = new ReviseObjectdrawPrograms(nm, count);
			//revOD.processFile(nm);
	 		
	 	}	
	 	
	 }
	 
}
