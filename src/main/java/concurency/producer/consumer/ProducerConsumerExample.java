package concurency.producer.consumer;

import java.util.concurrent.TimeUnit;

public class ProducerConsumerExample {

    public static void main(String ... args) {
        Warehouse warehouse = new Warehouse();

        Thread threadProd = new Thread(new Producer(warehouse));
        threadProd.setName("Producer thread");
        threadProd.setDaemon(false);
        threadProd.start();

        Thread consThread = new Thread(new Consumer(warehouse));
        consThread.setName("Consumer thread");
        consThread.start();

        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Quit");
    }

}
