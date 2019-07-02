package students.task.solution.tree;

public class MainTreeExample {

    public static void main(String[] args) {
        int[] inputData = {-1, 5, 500, 1000, 50, 101, 4, 5, 44, 3, 2, 6, 8, 3, 5, 0, -5, 5, -6, -8, 100};
        System.out.println(checkIfPresent(
                inputData,
                1000000));
    }

    public static boolean checkIfPresent(int[] array, int value){
        CustomTreeExample tree = new CustomTreeExample();

        for (int i = 0; i < array.length; i++) {
           tree.insert(array[i]);
        }

        return tree.checkIfExist(value);
    }
}
