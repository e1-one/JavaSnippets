package students.task.solution.encoder;

public final class CharacterHelper {

    private static Character[] numbers = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    public static boolean isNumber(Character input){
        for (int i = 0; i < numbers.length; i++) {
            if(numbers[i].equals(input)){
                return true;
            }
        }
        return false;
    }
}
