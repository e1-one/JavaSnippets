package socket.secure.fileserver;

import java.io.*;
import java.net.*;
import java.security.KeyStore;
import javax.net.*;
import javax.net.ssl.*;

/* socket.secure.fileserver.MySocketServer.java -- a simple fileserver server that can server
 * Http get request in both clear and secure channel
 *
 * The socket.secure.fileserver.MySocketServer implements a ClassServer that
 * reads files from the fileserver system. See the
 * doc for the "Main" method for how to run this
 * server.
 */

public class FileSystemHttpServer extends HttpServer {

    private String docroot;

    public final static int DefaultServerPort = 2001;

    /**
     * Constructs a socket.secure.fileserver.MySocketServer.
     *
     * @param docroot the path where the server locates files
     */
    public FileSystemHttpServer(ServerSocket ss, String docroot) throws IOException
    {
        super(ss);
        this.docroot = docroot;
    }

    /**
     * Returns an array of bytes containing the bytes for
     * the fileserver represented by the argument <b>path</b>.
     *
     * @return the bytes for the fileserver
     * @exception FileNotFoundException if the fileserver corresponding
     * to <b>path</b> could not be loaded.
     */
    public byte[] getBytes(String path)
            throws IOException
    {
        System.out.println("reading: " + path);
        File f = new File(docroot + File.separator + path);
        int length = (int)(f.length());
        if (length == 0) {
            throw new IOException("File length is zero: " + path);
        } else {
            FileInputStream fin = new FileInputStream(f);
            DataInputStream in = new DataInputStream(fin);

            byte[] bytecodes = new byte[length];
            in.readFully(bytecodes);
            return bytecodes;
        }
    }

    /**
     * Main method to create the class server that reads
     * files. This takes two command line arguments, the
     * port on which the server accepts requests and the
     * root of the path. To start up the server: <br><br>
     *
     * <code>   java socket.secure.fileserver.MySocketServer <port> <path>
     * </code><br><br>
     *
     * <code>   new socket.secure.fileserver.MySocketServer(port, docroot);
     * </code>
     */
    public static ServerSocketFactory getServerSocketFactory(String type) {
        if (type.equals("TLS")) {
            SSLServerSocketFactory ssf = null;
            try {
                System.out.println("Creating secure socket factory");
                // set up key manager to do server authentication
                SSLContext ctx;
                KeyManagerFactory kmf;
                KeyStore ks;
                char[] passphrase = "password".toCharArray();

                ctx = SSLContext.getInstance("TLS");
                kmf = KeyManagerFactory.getInstance("SunX509");
                ks = KeyStore.getInstance("JKS");

                ks.load(new FileInputStream("keystore.jks"), passphrase);
                kmf.init(ks, passphrase);
                ctx.init(kmf.getKeyManagers(), null, null);

                ssf = ctx.getServerSocketFactory();
                return ssf;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Default socket factory");
            return ServerSocketFactory.getDefault();
        }
        return null;
    }
}