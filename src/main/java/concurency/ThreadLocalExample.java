package concurency;

import java.util.UUID;

public class ThreadLocalExample extends Thread {

    static ThreadLocal<String>  threadContext = new ThreadLocal();

    public static void main(String[] args) {


        ThreadLocalExample t1 = new ThreadLocalExample();
        t1.start();
        ThreadLocalExample t2 = new ThreadLocalExample();
        t2.start();

    }

    @Override
    public void run() {
        System.out.println(threadContext.get());
        threadContext.set(UUID.randomUUID().toString());
        System.out.println(threadContext.get());
    }
}
