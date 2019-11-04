package se.lexicon.jonst;


import java.util.Scanner;

public class App {

    public static Scanner inputReader;

    public static final String[] SECRETWORDS = {"donkey", "cactus", "cowboy", "desert", "banana", "wrench", "exodus", "country", "tournament", "programmer", "sandstorm", "demolition"};

    public static String secretWord;    //Stores the secret word
    public static char[] guessWord;     //Stores the guess-word. Filled up with underscores initially.

    public static StringBuilder guesses;     //Initiate a stringbuilder to keep guessed letters in

    public static boolean guessedRight;     //Used to flag when you've guessed right and lead into loop exit
    public static int wrongGuesses;     //Tracks number of wrong guesses
    public static int fullWordGuess;

    public static void main(String[] args) {

        inputReader = new Scanner(System.in);

        String guess;       //Stores a guess


        System.out.println("Let's play Hangman!\n\n\n");


        do {


            secretWord = getRandomSecret();      //Get a randomly selected word from SECRETWORDS
            guessWord = getBlankArray(secretWord.length());  //Get a blank array to store correct guesses in
            guessedRight = false;
            guesses = new StringBuilder(8);
            wrongGuesses = 0;
            fullWordGuess = 0;

            while (true) {

                showResult();

                guess = askUserFor("Guess a word or letter: ");     //Get an input from user
                testGuess(guess.toLowerCase());            //Test the guess

                answerFound();


                if (guessedRight) {
                    System.out.print("That's right! The secret word is \"" + secretWord + "\"! You've won with only " + guesses.length() + " letter guesses and " + fullWordGuess + " full word guesses!");
                    break;
                }

                if (wrongGuesses >= 8) {
                    System.out.println("You're out of guesses! Better luck next time!");
                    break;
                }

            }

        } while (askUserFor("\n\nDo you want to play again? (y/n) [n] ").toLowerCase().equals("y") ? true : false); //Ask user, then use response to decide ternary operator

        System.out.println("\nThanks for playing!");

        inputReader.close();
    }









    public static String getRandomSecret() {        //Picks a word from the SECRETWORDS array
        return SECRETWORDS[(int) Math.floor(Math.random() * SECRETWORDS.length)];
    }

    static String askUserFor(String term) {     //Multi-use method for getting strings
        System.out.print(term);
        return inputReader.nextLine();
    }

    public static char[] getBlankArray(int length) {        //Gets a char array to store our found word in
        char[] blankArray = new char[length];   //Make array required size

        for (int i = 0; i < blankArray.length; i++) {
            blankArray[i] = '_';
        }

        return blankArray;
    }

    public static void showResult() {       //Shows what we've revealed of the guessed word so far
        System.out.print("The word is: ");
        for (char letter : guessWord) {
            System.out.print(letter + " ");
        }
        System.out.println("\n");

        System.out.println("Already guessed letters: " + guesses.toString());
        System.out.println("Wrong guesses so far: " + wrongGuesses + "\n\n");
    }

    public static void testGuess(String response) {     //Tests whether the guess is right or wrong

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

            fullWordGuess++;

            if (response.equals(secretWord)) {
                System.out.println("You guessed correctly!");
                guessedRight = true;

            } else {
                System.out.println("That's not the right word!");
                wrongGuesses++;
            }

        }

        for (String[] row : stickman(wrongGuesses)   //Write the stickman
        ) {
            for (String part : row
            ) {
                System.out.print(part);
            }
            System.out.print("\n");
        }

        System.out.println("\n");


    }

    public static void answerFound() {  //Checks if the answer has been found in full
        boolean blanksFound = false;
        for (char letter : guessWord) {      //Check if we still have blank spaces
            if (letter == '_')
                blanksFound = true;
        }
        if (!blanksFound)       //If we don't have any blank spaces, the word has been found!
            guessedRight = true;
    }


    public static String[][] stickman(int parts) {

        switch (parts) {    //Breaks not needed because return
            case 1:
                return new String[][]{
                        {"(", " ", " "},
                        {" ", " ", " "},
                        {" ", " ", " "},
                        {" ", " ", " "}
                };

            case 2:
                return new String[][]{
                        {"(", " ", ")"},
                        {" ", " ", " "},
                        {" ", " ", " "},
                        {" ", " ", " "}
                };

            case 3:
                return new String[][]{
                        {"(", " ", ")"},
                        {" ", "|", " "},
                        {" ", " ", " "},
                        {" ", " ", " "}
                };

            case 4:
                return new String[][]{
                        {"(", " ", ")"},
                        {" ", "|", " "},
                        {" ", "|", " "},
                        {" ", " ", " "}
                };

            case 5:
                return new String[][]{
                        {"(", " ", ")"},
                        {"\\", "|", " "},
                        {" ", "|", " "},
                        {" ", " ", " "}
                };

            case 6:
                return new String[][]{
                        {"(", " ", ")"},
                        {"\\", "|", "/"},
                        {" ", "|", " "},
                        {" ", " ", " "}
                };

            case 7:
                return new String[][]{
                        {"(", " ", ")"},
                        {"\\", "|", "/"},
                        {" ", "|", " "},
                        {"/", " ", " "}
                };

            case 8:
                return new String[][]{
                        {"(", " ", ")"},
                        {"\\", "|", "/"},
                        {" ", "|", " "},
                        {"/", " ", "\\"}
                };

            default:
                return new String[][]{
                        {" ", " ", " "},
                        {" ", " ", " "},
                        {" ", " ", " "},
                        {" ", " ", " "}
                };


        }

    }

}
