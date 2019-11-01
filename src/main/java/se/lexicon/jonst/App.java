package se.lexicon.jonst;


public class App {

    public static final String[] SECRETWORDS = {"donkey", "cactus", "cowboy", "desert", "banana", "wrench", "exodus", "country"};


    public static void main(String[] args) {

        System.out.println("Let's play Hangman!\n\n\n");

        String secretWord = getRandomSecret();      //Get a randomly selected word from SECRETWORDS
        char[] guessWord = getBlankArray(secretWord.length());  //Get a blank array to store correct guesses in


        StringBuilder guesses = new StringBuilder(8);    //Initiate a stringbuilder to keep guessed letters in

        while (true) {
        
        }


    }

    public static String getRandomSecret() {
        return SECRETWORDS[(int) Math.floor(Math.random() * SECRETWORDS.length)];
    }

    public static char[] getBlankArray(int length) {
        char[] blankArray = new char[length];   //Make array required size

        for (char letter : blankArray)    //Set each letter as underscore
            letter = '_';

        return blankArray;
    }

}
