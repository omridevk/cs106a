/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

    public void run() {
    	setup();
    	/*play();*/
    	
		/* You fill this in */
	}
  
   /* private void play() {
    	while (rightguesses < word.length()) { 
    		guess = getLetter();
    		checkGuess(word, guess);
    		println(wordstate);
    	}
    	
    }*/
    
    private void setup() {
    	result = "";
    	rightguesses = 0;
    	secretWord = lexicon.getWord(rgen.nextInt(0, 9));
    	println(secretWord);
    	wordstate = toDashes(secretWord);
    	println(wordstate);
    }
    
    
    public void checkGuess(String str, char ch) {
		int index = str.indexOf(ch);
		if (index != -1) {
			String partstring = "-" + removeAllOccurences(str, ch);
			String temp = wordstate.substring(0, index);
			wordstate = temp + ch + wordstate.substring(temp.length() + 1 );
			checkGuess(partstring, ch);			
		}
	}
    

    
    private String removeAllOccurences(String str, char ch) {

		while (str.indexOf(ch) != -1) {
			int index = str.indexOf(ch);
			if (index != -1){
				String s1 = str.substring(0, index);
				String s2  = index == str.length() ? "" : str.substring(index+1);
				str = s1 + s2;
			}
		}
		return str;
    }
    
    private char getLetter() {
    	String str = readLine("Your Guess is: ");
    	while (str.length() > 1 ){
    		str = readLine("Please enter only one letter: ");
    	}
    	char ch = str.charAt(0);
    	ch = Character.toUpperCase(ch);
    	return ch;

    }
    
    private String toDashes(String word) {
    	String result = "";
    	for (int i = 0; i < word.length(); i++) {
    		result += "-";
    	}
    	return result;
    }

    
    private String result;
    private int rightguesses;
    private String wordstate;
    private char guess;
    private String secretWord; 
    private RandomGenerator rgen = RandomGenerator.getInstance();
    private HangmanLexicon lexicon = new HangmanLexicon();
    
}
