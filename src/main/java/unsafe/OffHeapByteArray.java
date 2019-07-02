package unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * This array will be stored in a special memory region: off-heap and not controlled by the GC process.
 */
class OffHeapByteArray {

    private final static int BYTE = 1;
    private final long arraySize;
    private final long arrayBeginningAddress;

    public OffHeapByteArray(long arraySize) throws NoSuchFieldException, IllegalAccessException {
        this.arraySize = arraySize;
        arrayBeginningAddress = getUnsafe().allocateMemory(arraySize * BYTE);
    }

    private static Unsafe getUnsafe() throws IllegalAccessException, NoSuchFieldException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return (Unsafe) f.get(null);
    }

    public void set(long index, byte value) throws NoSuchFieldException, IllegalAccessException {
        checkArrayBoundaries(index);
        getUnsafe().putByte(arrayBeginningAddress + index * BYTE, value);
    }

    public int get(long index) throws NoSuchFieldException, IllegalAccessException {
        checkArrayBoundaries(index);
        return getUnsafe().getByte(arrayBeginningAddress + index * BYTE);
    }

    public long size() {
        return arraySize;
    }

    /**
     * Release memory back to the OS.
     */
    public void freeMemory() throws NoSuchFieldException, IllegalAccessException {
        getUnsafe().freeMemory(arrayBeginningAddress);
    }

    private void checkArrayBoundaries(long index){
        if(index < 0 || index > arraySize){
            throw new IllegalArgumentException("index: "+index+" is out of boundaries");
        }
    }
}