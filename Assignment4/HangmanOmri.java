
import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class HangmanOmri extends ConsoleProgram {
	
	public static final int NGUESSES = 8;
	
	public void main() {
		canvas.reset();
		setup();
		play(); 
		endGame();
	}
	
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
	}
	
	public void run() {
		canvas.reset();
		setup();
		play(); 
		endGame();
	}
	
	public void setup() {
		guesses = 0;
		int randomWordIndex = rgen.nextInt( 0 , lexicon.getWordCount() - 1 );
		secretWord = lexicon.getWord(randomWordIndex);
		println(secretWord);
		wordstate = toDashes(secretWord);
		println("Welcome to Hangman!");
		turn();	
	}
	
	private void endGame() {
		if (lose ==true) {
			println("You're completely hung.");
			println("The word was: " + secretWord);
			println("You lose.");
		} else {
			println("You guessed the word: " + secretWord);
			println("You win.");
		}
		String str = readLine("Write Yes to start a new game(you should) \n");
		str = str.toLowerCase();
		if (str.equals("yes")){
			run();
		} else {
			readLine("Are you sure?");
			println("Thank you for playing!");
		}
	}
	
	private void play() {
		while (true) {
			char guess = getLetter();
			if (wordstate.indexOf(guess)!= -1 || !checkGuess(secretWord, guess)) {
				guesses++;
				canvas.noteIncorrectGuess(guess);
				println("There are no " + guess + "\'s in the word");
			} else {
				canvas.displayWord(wordstate);
				println("That guess is correct");
			}
			turn();
			if (guesses >= NGUESSES || checkforWordComplete()) {
				lose = guesses >= NGUESSES ? true : false; 
				//check if you reached the maximum guesses and lose to true if so.
				break;
			}
		}
	}
	
	private void turn() {
		println("Your word now looks like this: " + wordstate);
		
		println("You have " + (NGUESSES - guesses) + " guesses left");
	}
	
	private boolean checkforWordComplete() {
		boolean result = (wordstate.equals(secretWord)) ? true : false;
		return result;
	}
	
	/* recursive function, checking for guess and creating the word state */
	/* 1. removing the guessed character from the string and adding dash to it's beginning 
	 * 2. setting temp string to be state word until the index of the guessed character
	 * 3. setting the wordstate to be the temp string plus the guessed character plus the length of temp + 1
	 *	  because if I am) splitting the word into two, the second part don't need to contain the last character of the first part
	 * 4. calling this function with the part of the string - this is done to check if the guess letter appears
	 *    more than once.
	 *    
	 *                                                    */
	private boolean checkGuess(String str, char ch) {
		int index = str.indexOf(ch);
		if (index != -1) {
			String partstring = "-" + removeOccurence(str, ch);
			String temp = wordstate.substring(0, index);
			wordstate = temp + ch + wordstate.substring(temp.length() + 1 );
			checkGuess(partstring, ch);
			return true;
		}else {
			return false;
		}
	}
	
	
	
	/* convert the secret word to dashes */
    private String toDashes(String word) {
    	String result = "";
    	for (int i = 0; i < word.length(); i++) {
    		result += "-";
    	}
    	return result;
    }
    
    
    private char getLetter() {
    	String str = readLine("Your Guess: ");
    	while (str.length() > 1 || str.equals("")){
    		str = readLine("Please enter only one letter and no empty lines: ");
    	}
    	char ch = str.charAt(0);
    	ch = Character.toUpperCase(ch);
    	return ch;
    }
    
    
	public String removeOccurence(String str, char ch) {
		int index = str.indexOf(ch);
		if (index != -1){
			String s1 = str.substring(0, index);
			String s2  = index == str.length() ? "" : str.substring(index+1);
			str = s1 + s2;
		}
		return str;
	}
	
	private HangmanCanvas canvas;
	private boolean lose;
	private int guesses;
	private String wordstate;
	private String secretWord;
	private RandomGenerator rgen = RandomGenerator.getInstance();
    private HangmanLexicon lexicon = new HangmanLexicon();
}
