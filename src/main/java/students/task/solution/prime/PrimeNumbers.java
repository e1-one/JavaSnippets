package students.task.solution.prime;

public class PrimeNumbers {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            if(isPrime(i)){
                System.out.println(i);
            }
        }
    }

    static boolean isPrime(int i){
        for (int j = 2; j < i; j++) {
            if(i % j == 0){
                return false;
            }
        }
        return true;
    }

}
