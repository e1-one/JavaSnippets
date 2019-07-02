package concurency.producer.consumer;

import java.util.concurrent.TimeUnit;

public class Warehouse {

    private int prod = 0;

    synchronized void get(){
        System.out.println("Get");
        if(prod < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread()+"get product. prod count:"+prod);
        prod --;
        notify();
    }

    synchronized void put(){
        System.out.println("Put");
        if(prod > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            System.out.println("Loading product");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread()+"put product");
        prod++;
        notify();
    }
}
