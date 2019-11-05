package se.lexicon.jonst;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testGetBlankArray() {
        //Arrange
        int length = 5;
        char[] expected = {'_', '_', '_', '_', '_'};

        //Act
        char[] result = App.getBlankArray(length);

        //Assert

        //assertEquals(Arrays.toString(expected), Arrays.toString(result) );
        assertArrayEquals(expected, result);
    }


    @Test
    public void testTestGuess() {

        //Arrange
        String response = "o";
        StringBuilder guesses = new StringBuilder("eta");
        String secretWord = "donkey";
        char[] guessWord = {'_', '_', '_', '_', '_', '_'};
        String expected = "rightchar";

        //Act
        String result = App.testGuess(response, guesses, secretWord, guessWord);

        //Assert
        assertEquals(expected, result);

        //----------------------------

        //Arrange
        response = "a";
        expected = "doublechar";

        //Act
        result = App.testGuess(response, guesses, secretWord, guessWord);

        //Assert
        assertEquals(expected, result);

        //----------------------------

        //Arrange
        response = "x";
        expected = "wrongchar";

        //Act
        result = App.testGuess(response, guesses, secretWord, guessWord);

        //Assert
        assertEquals(expected, result);

        //----------------------------

        //Arrange
        response = "horse";
        expected = "wrongword";

        //Act
        result = App.testGuess(response, guesses, secretWord, guessWord);

        //Assert
        assertEquals(expected, result);

        //----------------------------

        //Arrange
        response = "donkey";
        expected = "rightword";

        //Act
        result = App.testGuess(response, guesses, secretWord, guessWord);

        //Assert
        assertEquals(expected, result);
    }


    @Test
    public void testAnswerFound() {

        //Arrange
        char[] guessWord = {'d', 'o', 'n', 'k', 'e', 'y'};
        boolean expected = true;

        //Act
        boolean result = App.answerFound(guessWord);

        //Assert
        assertEquals(expected, result);

        //---------------------

        //Arrange
        guessWord = new char[] {'_', 'o', '_', 'k', 'e', 'y'};
        expected = false;

        //Act
        result = App.answerFound(guessWord);

        //Assert
        assertEquals(expected, result);

        //----------------------

        //Arrange
        guessWord = new char[] {'_', '_', '_', '_', '_', '_'};
        expected = false;

        //Act
        result = App.answerFound(guessWord);

        //Assert
        assertEquals(expected, result);
    }
}
