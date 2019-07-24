package socket.datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class DatagramReceiver {

    private static long receivedCounter = 0;
    private static AtomicInteger processedCounter = new AtomicInteger();

    public static Queue<String> queue = new ConcurrentLinkedQueue<String>();

    public static void startReceiver(int port) {
        new Thread(() -> {
            try {
                receiveData(port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void startProcessors(int processCount) {
        for (int i = 1; i <= processCount; i++) {
            Thread thread = new Thread(() -> processData());
            thread.setName("Processor " + i);
            thread.start();
        }
    }

    public static void main(String[] args) throws Exception {
        startReceiver(3000);
        int processCount = 2;
        startProcessors(processCount);
    }

    public static void receiveData(int port) throws IOException {
        DatagramSocket ds = new DatagramSocket(port);

        byte[] buf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, 1024);

        while (true) {
            receivedCounter++;
            ds.receive(dp);

            String stringRawData = new String(dp.getData(), 0, dp.getLength());
            System.out.printf("\n %tT %1$tL   %d received: %s from %s", new Date(), receivedCounter, stringRawData, dp.getAddress());

            queue.add(stringRawData);
        }

        //ds.close();
    }

    public static void processData() {
        while (true) {
            String stringRawData = queue.poll();
            if (stringRawData != null) {
                System.out.printf("\n%s Queue size: %d", Thread.currentThread().getName(), queue.size());
                int processed = processedCounter.incrementAndGet();
                System.out.printf("\n %s, %d Data in processing: %s", Thread.currentThread().getName(), processed, stringRawData);
            }
            sleep(1);
        }
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
