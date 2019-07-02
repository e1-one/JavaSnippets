package concurency.producer.consumer;

public class Consumer implements Runnable {

    private Warehouse warehouse;

    public Consumer(Warehouse warehouse) {
        this.warehouse = warehouse;
    }


    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("total consumed: " + i);
            warehouse.get();
        }
    }
}
