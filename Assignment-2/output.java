//David Murphy
//12493252
//d.murphy53@nuigalway.ie

//IMPORT ITEMS
import java.io.LineNumberReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;

public class output {
	public static void main(String[] args) throws IOException {
		BufferedReader br = null; //create method wide BufferedReader
		LineNumberReader lnr = null;//create method wide LineNumberReader
		String string; //create method wide string variable
		int i; //create method wide int variable
	
		try {
			br = new BufferedReader(new FileReader("patientData.txt"));//set BufferedReader equal to "patientData.txt"
			lnr = new LineNumberReader(br); //set LineNumberReader equal to lines in BufferedReader br
		
			//reading lines
			while((string=lnr.readLine()) !=null)//do this as long as there is a next line
			{
				i=lnr.getLineNumber(); //sets value of i equal to current line number
				JOptionPane.showMessageDialog(null, "Line " + i + ": " +string);//prints line # and the corresponding string
				
			}
		}
	
		catch(IOException ex){
			//Do something if error
			JOptionPane.showMessageDialog(null, "Error Message: Output");
		}
	
		finally{
				//closes the streams and releases sys resources
				if (br!=null)
					br.close();
					//USED FOR DEBUGGING
					//JOptionPane.showMessageDialog(null, "br is closed");
				if (lnr!=null)
					lnr.close();
					//USED FOR DEBUGGING
					//JOptionPane.showMessageDialog(null, "lnr is closed");
		}
	}//END MAIN
}//EN DOUTPUT CLASS
