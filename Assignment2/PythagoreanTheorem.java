/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		double a = readDouble("one side: ");
		double b = readDouble("other side: ");
		double c = Math.sqrt((a*a) + (b*b));
		println(c);
	}
}
