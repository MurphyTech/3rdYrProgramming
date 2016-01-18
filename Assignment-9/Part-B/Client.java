/**
 * David Murphy
 * 12493252
 * d.murphy53@nuigalway.ie
 */
package partB;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client extends JFrame{
private static final long serialVersionUID = 1L;
private JTextField enterField;
private JTextArea displayArea; 
private DatagramPacket sendPacket, receivePacket;
private DatagramSocket socket;

// set up GUI and DatagramSocket
public Client()  { 
	super ( "Client");
	
    Container container = getContentPane();
    enterField = 
    new JTextField( "Type message here" );
    enterField.addActionListener( new ActionListener() { 
    
    // create and send a packet
   	public void actionPerformed( ActionEvent event )  { 

   		
   		//create and send packet
    		 try{ 
                 displayArea.append(    "\nSending packet containing: " + event.getActionCommand() + "\n" ); 
                 //get message from textfield and convert to 
                 // array of bytes
                 String message = event.getActionCommand(); 
                 byte data[] = message.getBytes(); 
                 //create sendPacket
                 sendPacket = new DatagramPacket(data, data.length,InetAddress.getLocalHost(), 5000 ); 
                 // send packet
                 socket.send( sendPacket ); 
                 displayArea.append( "Packet sent\n" );
                 displayArea.setCaretPosition( displayArea.getText().length() ); 
              } 

              // process problems creating or sending packet

    		 catch( IOException ioException ) {
    			 displayArea.append( ioException.toString() + "\n" );
                 ioException.printStackTrace();
    		 }
           }  //end actionPerformed
        }//end anonymous inner class
      );//end call to addActionListener
      	container.add( enterField, BorderLayout.NORTH);
      	displayArea = new JTextArea();
      	container.add( new JScrollPane( displayArea ),BorderLayout. CENTER);
      	setSize( 400,300);
      	setVisible(true);
      	//create DatagramSocket for sending and receiving packets
      	try{socket = new DatagramSocket();
     }
      	//catch problems creating DatagramSocket
      	catch( SocketException socketException ) {
        socketException.printStackTrace();
        System.exit( 1);
     }
  }  //end Client constructor
 
// wait for packets to arrive from Server, 
// then display packet contents
public void waitForPackets(){
	// loop forever
	while(true) {
        // receive packet and display contents
		try {
        // set up packet
		byte data[] = new byte[100];
        receivePacket = new DatagramPacket( data, data.length );
        // wait for packet
        socket.receive( receivePacket );
        // display packet contents
        displayPacket();
        }

        // process problems receiving or displaying packet

		catch( IOException exception ) {
           displayArea.append( exception.toString() + "\n");
           exception.printStackTrace();
        }
     }  //end while
  } // end method waitForPackets
  
// display contents of receivePacket
  private void displayPacket(){
     displayArea.append( 	"\nPacket received:" +"\nFrom host: "+ receivePacket.getAddress() +
    		 				"\nHost port: " + receivePacket.getPort() +
    		 				"\nLength: " + receivePacket.getLength() +
    		 				"\nContaining:\n\t" + new String( receivePacket.getData(),0,receivePacket.getLength() ) );

     displayArea.setCaretPosition(displayArea.getText().length() );
    }

  // execute application

public static void main(String args[]) {
     Client application = new Client();
     application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     application.waitForPackets();
  }
}//end class Client
