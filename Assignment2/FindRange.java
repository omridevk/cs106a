/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	private static final int SENTINEL = 0;
	public void run() {
		int largest = 0;
		int smallest = 0;
		int counter = 0;
		while(true){
			int number = readInt("? ");
			if (number == SENTINEL && counter == 1){
				int same = largest + smallest;
				println("smallest: " + same);
				println("largest: " + same);
				break;
			}else if (number == SENTINEL && counter == 0){
				println("no value to display");
				break;
			} else if (number == SENTINEL && counter > 1){
				println("smallest: " + smallest);
				println("largest: " + largest);
				break;
			}
			if (number > largest ){
				largest = number;
				
			} else if (number < smallest){
				smallest = number;
			}
			counter++;
		}
	}
}

