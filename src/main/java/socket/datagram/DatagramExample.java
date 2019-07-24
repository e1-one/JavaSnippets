package socket.datagram;


public class DatagramExample {

    //this example shows that not all packets are processed with UDP protocol. 74% of packets could be lost.

    public static void main(String[] args) throws Exception {
        int port = 3000;

        DatagramReceiver.startReceiver(port);
        int processCount = 2;
        //DatagramReceiver.startProcessors(processCount);

        int size = DatagramReceiver.queue.size();
        System.out.println(size);

        DatagramSender.startSendAData(port, 1000, 4);


        System.out.println("Main thread execution end");

    }

}
