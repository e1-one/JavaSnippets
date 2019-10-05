package socket.simple;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class MySocketServer implements Runnable {

    private ServerSocket server = null;

    public MySocketServer(ServerSocket ss) {
        server = ss;

        this.newListener();
    }

    private void newListener() {
        (new Thread(this)).start();
    }

    public void run() {
        Socket socket;

        // accept a connection
        try {
            socket = server.accept();
        } catch (IOException e) {
            System.out.println("Class HttpServer died: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // create a new thread to accept the next connection
        this.newListener();

        try {
            OutputStream rawOut = socket.getOutputStream();

            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    rawOut)));
            try {
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(socket.getInputStream()));

                byte[] abc_hello_worlds = new String("abc hello world").getBytes();
                byte[] byteCodes = abc_hello_worlds;//new byte[]{127,0,0,0,127};
                try {
                    //out.print("Some text \r\n");
                    //out.flush();
                    rawOut.write(byteCodes);
                    rawOut.flush();
                } catch (IOException ie) {
                    ie.printStackTrace();
                    return;
                }

            } catch (Exception e) {
                e.printStackTrace();
                // write out error response
                //out.println("HTTP/1.0 400 " + e.getMessage() + "\r\n");
                //out.println("Content-Type: text/html\r\n\r\n");
                //out.flush();
            }

        } catch (IOException ex) {
            // eat exception (could log error to log fileserver, but
            // write out to stdout for now).
            System.out.println("Error writing response: " + ex.getMessage());
            ex.printStackTrace();

        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }

}