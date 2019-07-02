package concurency.producer.consumer;

public class Producer implements Runnable{
    private Warehouse warehouse;

    public Producer(Warehouse warehouse){
        this.warehouse = warehouse;
    }


    @Override
    public void run() {
        for (int i = 0; i< 60; i++){
            System.out.println("total produced: "+i);
            warehouse.put();
        }
    }
}
