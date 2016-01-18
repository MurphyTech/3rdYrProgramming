/**
 * David Murphy
 * d.murphy53@nuigalway.ie
 * 12493252
 */
package stock;

import java.util.Random;
	
public class Main {
	
	public static StockRoom  room = new StockRoom();
	public static Random number = new Random();
	
	public static void main(String[] args){
	//04/11/15
			for (int i=0; i<10; i++){
				//Create some threads
				Thread thread = new Thread(new Driver(room));
				thread.start();
			}
			
			try{
				Thread.sleep(10000);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			
			System.out.println("Closing...");
			System.exit(1);
	}
	 public static String alphaNuma = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	 
	 public static String nameGen(){
		 return String.format("%s%s-%d", alphaNuma.charAt(random(0,36)), alphaNuma.charAt(random(0,36)), random(0,256));
	 }
	 
	 public static int random(int min, int max){
		 return number.nextInt(max - min) + min;
	 }
}
