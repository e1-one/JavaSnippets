package socket.datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

public class DatagramSender {

    public static void startSendAData(int port, int sendsCount, int sendersCount) throws SocketException, IOException {
        for (int i = 1; i <= sendersCount; i++) {
            Thread thread = new Thread(() -> {
                try {
                    sendPackets(port, sendsCount);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.setName("Sender " + i);
            thread.start();
        }

    }

    private static void sendPackets(int port, int sendsCount) throws IOException {
        String threadName = Thread.currentThread().getName();

        DatagramSocket ds = new DatagramSocket();


        InetAddress ip = InetAddress.getByName("127.0.0.1");
        System.out.println(threadName+ " Sending "+sendsCount+" packets");

        for (int i = 1; i <= sendsCount; i++) {
            String str = threadName +" data# " + i;
            DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), ip, port);
            ds.send(dp);
        }
        ds.close();
        System.out.printf("\n ############################# %tT%n Sending %d packets: Done.", threadName, new Date(), sendsCount);
    }

}
