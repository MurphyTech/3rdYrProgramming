package partA;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import org.apache.commons.io.IOUtils;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

@SuppressWarnings("serial")
public class Client extends JFrame {
	Dimension screenSize = getToolkit().getScreenSize();
	private int port;
	private String host;
	private Socket socket;
	    
    //ELEMENTS
	private static JButton Select, Upload, Download;
	private JLabel uploadLabel;
	private JList<String> filelist;
	private JTextArea consoleText;
	
	private File currentUploadFile;
	
	public Client(){
		
		GUI();
		
	}
	
	public Client(String host, int port) {
	        this.port = port;
	        this.host = host;

	       // setTitle("Sockets");
	       // setLocation((screenSize.width / 2), (screenSize.height / 2));

	        GUI();

	       // setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	       // pack();
	        //setVisible(true);

	        // Initially refresh the UI
	        try {
	            refresh();
	        } catch (IOException e) {
	            error("IO Error!");
	        }
	    }
	
	private void GUI(){
		//CREATE PANEL
		JPanel pane = new JPanel();
		pane.setLayout(null);
		JPanel consolePanel = new JPanel(new BorderLayout());
		Container container = getContentPane();
		container.add(pane);
		//container.add(consolePanel);
		
		//UPLOAD
		uploadLabel = new JLabel("No file selected.");
		uploadLabel.setLocation(0,0);
		uploadLabel.setSize(320, 60);
		pane.add(uploadLabel);
		
		Select = new JButton("Select File");
		Select.setLocation(0, 60);
		Select.setSize(320, 60);
		pane.add(Select);
		
		Upload = new JButton("Upload");
		Upload.setLocation(0, 120);
		Upload.setSize(320, 60);
		pane.add(Upload);
		
		//DOWNLOAD
		//ADDING DOWNLOAD BUTTON
		Download = new JButton("Download");
		
		Download.setLocation(320,120);
		Download.setSize(320, 60);
		pane.add(Download);
		//ADDING FILE LIST
		filelist = new JList<String>(new String[] { "File One", "File Two", "File Three"});
		filelist.setLocation(320, 0);
		filelist.setSize(320, 120);
		pane.add(filelist);
		
		//CONSOLE
		consoleText = new JTextArea();
        consoleText.setEnabled(false);
        consoleText.setBackground(Color.black);
        consoleText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        consoleText.setMargin(new Insets(4, 10, 10, 10));
        DefaultCaret caret = (DefaultCaret) consoleText.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(consoleText);
        
        consolePanel.add(scrollPane);
		consolePanel.setLocation(0, 180);
		consolePanel.setSize(640, 90);
		
		pane.add(consolePanel);
		
		//HANDLERS
        // Add the event handlers
        Select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSelectButton();
            }
        });
        Download.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDownloadButton();
            }
        });
        Upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUploadButton();
            }
        });
		
		
		
		setSize(640, 300);
		setVisible(true);
		setResizable(false);
	}
	
	protected void handleSelectButton() {
	        try {
	            select();
	        } catch (IOException e) {
	            error("IO Exception");
	        }
	    }
	protected void handleUploadButton() {
	        try {
	            upload();
	        } catch (IOException e) {
	            error("IO Exception");
	        }
	    }
	    
	protected void handleDownloadButton() {
	         try {
	            String selected = filelist.getSelectedValue().toString();

	            if(selected == null) {
	                error("No file selected.");
	                return;
	            }
	            download(selected);
	        } catch (IOException e) {
	            error("IO Exception");
	        }
	    }
	
	public static void main(String args[]){
		Client client = new Client();
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
    private void download(String path) throws IOException {
    	consoleText.append(">   Download\n");
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setDialogTitle("Select directory to save file");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = new File(path);
            String filename = file.getName();
            String outputPath = chooser.getSelectedFile() + "/" + filename;

            File outputFile = new File(outputPath);
            FileOutputStream fout = new FileOutputStream(outputFile);

            IOUtils.copy(commandStream("DOWNLOAD /" + filename), fout);
            refresh();
        } else {
            System.out.println("No Selection ");
            refresh();
        }
    }

    private void select() throws IOException {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Select file to select");
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            currentUploadFile = chooser.getSelectedFile();
            uploadLabel.setText(chooser.getName(currentUploadFile));
            refresh();
        } else {
            System.out.println("No Selection ");
            refresh();
        }
    }
	
	private void upload() throws IOException {
	        if(currentUploadFile == null) {
	            error("No file selected");
	            refresh();
	            return;
	        }

	        FileInputStream fin = new FileInputStream(currentUploadFile);
	        commandStream("UPLOAD " + currentUploadFile.getName(), fin);
	        // Refresh the filelist
	        refresh();
	    }
	
	private void refresh() throws IOException {
	        String files = command("LIST");
	        filelist.setListData(files.split("\n"));
	        consoleText.append(">   refreshed...\n");
	    }
	 
	private InputStream request(String data, InputStream stream) throws IOException {
	        Socket req = new Socket(host, port);
	        DataOutputStream out = new DataOutputStream(req.getOutputStream());
	        out.write(data.getBytes()); // Write the command

	        if(stream == null) {
	            // If we have no stream, just terminate
	            out.write("\4".getBytes());
	        } else {
	            out.write(" >>>".getBytes()); // Tell the server to expect raw bytes
	            IOUtils.copy(stream, out); // Pipe in the stream
	            out.close();
	            return null;
	        }

	        return req.getInputStream();
	    }
	 
	private InputStream request(String data) throws IOException {
        return request(data, null);
    }

    
    private InputStream commandStream(String command, InputStream stream) throws IOException {
        consoleText.append("> " + command + "\n");
        // Make the request to the server, convert the response input stream to a string and print
        return request(command, stream);
    }

    private InputStream commandStream(String command) throws IOException {
        return commandStream(command, null);
    }


	private String command(String command, InputStream stream) throws IOException {
		        InputStream input = commandStream(command, stream);
		        String data = IOUtils.toString(input);
		        consoleText.append(data + "\n");

		        return data;
		    }

	
	private String command(String command) throws IOException {
		        return command(command, null);
		    }
	protected void error(String message) {
	        JOptionPane.showMessageDialog(this,
	                message,
	                "Error",
	                JOptionPane.WARNING_MESSAGE);
	    }
}
