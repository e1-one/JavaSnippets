package socket.secure.fileserver;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class HttpServer implements Runnable {

    private ServerSocket server = null;
    /**
     * Constructs a ClassServer based on <b>ss</b> and
     * obtains a fileserver's bytecodes using the method <b>getBytes</b>.
     *
     */
    protected HttpServer(ServerSocket ss)
    {
        server = ss;
        newListener();
    }

    /**
     * Returns an array of bytes containing the bytes for
     * the fileserver represented by the argument <b>path</b>.
     *
     * @return the bytes for the fileserver
     * @exception FileNotFoundException if the fileserver corresponding
     * to <b>path</b> could not be loaded.
     * @exception IOException if error occurs reading the class
     */
    public abstract byte[] getBytes(String path)
            throws IOException, FileNotFoundException;

    /**
     * The "listen" thread that accepts a connection to the
     * server, parses the header to obtain the fileserver name
     * and sends back the bytes for the fileserver (or error
     * if the fileserver is not found or the response was malformed).
     */
    public void run()
    {
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
                // get path to class fileserver from header
                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
//                inputStreamReader.

                BufferedReader in =
                        new BufferedReader(inputStreamReader);
                String path = getPath(in);
                // retrieve bytecodes
                byte[] bytecodes = getBytes(path);
                // send bytecodes in response (assumes HTTP/1.0 or later)
                try {
                    out.print("HTTP/1.0 200 OK\r\n");
                    out.print("Content-Length: " + bytecodes.length +
                            "\r\n");
                    out.print("Content-Type: image/png\r\n\r\n");
                    out.flush();
                    rawOut.write(bytecodes);
                    rawOut.flush();
                } catch (IOException ie) {
                    ie.printStackTrace();
                    return;
                }

            } catch (Exception e) {
                e.printStackTrace();
                // write out error response
                out.println("HTTP/1.0 400 " + e.getMessage() + "\r\n");
                out.println("Content-Type: text/html\r\n\r\n");
                out.flush();
            }

        } catch (IOException ex) {
            // eat exception (could log error to log fileserver, but
            // write out to stdout for now).
            System.out.println("error writing response: " + ex.getMessage());
            ex.printStackTrace();

        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * Create a new thread to listen.
     */
    private void newListener()
    {
        (new Thread(this)).start();
    }

    /**
     * Returns the path to the fileserver obtained from
     * parsing the HTML header.
     */
    private static String getPath(BufferedReader in)
            throws IOException
    {
        String line = in.readLine();
        String path = "";
        // extract class from GET line
        if (line.startsWith("GET /")) {
            line = line.substring(5, line.length()-1).trim();
            int index = line.indexOf(' ');
            if (index != -1) {
                path = line.substring(0, index);
            }
        }

        // eat the rest of header
        do {
            line = in.readLine();
        } while ((line.length() != 0) &&
                (line.charAt(0) != '\r') && (line.charAt(0) != '\n'));

        if (path.length() != 0) {
            return path;
        } else {
            throw new IOException("Malformed Header");
        }
    }
}