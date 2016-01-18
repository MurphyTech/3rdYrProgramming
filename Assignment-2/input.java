//David Murphy
//12493252
//d.murphy53@nuigalway.ie

//IMPORT ITEMS
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class input {

		static FileWriter fw; //declaring class wide FileWriter
		static PrintWriter pw; //declaring class wide PrintWriter
	
	public static void main(String[] args) throws IOException{
		fw = new FileWriter(new File("patientData.txt")); //sets FileWriter to desired file, creates new file.
		pw = new PrintWriter(fw);//sets PrintWriter to write to newly created file
		
		//calling for data entry
		enterData();
		enterData();
		enterData();
		enterData();
		enterData();
		
		
		pw.close();//closing PrintWriter
		fw.close();//closing FileWriter		
	}//END MAIN

	//This method prompts the user to enter patient data and then sends it to written to file 
	private static void enterData() {
		JFrame frame = new JFrame("Input");
		JOptionPane.showMessageDialog(null, "Please enter Patient Data.");
		String FirstName = JOptionPane.showInputDialog(frame, "First Name");
		String Surname  = JOptionPane.showInputDialog(frame, "Surname");
		String PatientID = JOptionPane.showInputDialog(frame, "Patient ID");
		String PhoneNumber = JOptionPane.showInputDialog(frame, "Phone Number");
		
		writeData(FirstName,Surname, PatientID, PhoneNumber );//passes values to wrireData method
	}//END enterData
	
	//This method writes the string values to the file as a single line
	public static void writeData(String First,String Sur, String PatID, String PHnum){
			pw.println(First + ", " + Sur + ", " + PatID + ", " + PHnum);//writes values to one line of the file
	}//END writeData
	
}//END INPUT CLASS
