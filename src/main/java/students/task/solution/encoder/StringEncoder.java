package students.task.solution.encoder;

public class StringEncoder {
    private static Character escapeSymbol = '\\';

    private StringBuilder encodedStringBuilder;

    private Character lastProcessedCharacter;
    private int lastProcessedCharacterCounter;

    public StringEncoder() {
        lastProcessedCharacter = null;
        lastProcessedCharacterCounter = 0;
    }

    public String encode(String stringToEncode){
        encodedStringBuilder = new StringBuilder();
        for (int i = 0; i < stringToEncode.length(); i++) {
            processNextOne(stringToEncode.charAt(i));
        }
        endProcessing();
        return encodedStringBuilder.toString();
    }

    private void processNextOne(Character currentCharacter){
        if(CharacterHelper.isNumber(currentCharacter)){
            writeDownLastProcessedCharacter();
            writeDown(currentCharacter);
            return;
        }
        if (isEscape(currentCharacter)){
            writeDownLastProcessedCharacter();
            writeDown(currentCharacter);
            return;
        }
        if(currentCharacter.equals(lastProcessedCharacter)){
            updateLastCharInSequence(currentCharacter);
        } else {
            if (lastProcessedCharacter == null){
                updateLastCharInSequence(currentCharacter);
            } else {
                writeDownLastProcessedCharacter();
                updateLastCharInSequence(currentCharacter);
            }
        }
    }

    private void endProcessing(){
        writeDownLastProcessedCharacter();
    }



    private static boolean isEscape(Character input){
        return escapeSymbol.equals(input);
    }

    private void writeDown(Character character){
        if(CharacterHelper.isNumber(character)) {
            encodedStringBuilder.append(escapeSymbol).append(character);
        } else {
            encodedStringBuilder.append(escapeSymbol.toString()).append(escapeSymbol);
        }
    }

    private void writeDownLastProcessedCharacter(){
        if(lastProcessedCharacter != null) {
            encodedStringBuilder.append(lastProcessedCharacterCounter).append(lastProcessedCharacter.toString());
            lastProcessedCharacterCounter = 0;
            lastProcessedCharacter = null;
        }
    }

    private void updateLastCharInSequence(Character currentCharacter){
        lastProcessedCharacter = currentCharacter;
        lastProcessedCharacterCounter++;
    }
}
