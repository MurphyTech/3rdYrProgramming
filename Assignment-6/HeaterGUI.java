package assign6;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class HeaterGUI extends JFrame {
	//ELEMENTS
	private static JToggleButton On, Off;
	private static JToggleButton Manual, Timed;
	private JSlider heatSlider;
	private JButton status;
	private JTextArea statusText;
	//STATUS STRINGS WITH PRESET VALUES
	private String ON_OFF = "OFF";
	private String ControlMethod = "Manual Control";

	
	
	public HeaterGUI(){
		//CREATE PANEL AND CONTAINER
		JPanel pane = new JPanel();
		pane.setLayout(null);
		Container container = getContentPane();
		container.add(pane);
		
		//ON
		On = new JToggleButton("On");
		On.setLocation(0,0);
		On.setSize(120,80);
		pane.add(On);
		
		//OFF
		Off = new JToggleButton("Off");
		Off.setLocation(120,0);
		Off.setSize(120,80);
		pane.add(Off);
		
		//MANUAL
		Manual = new JToggleButton("Manual");
		Manual.setLocation(0,80);
		Manual.setSize(120,80);
		pane.add(Manual);
		
		//TIMED
		Timed = new JToggleButton("Timed");
		Timed.setLocation(120,80);
		Timed.setSize(120,80);
		pane.add(Timed);
		
		//HEATER SLIDER
		heatSlider = new JSlider(SwingConstants.HORIZONTAL,0,5,1);
		heatSlider.setPaintTicks(true);
		heatSlider.setPaintLabels(true);
		heatSlider.setMajorTickSpacing(1);
		heatSlider.setLocation(10,160);
		heatSlider.setSize(220,80);
		pane.add(heatSlider);
		
		//STATUS BUTTON
		status = new JButton("Status");
		status.setLocation(0,240);
		status.setSize(240,80);
		pane.add(status);
		
		//STATUS TEXT FIELD
		statusText = new JTextArea();
		statusText.setLocation(0, 320);
		statusText.setSize(240,80);
		pane.add(statusText);
	
		//HANDLERS
		ButtonHandler handler = new ButtonHandler();
		On.addActionListener( handler );
		Off.addActionListener( handler );
		Manual.addActionListener( handler );
		Timed.addActionListener( handler );
		status.addActionListener( handler);
		
		
		//SLIDER HANDLER
		heatSlider.addChangeListener(  
				new ChangeListener() {
				//HANDLE CHANGE IN SLIDER VALUE
				public void stateChanged( ChangeEvent e ){
					//PRINT CHANGE TO CONSOLE
					System.out.println("Slider Value changed to: " + heatSlider.getValue());
					//RESET STATUS TEXT FIELD INFO NOT UP TO DATE
					statusText.setText("");
				}
		}  );
	
		
		//SETTING WINDOW CHARACTERISTICS
		setSize(240, 430);
		setVisible(true);
		setResizable(false);
	}
	
	public static void main(String[] args){
		HeaterGUI HeatGUIObj = new HeaterGUI();
		
		HeatGUIObj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Off.setSelected(true);
		Manual.setSelected(true);
	}
	
	public class ButtonHandler implements ActionListener{
public void actionPerformed( ActionEvent event){
			
			if (event.getActionCommand() == "On"){
				//PRINT CHANGE TO CONSOLE
				System.out.println( event.getActionCommand() + "  pressed");
				//IF ON CAN'T BE OFF
				Off.setSelected(false);
				//CHANGE STATUS STRING
				ON_OFF = "ON";
				//RESET STATUS TEXT FIELD, INFO NO OUT OF DATE
				statusText.setText("");
		
			}
			else if (event.getActionCommand() == "Off"){
				//PRINT CHANGE TO CONSOLE
				System.out.println( event.getActionCommand() + "  pressed");
				//IF OFF CAN'T BE ON
				On.setSelected(false);
				//CHANGE STATUS STRING
				ON_OFF = "OFF";
				//RESET STATUS TEXT FIELD, INFO NO OUT OF DATE
				statusText.setText("");
			}
			else if (event.getActionCommand() == "Manual"){
				//PRINT CHANGE TO CONSOLE
				System.out.println( event.getActionCommand() + "  pressed");
				//IF MANUAL NOT TIMED
				Timed.setSelected(false);
				//CHANGE STATUS STRING
				ControlMethod = "Manual Control";
				//RESET STATUS TEXT FIELD, INFO NO OUT OF DATE
				statusText.setText("");
			}
			else if (event.getActionCommand() == "Timed"){
				//PRINT CHANGE TO CONSOLE
				System.out.println( event.getActionCommand() + "  pressed");
				//IF TIMED CAN'T BE MANUAL
				Manual.setSelected(false);
				//CHANGE STATUS STRING
				ControlMethod = "Timed Control";
				//RESET STATUS TEXT FIELD, INFO NO OUT OF DATE
				statusText.setText("");
			}
			else if (event.getActionCommand() == "Status"){
				statusText.setText("Status:\n" + ON_OFF + "\n" + ControlMethod + "\n" +   "Heat Intensity: " + heatSlider.getValue());
				//PRINT CHANGE TO CONSOLE
				System.out.println("TextField Updated");
			}
		}
	}

}
