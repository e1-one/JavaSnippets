package socket.secure.fileserver;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import java.io.IOException;
import java.net.ServerSocket;

import static socket.secure.fileserver.FileSystemHttpServer.DefaultServerPort;

public class Main {

    public static void main(String args[])
    {
        System.out.println(
                "USAGE: java socket.secure.fileserver.MySocketServer port docroot [TLS [true]]");
        System.out.println("");
        System.out.println(
                "If the third argument is TLS, it will start as\n" +
                        "a TLS/SSL fileserver server, otherwise, it will be\n" +
                        "an ordinary fileserver server. \n" +
                        "If the fourth argument is true,it will require\n" +
                        "client authentication as well.");

        int port = DefaultServerPort;
        String docroot = "";

        if (args.length >= 1) {
            port = Integer.parseInt(args[0]);
        }

        if (args.length >= 2) {
            docroot = args[1];
        }
        String type = "PlainSocket";
        if (args.length >= 3) {
            type = args[2];
        }

        System.out.println("port: "+ port + ", docroot: "+ docroot);
        try {
            ServerSocketFactory ssf =
                    FileSystemHttpServer.getServerSocketFactory(type);
            ServerSocket ss = ssf.createServerSocket(port);
            if (args.length >= 4 && args[3].equals("true")) {
                ((SSLServerSocket)ss).setNeedClientAuth(true);
            }
            new FileSystemHttpServer(ss, docroot);
        } catch (IOException e) {
            System.out.println("Unable to start ClassServer: " +
                    e.getMessage());
            e.printStackTrace();
        }
        System.out.println("End");
    }
}
