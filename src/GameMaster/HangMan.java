package GameMaster;

import java.util.*;

public class HangMan{
    int actualWrong;
    String secretWord;
    int nWrongGuesses;
    ArrayList<Character> lettersGuessed;

    public HangMan(String secretWord, int nWrongGuesses) throws illegalConstructionException{
        if (!validWord(secretWord)){
            throw new illegalConstructionException("Illegal Secret Word");
        }
        if (!validWordLength(secretWord)){
            throw new illegalConstructionException("Secret Word Must Be Between 2 and 8 inclusive");
        }
        if (!validNWrongGuesses(nWrongGuesses)){
            throw new illegalConstructionException("Wrong Guesses must be between 1 and 7 inclusive");
        }
        this.actualWrong = 0;
        this.secretWord = secretWord;
        this.nWrongGuesses = nWrongGuesses;
        this.lettersGuessed = new ArrayList<>();
    }

    boolean gotLetter(char guess) throws illegalGuessException, illegalGameStateException{
        boolean correctGuess = false;
        if (lettersGuessed.contains(guess)){
            throw new illegalGuessException ("Duplicate Guess");
        }
        if (isGameLost() || isGameWon()){
            throw new illegalGameStateException("Game Is Over");
        }
        for (int i=0; i<secretWord.length(); i++){
            if (guess == secretWord.charAt(i)){
                correctGuess = true;
            }
        }
        if (!correctGuess){
            actualWrong++;
        }
        lettersGuessed.add(guess);
        return correctGuess;
    }

    boolean hasWon(){
        if (isGameWon()){
            return true;
        }
        return false;
    }

    char giveHint() throws illegalGameStateException{
        if (isGameWon()){
            throw new illegalGameStateException("Game Is Over Cannot Hint");
        }
        ArrayList<Character> notGuessed = new ArrayList<>();
        for (int i=0; i<secretWord.length(); i++){
            if (!lettersGuessed.contains(secretWord.charAt(i))){
               notGuessed.add(secretWord.charAt(i));
            }
        }
        int index = (int)(Math.random() * notGuessed.size());
        return notGuessed.get(index);
    }

    boolean hasLost(){
        if (isGameLost()){
            return true;
        }
        return false;
    }

    public String getShowWord(){
        String showWord = "";
        char blankSpace = '_';
        for (int i=0; i<secretWord.length(); i++){
            if (lettersGuessed.contains(secretWord.charAt(i))){
                showWord = showWord + secretWord.charAt(i);
            }else {
                showWord = showWord + blankSpace;
            }
        }
        return showWord;
    }

    public int getActualWrong(){
        return actualWrong;
    }

    boolean validWord(String secretWord){
        String lowerSecretWord = secretWord.toLowerCase();
        String[] badWords = new String[]{"ugly", "stupid", "idiot", "dumb", "lazy", "goof"};
        for (int i=0; i< badWords.length; i++){
            if (lowerSecretWord.equals(badWords[i])){
                return false;
            }
        }
        for (int i = 0; i<secretWord.length(); i++) {
            if (Character.isDigit(lowerSecretWord.charAt(i)) || Character.isWhitespace(lowerSecretWord.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    boolean validWordLength(String secretWord){
        final int maxWrdLength = 8;
        final int minWrdLength = 2;
        if (secretWord.length() > maxWrdLength || secretWord.length() < minWrdLength){
            return false;
        }
        return true;
    }
    boolean validNWrongGuesses(int nWrongGuesses){
        final int maxWrongGuesses = 7;
        final int minWrongGuesses = 1;
        if (nWrongGuesses > maxWrongGuesses || nWrongGuesses < minWrongGuesses){
            return false;
        }
        return true;
    }
    boolean isGameWon(){
        int correctLetters = 0;
        for (int i=0; i<secretWord.length(); i++){
            if (lettersGuessed.contains(secretWord.charAt(i))){
                correctLetters++;
            }
        }
        if (correctLetters == secretWord.length()){
            return true;
        }
        return false;
    }
    boolean isGameLost(){
        if (actualWrong >= nWrongGuesses && !isGameWon()){
            return true;
        }
        return false;
    }

    public class illegalConstructionException extends Throwable{
        public illegalConstructionException(String message){
            super(message);
        }
    }

    public class illegalGuessException extends Throwable {
        public illegalGuessException(String message) {
            super(message);
        }
    }

    public class illegalGameStateException extends Throwable {
        public illegalGameStateException(String message) {
            super (message);
        }
    }
}