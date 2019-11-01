package se.lexicon.jonst;


import java.util.Scanner;

public class App {

    public static Scanner inputReader;

    public static final String[] SECRETWORDS = {"donkey", "cactus", "cowboy", "desert", "banana", "wrench", "exodus", "country"};

    public static String secretWord;
    public static char[] guessWord;

    public static StringBuilder guesses = new StringBuilder(8);     //Initiate a stringbuilder to keep guessed letters in
    public static boolean guessedRight;
    public static int wrongGuesses = 0;     //Tracks number of wrong guesses

    public static void main(String[] args) {

        inputReader = new Scanner(System.in);

        String guess;


        System.out.println("Let's play Hangman!\n\n\n");

        secretWord = getRandomSecret();      //Get a randomly selected word from SECRETWORDS
        guessWord = getBlankArray(secretWord.length());  //Get a blank array to store correct guesses in

        while (true) {

            showResult();

            guess = askUserFor("Guess a word or letter: ");     //Get an input from user
            testGuess(guess.toLowerCase());            //Test the guess

            answerFound();

            if (guessedRight) {
                System.out.print("That's right! The secret word is \"" + secretWord + "\"! You've won in only " + guesses.length() + " tries!");
                break;
            }

            if (wrongGuesses >= 8) {
                System.out.println("You're out of guesses! Better luck next time!");
                break;
            }

        }

        inputReader.close();
    }

    public static String getRandomSecret() {
        return SECRETWORDS[(int) Math.floor(Math.random() * SECRETWORDS.length)];
    }

    static String askUserFor(String term) {     //Multi-use method for getting strings
        System.out.print(term);
        return inputReader.nextLine();
    }

    public static char[] getBlankArray(int length) {
        char[] blankArray = new char[length];   //Make array required size

        for (int i = 0; i < blankArray.length; i++) {
            blankArray[i] = '_';
        }

        return blankArray;
    }

    public static void showResult() {
        System.out.print("The word is: ");
        for (char letter : guessWord) {
            System.out.print(letter);

        }
        System.out.println("\n");

        System.out.println("Already guessed letters: " + guesses.toString() + "\n\n");
    }

    public static void testGuess(String response) {

        if (response.length() == 1) {     //If it's just one letter

            if (guesses.toString().contains(response)) {
                System.out.println("You've already guessed that letter!");


            } else if (secretWord.contains(response)) {        //If the letter is in the word
                guesses.append(response);       //Add the guess to list of guessed letters
                System.out.println("Good guess!");

                for (int i = 0; i < secretWord.length(); i++) {         //Add the letter to relevant spots in the guessed word

                    if (secretWord.charAt(i) == response.charAt(0)) {      //Tricky comparing chars with strings
                        guessWord[i] = response.charAt(0);
                    }
                }


            } else {        //If you didn't guess right, and haven't guessed that yet
                guesses.append(response);       //Add the guess to list of guessed letters
                System.out.println("Wrong guess!");
                wrongGuesses++;

            }

        } else {           //If it's more than one, i.e. a word

            if (response.equals(secretWord)) {
                System.out.println("You guessed correctly!");
                guessedRight = true;

            } else {
                System.out.println("That's not the right word!");
                wrongGuesses++;
            }

        }

    }

    public static void answerFound() {
        boolean blanksFound = false;
        for (char letter : guessWord) {      //Check if we still have blank spaces
            if (letter == '_')
                blanksFound = true;
        }
        if (!blanksFound)       //If we don't have any blank spaces, the word has been found!
            guessedRight = true;
    }

}
