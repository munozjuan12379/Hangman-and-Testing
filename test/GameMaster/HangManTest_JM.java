package GameMaster;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class HangManTest_JM {
    @DisplayName("Test All Bad Words")
    @ParameterizedTest
    @CsvSource({
            "ugly",
            "stupid",
            "IDiot",
            "dumB",
            "lAzy",
            "GOOF",
    })
    void testHangMan_BadWords_ThrowsException(String secretWord){
        //Arrange
        int nWrongGuesses = 7;
        String expectedErrorMessage = "Illegal Secret Word";
        //Act
        //Assert
        Throwable IExcept = assertThrows(Throwable.class, () -> {
            new HangMan(secretWord, nWrongGuesses);
        },  "Bad Word Should Throw Exception");
        assertEquals(expectedErrorMessage, IExcept.getMessage(), "Unexpected Error Messages");
    }
    @DisplayName("Test Secret Words With Spaces Between")
    @ParameterizedTest
    @CsvSource({
            "Jo lly",
            "J o lly",
            "H o l e"
    })
    void testHangMan_WordsWithSpaces_ThrowsException(String secretWord){
        //Arrange
        int nWrongGuesses = 7;
        String expectedErrorMessage = "Illegal Secret Word";
        //Act
        //Assert
        Throwable IExcept = assertThrows(Throwable.class, () -> {
            new HangMan(secretWord, nWrongGuesses);
        },  "Bad Word Should Throw Exception");
        assertEquals(expectedErrorMessage, IExcept.getMessage(), "Unexpected Error Messages");
    }

    @DisplayName("Test Secret Word With Spaces Before")
    @Test
    void testHangMan_SecretWordSpacesBefore_ThrowsException(){
        //Arrange
        String secretWord = " Jolly";
        int nWrongGuesses = 7;
        String expectedErrorMessage = "Illegal Secret Word";
        //Act
        //Assert
        Throwable IExcept = assertThrows(Throwable.class, () -> {
            new HangMan(secretWord, nWrongGuesses);
        },  "Bad Word Should Throw Exception");
        assertEquals(expectedErrorMessage, IExcept.getMessage(), "Unexpected Error Message");
    }
    @DisplayName("Test Secret Word With Spaces After")
    @Test
    void testHangMan_SecretWordSpaces_ThrowsException(){
        //Arrange
        String secretWord = "Jolly ";
        int nWrongGuesses = 7;
        String expectedErrorMessage = "Illegal Secret Word";
        //Act
        //Assert
        Throwable IExcept = assertThrows(Throwable.class, () -> {
            new HangMan(secretWord, nWrongGuesses);
        },  "Bad Word Should Throw Exception");
        assertEquals(expectedErrorMessage, IExcept.getMessage(), "Unexpected Error Message");
    }
    @DisplayName("Test Secret Word Over 8 Characters")
    @Test
    void testHangMan_SecretWord9Characters_ThrowsException(){
        //Arrange
        String secretWord = "Abilities";
        int nWrongGuesses = 4;
        String expectedErrorMessage = "Secret Word Must Be Between 2 and 8 inclusive";
        //Act
        //Assert
        Throwable IExcept = assertThrows(Throwable.class, () -> {
            new HangMan(secretWord, nWrongGuesses);
        },  "Long Word Should Throw Exception");
        assertEquals(expectedErrorMessage, IExcept.getMessage(), "Unexpected Error Message");
    }
    @DisplayName("Test Secret Word Under 2 Characters")
    @Test
    void testHangMan_SecretWord1Character_ThrowsException(){
        //Arrange
        String secretWord = "A";
        int nWrongGuesses = 4;
        String expectedErrorMessage = "Secret Word Must Be Between 2 and 8 inclusive";
        //Act
        //Assert
        Throwable IExcept = assertThrows(Throwable.class, () -> {
            new HangMan(secretWord, nWrongGuesses);
        },  "Short Word Should Throw Exception");
        assertEquals(expectedErrorMessage, IExcept.getMessage(), "Unexpected Error Message");
    }
    @DisplayName("Test Num Wrong Guesses Over 7")
    @Test
    void testHangMan_8NWrongGuesses_ThrowsException(){
        //Arrange
        String secretWord = "Apple";
        int nWrongGuesses = 8;
        String expectedErrorMessage = "Wrong Guesses must be between 1 and 7 inclusive";
        //Act
        //Assert
        Throwable IExcept = assertThrows(Throwable.class, () -> {
            new HangMan(secretWord, nWrongGuesses);
        },  "8 nWrongGuesses Should Throw Exception");
        assertEquals(expectedErrorMessage, IExcept.getMessage(), "Unexpected Error Message");
    }
    @DisplayName("Test Num Wrong Guesses 0")
    @Test
    void testHangMan_0NWrongGuesses_ThrowsException(){
        //Arrange
        String secretWord = "Apple";
        int nWrongGuesses = 0;
        String expectedErrorMessage = "Wrong Guesses must be between 1 and 7 inclusive";
        //Act
        //Assert
        Throwable IExcept = assertThrows(Throwable.class, () -> {
            new HangMan(secretWord, nWrongGuesses);
        },  "0 nWrongGuesses Should Throw Exception");
        assertEquals(expectedErrorMessage, IExcept.getMessage(), "Unexpected Error Message");
    }
    @DisplayName("Test Num Wrong Guesses Negative")
    @Test
    void testHangMan_NegativeNWrongGuesses_ThrowsException(){
        //Arrange
        String secretWord = "Apple";
        int nWrongGuesses = -1;
        String expectedErrorMessage = "Wrong Guesses must be between 1 and 7 inclusive";
        //Act
        //Assert
        Throwable IExcept = assertThrows(Throwable.class, () -> {
            new HangMan(secretWord, nWrongGuesses);
        },  "-1 WrongGuesses Should Throw Exception");
        assertEquals(expectedErrorMessage, IExcept.getMessage(), "Unexpected Error Message");
    }
    @DisplayName("Test Character Guessed Already")
    @Test
    void testGotLetter_CharGuessedAlready_ThrowsException(){
        //Arrange
        String secretWord = "Apple";
        int nWrongGuesses = 3;
        String expectedErrorMessage = "Duplicate Guess";
        //Act
        //Assert
        Throwable IExcept = assertThrows(Throwable.class, () -> {
            HangMan hang = new HangMan(secretWord, nWrongGuesses);
            hang.gotLetter('a');
            hang.gotLetter('a');
        },  "Duplicate Guess Should Throw Exception");
        assertEquals(expectedErrorMessage, IExcept.getMessage(), "Unexpected Error Message");
    }
    @DisplayName("Test Guess While Game Won")
    @Test
    void testGotLetter_GuessDuringGameWon_ThrowsException(){
        //Arrange
        String secretWord = "Apple";
        int nWrongGuesses = 3;
        String expectedErrorMessage = "Game Is Over";
        //Act
        //Assert
        Throwable IExcept = assertThrows(Throwable.class, () -> {
            HangMan hang = new HangMan(secretWord, nWrongGuesses);
            hang.gotLetter('A');
            hang.gotLetter('p');
            hang.gotLetter('l');
            hang.gotLetter('e');
            hang.gotLetter('d');
        },  "Guess while game is won should throw exception");
        assertEquals(expectedErrorMessage, IExcept.getMessage(), "Unexpected Error Message");
    }
    @DisplayName("Test Guess While Game Lost")
    @Test
    void testGotLetter_GuessDuringGameLost_ThrowsException(){
        //Arrange
        String secretWord = "Apple";
        int nWrongGuesses = 3;
        String expectedErrorMessage = "Game Is Over";
        //Act
        //Assert
        Throwable IExcept = assertThrows(Throwable.class, () -> {
            HangMan hang = new HangMan(secretWord, nWrongGuesses);
            hang.gotLetter('z');
            hang.gotLetter('y');
            hang.gotLetter('x');
            hang.gotLetter('v');
        },  "Guess while game is lost should throw exception");
        assertEquals(expectedErrorMessage, IExcept.getMessage(), "Unexpected Error Message");
    }
    @DisplayName("Test For Win All Letters Guessed")
    @Test
    void testHasWon_AllLettersGuessed_ShouldBeTrue() throws HangMan.illegalConstructionException, HangMan.illegalGuessException, HangMan.illegalGameStateException {
        //Arrange
        String secretWord = "Apple";
        int nWrongGuesses = 3;
        HangMan hang = new HangMan(secretWord, nWrongGuesses);
        hang.gotLetter('A');
        hang.gotLetter('p');
        hang.gotLetter('l');
        hang.gotLetter('e');
        //Act
        boolean actual = hang.hasWon();
        //Assert
        assertTrue(actual);
    }
    @DisplayName("Test For Win Not All Letters Guessed")
    @Test
    void testHasWon_NotAllLettersGuessed_ShouldBeFalse() throws HangMan.illegalConstructionException, HangMan.illegalGuessException, HangMan.illegalGameStateException {
        //Arrange
        String secretWord = "Apple";
        int nWrongGuesses = 3;
        HangMan hang = new HangMan(secretWord, nWrongGuesses);
        hang.gotLetter('A');
        hang.gotLetter('p');
        hang.gotLetter('e');
        //Act
        boolean actual = hang.hasWon();
        //Assert
        assertFalse(actual);
    }
    @DisplayName("Test Hint While All Letters Guessed")
    @Test
    void testGiveHint_HintButAllLettersGuessed_ThrowsException(){
        //Arrange
        String secretWord = "Apple";
        int nWrongGuesses = 3;
        String expectedErrorMessage = "Game Is Over Cannot Hint";
        //Act
        //Assert
        Throwable IExcept = assertThrows(Throwable.class, () -> {
            HangMan hang = new HangMan(secretWord, nWrongGuesses);
            hang.gotLetter('A');
            hang.gotLetter('p');
            hang.gotLetter('l');
            hang.gotLetter('e');
            hang.giveHint();
        },  "Hint while game is won should throw exception");
        assertEquals(expectedErrorMessage, IExcept.getMessage(), "Unexpected Error Message");
    }
    @DisplayName("Test Random Hints")
    @RepeatedTest(10)
    void testGiveHint_OneCharacterGuessed_ShouldReturnEveryOtherCharRandomly() throws HangMan.illegalConstructionException, HangMan.illegalGuessException, HangMan.illegalGameStateException {
        //Arrange
        String secretWord = "Apple";
        int nWrongGuesses = 3;
        HangMan hang = new HangMan(secretWord, nWrongGuesses);
        hang.gotLetter('A');
        //Act
        char actual = hang.giveHint();
        boolean result = (actual == 'p' || actual =='l' || actual == 'e');
        //Assert
        assertTrue(result);
    }
    @DisplayName("Test Random Hints No Guesses")
    @RepeatedTest(10)
    void testGiveHint_NoCharacterGuessed_ShouldReturnEveryCharRandomly() throws HangMan.illegalConstructionException, HangMan.illegalGuessException, HangMan.illegalGameStateException {
        //Arrange
        String secretWord = "Apple";
        int nWrongGuesses = 3;
        HangMan hang = new HangMan(secretWord, nWrongGuesses);
        //Act
        char actual = hang.giveHint();
        boolean result = (actual == 'p' || actual =='l' || actual == 'e' || actual == 'A');
        //Assert
        assertTrue(result);
    }
    @DisplayName("Test For Loss Too Many Wrong Guesses")
    @Test
    void testHasLost_TooManyGuesses_ShouldBeTrue() throws HangMan.illegalConstructionException, HangMan.illegalGuessException, HangMan.illegalGameStateException {
        //Arrange
        String secretWord = "Apple";
        int nWrongGuesses = 3;
        HangMan hang = new HangMan(secretWord, nWrongGuesses);
        hang.gotLetter('g');
        hang.gotLetter('o');
        hang.gotLetter('t');
        //Act
        boolean actual = hang.hasLost();
        //Assert
        assertTrue(actual);
    }
    @DisplayName("Test For Loss But Game Already Won")
    @Test
    void testHasLost_GameWon_ShouldBeFalse() throws HangMan.illegalConstructionException, HangMan.illegalGuessException, HangMan.illegalGameStateException {
        //Arrange
        String secretWord = "Apple";
        int nWrongGuesses = 3;
        HangMan hang = new HangMan(secretWord, nWrongGuesses);
        hang.gotLetter('A');
        hang.gotLetter('p');
        hang.gotLetter('l');
        hang.gotLetter('e');
        //Act
        boolean actual = hang.hasLost();
        //Assert
        assertFalse(actual);
    }
    @DisplayName("Test For Loss While Game In Progress")
    @Test
    void testHasLost_GameInProgress_ShouldBeFalse() throws HangMan.illegalConstructionException, HangMan.illegalGuessException, HangMan.illegalGameStateException {
        //Arrange
        String secretWord = "Apple";
        int nWrongGuesses = 3;
        HangMan hang = new HangMan(secretWord, nWrongGuesses);
        hang.gotLetter('A');
        hang.gotLetter('l');
        hang.gotLetter('c');
        hang.gotLetter('g');
        //Act
        boolean actual = hang.hasLost();
        //Assert
        assertFalse(actual);
    }
    @DisplayName("Test Word Progress While Some Letters Guessed")
    @Test
    void testGetShowWord_SomeLettersGuessed_ShouldReturnStringWithBarsAndChars() throws HangMan.illegalConstructionException, HangMan.illegalGuessException, HangMan.illegalGameStateException {
        //Arrange
        String secretWord = "Apple";
        int nWrongGuesses = 3;
        HangMan hang = new HangMan(secretWord, nWrongGuesses);
        hang.gotLetter('A');
        hang.gotLetter('l');
        String expected = "A__l_";
        //Act
        String actual = hang.getShowWord();
        //Assert
        assertEquals(expected, actual);
    }
    @DisplayName("Test Word Progress When All Letters Guessed")
    @Test
    void testGetShowWord_AllLettersGuessed_ShouldReturnFullString() throws HangMan.illegalConstructionException, HangMan.illegalGuessException, HangMan.illegalGameStateException {
        //Arrange
        String secretWord = "Apple";
        int nWrongGuesses = 3;
        HangMan hang = new HangMan(secretWord, nWrongGuesses);
        hang.gotLetter('A');
        hang.gotLetter('l');
        hang.gotLetter('p');
        hang.gotLetter('e');
        String expected = "Apple";
        //Act
        String actual = hang.getShowWord();
        //Assert
        assertEquals(expected, actual);
    }
    @DisplayName("Test Word Progress When No Letters Guessed")
    @Test
    void testGetShowWord_NoLettersGuessed_ShouldReturnUnderscores() throws HangMan.illegalConstructionException{
        //Arrange
        String secretWord = "Apple";
        int nWrongGuesses = 3;
        HangMan hang = new HangMan(secretWord, nWrongGuesses);
        String expected = "_____";
        //Act
        String actual = hang.getShowWord();
        //Assert
        assertEquals(expected, actual);
    }
    @DisplayName("Test No Guesses")
    @Test
    void testGetActualWrong_NoGuesses_ShouldReturnZero() throws HangMan.illegalConstructionException{
        //Arrange
        String secretWord = "Apple";
        int nWrongGuesses = 3;
        HangMan hang = new HangMan(secretWord, nWrongGuesses);
        int expected = 0;
        //Act
        int actual = hang.getActualWrong();
        //Assert
        assertEquals(expected, actual);
    }
    @DisplayName("Test No Actual Wrong Guesses")
    @Test
    void testGetActualWrong_NoWrongGuesses_ShouldReturnZero() throws HangMan.illegalConstructionException, HangMan.illegalGuessException, HangMan.illegalGameStateException {
        //Arrange
        String secretWord = "Apple";
        int nWrongGuesses = 3;
        HangMan hang = new HangMan(secretWord, nWrongGuesses);
        hang.gotLetter('A');
        hang.gotLetter('p');
        int expected = 0;
        //Act
        int actual = hang.getActualWrong();
        //Assert
        assertEquals(expected, actual);
    }
    @DisplayName("Test One Wrong Guess")
    @Test
    void testGetActualWrong_OneWrongGuess_ShouldReturnOne() throws HangMan.illegalConstructionException, HangMan.illegalGuessException, HangMan.illegalGameStateException {
        //Arrange
        String secretWord = "Apple";
        int nWrongGuesses = 3;
        HangMan hang = new HangMan(secretWord, nWrongGuesses);
        hang.gotLetter('A');
        hang.gotLetter('p');
        hang.gotLetter('d');
        int expected = 1;
        //Act
        int actual = hang.getActualWrong();
        //Assert
        assertEquals(expected, actual);
    }
    @DisplayName("Test Max Wrong Guesses")
    @Test
    void testGetActualWrong_MaxWrongGuesses_ShouldReturn3() throws HangMan.illegalConstructionException, HangMan.illegalGuessException, HangMan.illegalGameStateException {
        //Arrange
        String secretWord = "Apple";
        int nWrongGuesses = 3;
        HangMan hang = new HangMan(secretWord, nWrongGuesses);
        hang.gotLetter('c');
        hang.gotLetter('g');
        hang.gotLetter('d');
        int expected = 3;
        //Act
        int actual = hang.getActualWrong();
        //Assert
        assertEquals(expected, actual);
    }
}
