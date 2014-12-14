/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int oldnumber = 0;
		int number = readInt("Enter a number: ");
		int counter = 0;
		while (true){
			counter ++;
			if (number%2 != 0){
				oldnumber = number;
				number = (number*3)+1;
				println(oldnumber +" is odd, so I make 3n+1 : " + number);
			} else {
				oldnumber = number;
				number = number/2;
				println(oldnumber + " is even, so I take half: " + number);
			}
			if (number == 1){
				println("The proccess took " + counter + " to reach 1");
				break;
			}
		}
	}
}

