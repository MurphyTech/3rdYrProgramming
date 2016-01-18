/*
 * David Murphy
 * 12493252
 * d.murphy53@nuigalway.ie
 */
package assign3_part3;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccess {
	public static void main(String args[]) throws IOException{
		//Create RandomAccessFile Object
		RandomAccessFile RanFile = new RandomAccessFile("file.txt", "rw");
		//Find length of RandomAccessFiel Object
		long filelen = RanFile.length();
		//Navigate to end of RandomAccessFile Object
		RanFile.seek(filelen);
		//Append File
		try{
			RanFile.write("Hey, I'm appending a file all on my own!!! :)".getBytes());
		}
		//Catch Exception
		catch (IOException IOexec){
			IOexec.printStackTrace();
		}
		//Close Stream
		RanFile.close();
	}
}
