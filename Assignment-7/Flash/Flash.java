/**
 * David Murphy
 * d.murphy53@nuigalway.ie
 * 12493252
 */
package flash;

import javax.swing.*;
import java.awt.*; 
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

public class Flash implements Runnable{
	public boolean FlashingBool = true;
	public int sleep;
	public static JLabel label;

	public Flash(JLabel label){
		Flash.label = label;
	}//END FLASH
	
	
	public static void main(String args[]){
		//CREATE THE FRAME
		JFrame frame = new JFrame("GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//CREATE AND ADD THE LABEL TO THE FRAME
		label = new JLabel("Flashing!!!", SwingConstants.CENTER);
		label.setPreferredSize(new Dimension(300,100));
		frame.getContentPane().add(label,  BorderLayout.CENTER);
		
		//FRAME SETTINGS
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
		
		//CREATE FLASHING LABEL
		final Flash Flashing = new Flash(label);
		
		//GET THE USER DEFINED INPUT
		Flashing.sleep = getSleepTime();
		
		//ADDING MOUSE LISTENER TO THE FRAME
		frame.addMouseListener(new MouseListener(){
			//IF MOUSE BUTTON IS CLICKED
			@Override
			public void mouseClicked(MouseEvent mouseevent){}
			//IF MOUSE BUTTON IS RELEASED
			@Override
			public void mouseReleased(MouseEvent mouseEvent){}
			//IF MOUSE EVENT
			@Override
			public void mouseEntered(MouseEvent mouseEvnet){}
			//IF MOUSE EXITED
			@Override
			public void mouseExited(MouseEvent mouseEvent){}
			//IF MOUSED PRESSED(CLICKED AND RELEASED
			@Override
			public void mousePressed(MouseEvent mouseEvent){
				//CHANGE STATE OF FLASHING
				Flashing.FlashingBool = !Flashing.FlashingBool;
			}	
		});
		//CREATE THREAD
		Thread Flash = new Thread(Flashing);
		//START THREAD
		Flash.start();	
	}//END MAIN
	
	
	
	
	
	public static int getSleepTime(){
		System.out.println("Please Enter Flashing sleep");
		//START SCANNER
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		
		//SET SLEEP TIME TO USER INPUT
		int sleep = Integer.parseInt(input);
		
		//DO A CHECK TO SEE IF USER INPUT IS TOO SMALL, YOU MIGHT MISS IT & PRINT APPROPRIATE MESSAGE
		if(sleep < 50){
			System.out.println("Time too short, settign to 1 second");
			sleep = 1000;
		}
		else{
			System.out.println("SleepTime is: " + sleep);
		}
		scanner.close();
		
		return sleep;
	}//GETSLEEPTIME
	
	@Override
	public void run() {
		while(true){
			//IF IT'S FLASHING, FLASH
			if(FlashingBool){
				label.setVisible(!label.isVisible());
			}
			//MAKE THE THREAD SLEEP
			try {
				Thread.sleep(sleep);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}	
	}//END RUN
}//END CLASS
