package partA;

import java.io.IOException;

public class Main {
    public static final String HOST = "0.0.0.0";
    public static final int PORT = 8089;
   

    public static void main(String[] args) {
        // Create our server in it's own thread
        Thread server = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Start the server listening on PORT
                    (new Server(PORT)).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread client = new Thread(new Runnable() {
            @Override
            public void run() {
                // Create a new client
                new Client(HOST, PORT);
            }
        });

        client.start();
        server.start();
    }
}
