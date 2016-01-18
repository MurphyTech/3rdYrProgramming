/**
 * David Murphy
 * 12493252
 * d.murphy53@nuigalway.ie
 */
package assign8;

import java.util.Random;

public class Producer extends Thread {
	public int ID;
	public StringObject strObj;
	private static Random random = new Random();
	
	public Producer(int ID, StringObject strObj){
		this.ID = ID;
		this.strObj = strObj;
	}
	
	public void run(){
		try{
			//PRETEND TO DO A THING
			Thread.sleep(500 + random.nextInt(1200));
			//PRODUCE A THING
			this.strObj.put();
			
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
}
