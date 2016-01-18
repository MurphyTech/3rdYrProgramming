/**
 * David Murphy
 * 12493252
 * d.murphy53@nuigalway.ie
 */
package partB;

import java.io.*;
import java.net.*;
import java.awt.*;    
import javax.swing.*;

public class Server extends JFrame {
private static final long serialVersionUID = 1L;
private JTextArea displayAreaS;
private DatagramPacket sendPacket, receivePacket;
private DatagramSocket socket;
//set up GUI and DatagramSocket
public Server(){
	super("Server");
		displayAreaS = new JTextArea();
		getContentPane().add(new JScrollPane( displayAreaS ),BorderLayout.CENTER);
		setSize( 400,300);
		setVisible( true);

//create DatagramSocket for sending and receiving packets
		try
		{
			socket = new DatagramSocket( 5000);
		}
//process problems creating DatagramSocket
		catch( SocketException socketException ) {
        socketException.printStackTrace();
        System.exit(1);
		}
  }  

//end Server constructor
//wait for packets to arrive, then display data and echo
// packet to client

public void waitForPackets()  {
//loop forever
	while( true) {
//receive packet, display contents, echo to client
	try{
           // set up packet
		byte data[] = new byte[ 100 ];
		receivePacket =new DatagramPacket( data, data.length );
        // wait for packet
        socket.receive( receivePacket );
        // process packet
        displayPacket();
        // echo information from packet back to client
        sendPacketToClient();
        }

        // process problems manipulating packet
		catch( IOException ioException ) {
           displayAreaS.append( ioException.toString() + "\n" );
           ioException.printStackTrace();
        }
     }  //end while
  } //end method waitForPackets
//display packet contents

private void
displayPacket()  { 
     displayAreaS.append( 	"\nPacket received:"+
    		 				"\nFrom host: " + receivePacket.getAddress() +
    		 				"\nHost port: "+ receivePacket.getPort() +
    		 				"\nLength: " + receivePacket.getLength() +
    		 				"\nContaining:\n\t" + new String( receivePacket.getData(),0,receivePacket.getLength() ) );
   }

//echo packet to client
private void sendPacketToClient() throws IOException {
     displayAreaS.append("\n\nEcho data to client..." );
     // create packet to send
      sendPacket = new DatagramPacket( receivePacket.getData(),receivePacket.getLength(), receivePacket.getAddress(),        receivePacket.getPort() );
     // send packet
      socket.send( sendPacket );
     displayAreaS.append( "Packet sent\n");
     displayAreaS.setCaretPosition( displayAreaS.getText().length() );
  }

//execute application

public static void main(String args[])  {
       Server application = new Server();
       application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       application.waitForPackets();
  }

}  
