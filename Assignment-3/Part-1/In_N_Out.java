/*
 * David Murphy
 * 12493252
 * d.murphy53@nuigalway.ie
 */
package assign3_part1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@SuppressWarnings("serial")
public class In_N_Out implements Serializable{
	
	public static void main(String args[]) throws IOException, ClassNotFoundException{
		System.out.println("Part 1");
		//Create Arrays
		StudentData[] Array_out = new StudentData[10];
		StudentData[] Array_in = new StudentData[10];
		//Writing Generic DataSet to Array_out
		for (int i=0; i<=Array_out.length-1; i++){
			Array_out[i] = new StudentData("John Smith", "ECE", i);
		}
		
		try{
			//Create Appropriate Output Streams
			FileOutputStream File_out = new FileOutputStream("namedstudentdata.txt");
			ObjectOutputStream Obj_out = new ObjectOutputStream(File_out);
			//Serializing Data
			Obj_out.writeObject(Array_out);
			//Closing Output Streams
			Obj_out.close();
			File_out.close();
		}
		//Catching Exception
		catch(IOException IOexc){
			IOexc.printStackTrace();
		}
		
		try{
			//Create Appropriate Input Streams
			FileInputStream File_in = new FileInputStream("namedstudentdata.txt");
			ObjectInputStream Obj_in = new ObjectInputStream(File_in);
			//De-serializing Data
			Array_in =  (StudentData[]) Obj_in.readObject();
			//Writing Data to individual Array Cells
			for (int k=0; k<=Array_in.length-1; k++){
				System.out.println("Array-in Cell " + k + ":   " + Array_in[k].toString());
			}
			//Closing Input Streams
			Obj_in.close();
			File_in.close();
		}
		//Catching Exceptions
		catch(IOException IOexec2){
			IOexec2.printStackTrace();
		}
		catch(ClassNotFoundException NotFound){
			NotFound.printStackTrace();
		}
		
		
	}

}
