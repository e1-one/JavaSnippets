package concurency.oom.error;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class OutOfMemoryErrorThreadTest {

    private static final int MEGABYTE = (1024*1024);

    private static final int MB_COUNT_TO_ALLOCATE = 1000;
    private static final int JOBS_THREAD_COUNT = 100;

    private static AtomicInteger counter = new AtomicInteger();
    private static MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();



    //this example shows us that even after OOME application continue work
    public static void main(String ... args){
        System.out.println("wait N seconds. You cold connect with JVisualVm");
        sleep(5_000);
        printHeapStatistic(0);

        List<Thread> threadList = new LinkedList<>();
        for(int i = 0; i < JOBS_THREAD_COUNT; i++){
            threadList.add(new Thread(() -> allocateHugePortionOfHeapMemory()));
        }
        for(int i = 0; i < JOBS_THREAD_COUNT; i++){
            Thread thread = threadList.get(i);
            sleepAndStart(thread);
        }

        for(int i = 0; i < JOBS_THREAD_COUNT; i++){
            threadJoin(threadList.get(i));
        }

        System.out.println("End");
    }


    private static void sleepAndStart(Thread thread){
        sleep(2_000);
        thread.start();
    }

    private static void threadJoin(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //allocates memory and then wait some time
    private static void allocateHugePortionOfHeapMemory(){
        int jobId = counter.incrementAndGet();
        printHeapStatistic(jobId);
        try {

            byte[] bytes = new byte[MEGABYTE * MB_COUNT_TO_ALLOCATE];

            sleep(10_000);

            byte aByte = bytes[10000];
        } catch (OutOfMemoryError error){
            System.out.println("INSIDE JOB: "+jobId + "!=============== OutOfMemoryError ");
            throw error;
        }
        System.out.println("INSIDE JOB: "+jobId+" +++ Success end of a Job");
    }



    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printHeapStatistic(int id){
        MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
        long maxMemory = heapUsage.getMax() / MEGABYTE;
        long usedMemory = heapUsage.getUsed() / MEGABYTE;
        System.out.println("INSIDE JOB: "+ id + " . HeapStatistic : Memory Use :" + usedMemory + "M/" + maxMemory + "M");
    }

    private static void printStatisticNonHeap(int id){
        MemoryUsage heapUsage = memoryBean.getNonHeapMemoryUsage();
        long maxMemory = heapUsage.getMax() / MEGABYTE;
        long usedMemory = heapUsage.getUsed() / MEGABYTE;
        System.out.println("INSIDE JOB: "+id + " . NonHeap : Memory Use :" + usedMemory + "M/" + maxMemory + "M");
    }

}
