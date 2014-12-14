/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;

	public void run() {
		int x = (getWidth() - (BRICK_WIDTH * BRICKS_IN_BASE))/2;
		int y = getHeight() - BRICK_HEIGHT;
		int bricksNumber = BRICKS_IN_BASE;
		for (int i = 0; i < BRICKS_IN_BASE; i++){
			for (int j = 0; j < bricksNumber; j++){
				GRect brick = new GRect (x + (j*BRICK_WIDTH), y - (i*BRICK_HEIGHT), BRICK_WIDTH, BRICK_HEIGHT);
				add(brick);
			}
			x += (BRICK_WIDTH)/2;
			bricksNumber--;
		}
	}

}
/*

width of screen - (number of bricks * brick width)*/