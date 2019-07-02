package unsafe;

import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.management.ManagementFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OffHeapByteArrayTest {

    @Test
    void invalidSize() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new OffHeapByteArray(-100));
    }

    @Test
    void outOfBoundaries() throws NoSuchFieldException, IllegalAccessException {
        long arraySize = (long) 10;
        OffHeapByteArray array = new OffHeapByteArray(arraySize);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            array.get(arraySize + 1);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            array.set(arraySize + 1, (byte)1);
        });
        array.freeMemory();
    }

    @Test
    void setAndGetAllArrayCells() throws NoSuchFieldException, IllegalAccessException {
        long arraySize = (long) 10;
        OffHeapByteArray array = new OffHeapByteArray(arraySize);

        int sum = 0;

        for (int i = 0; i < 10; i++) {
            array.set((long) i, (byte) 2);
            sum += array.get((long) i);
        }

        assertEquals(array.size(), arraySize);
        assertEquals(20, sum);

        array.freeMemory();
    }

    @Test
    @Ignore("The test is ok. However, it consumes time and memory.")
    /*
    No heap size change can be visible with JVisualVm.
    Memory is not allocated in the heap.
    But you could see actual RAM consumption with for example ProcessHacker Windows application.
     */
    void allocateExtremelyBigArray() throws NoSuchFieldException, IllegalAccessException {
        String pid = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println("Pid: "+ pid);
        sleepSeconds(5);

        long arraySize3Gb = (long) 3 * 1024 * 1024 * 1024;
        OffHeapByteArray array = new OffHeapByteArray(arraySize3Gb);
        System.out.println("Array with size "+array.size()+" bytes was allocated.");

        sleepSeconds(20);

        array.freeMemory();
        System.out.println("Free memory done.");

        System.out.println("Java process is going to be shutdown");
        sleepSeconds(5);
    }

    private void sleepSeconds(int s) {
        try {
            System.out.println("Waiting seconds: "+ s);
            Thread.sleep(1000 * s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}