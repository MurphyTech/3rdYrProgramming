package partA;

import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Server implements Runnable{
    // Server control
    private int port = 8088; // Default to 8088 port
    private ServerSocket sock;
    private ExecutorService pool; 
    public Server(int port) {
        this.port = port;
    }
    
    @Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

    /**
     * Start the server.
     * @throws IOException
     */
    public void start() throws IOException {
        this.sock = new ServerSocket(this.port);

        Socket sock; int i = 0;
        while(true && ++i > 0) 
            // Wait for a new connection
            sock = sock.accept();
           

       }
    
    private static final long serialVersionUID = 1L;
    private JTextArea displayAreaS;
    private DatagramPacket sendPacket;

	FileInputStream receivePacket;
	
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
    			FileInputStream socketIN = new FileInputStream();
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
    		receivePacket =new FileInputStream( data, data.length );
            FileInputStream socketIN;
			// wait for packet
    		((FileInputStream) socketIN).FileInputStream( receivePacket );
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
          sock.send( sendPacket );
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
