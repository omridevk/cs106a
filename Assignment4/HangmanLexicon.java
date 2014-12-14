/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */
import acm.util.*;
import java.io.BufferedReader;
import acm.program.*;

import java.io.*;
import java.util.ArrayList;

public class HangmanLexicon extends ConsoleProgram{
	
	// This is the HangmanLexicon constructor
	public HangmanLexicon() {
		createList();
	}
	

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return wordList.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		String word = wordList.get(index);
		return word;
	}
	
	private void createList() {
		wordList = new ArrayList<String>();
		try {
    		
    		BufferedReader rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
    		while (true) {
    			String line = rd.readLine();
    			wordList.add(line);
    			if (line == null) break;
    		}
    		rd.close();
    		
    	} catch (IOException ex) {
    		throw new ErrorException(ex);
    	}
		
	}
	
	
	private ArrayList<String> wordList;

	
}
