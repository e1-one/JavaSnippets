package random.xor;

public class XorRandomExample {

    private static long x;

    public static void main(String[] args) {
        x = System.nanoTime();
        while (true) {
            System.out.println(randomLong());
        }
    }

    public static long randomLong() {
        x ^= (x << 21);
        x ^= (x >>> 35);
        x ^= (x << 4);
        return x;
    }
}
