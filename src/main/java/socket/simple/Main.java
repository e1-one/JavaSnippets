package socket.simple;

import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;


public class Main {


    public static void main(String[] args)
    {
        System.out.println("USAGE: java ...Main port");

        int port = 4001;

//        if (args.length >= 1) {
//            port = Integer.parseInt(args[0]);
//        }

        try {
            ServerSocketFactory ssf = ServerSocketFactory.getDefault();
            ServerSocket ss = ssf.createServerSocket(port);
            new MySocketServer(ss);
        } catch (IOException e) {
            System.out.println("Unable to start ClassServer: " +
                    e.getMessage());
            e.printStackTrace();
        }
        System.out.println("End");
    }
}
