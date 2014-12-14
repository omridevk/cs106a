/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;
import acm.util.ErrorException;
import java.awt.event.*;


public class HangmanCanvas extends GCanvas {
	

/** Resets the display so that only the scaffold appears */
	public void reset() {
		getMouseListeners();
		removeAll();
		guessedwordsString = "";
		label = new GLabel("", 10, getHeight() - 50);
		label.setFont("SansSerif-40");
		double y = label.getY() + label.getDescent(); 
		guessedwords = new GLabel("", 10, y);
		add(label);
		add(guessedwords);
		drawScaffold();
		guesses = 0;
		/* You fill this in */
	}
	
	private void drawScaffold() {
		double x = getWidth()/2 - BEAM_LENGTH;
		double y = getHeight()/2 - 200;
		GLine scaffold = new GLine(x, y,x,SCAFFOLD_HEIGHT);
		add(scaffold);
		GLine beam = new GLine(x, scaffold.getY(), x + BEAM_LENGTH,scaffold.getY());
		add(beam);
		rope = new GLine(getWidth()/2 , beam.getY(),getWidth()/2,beam.getY()+ ROPE_LENGTH);
		add(rope);
	}
	


/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		/* You fill this in */
		
		label.setLabel(word);
	}

	
/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		guessedwordsString += letter;
		guessedwords.setLabel(guessedwordsString);
		/* You fill this in */
		guesses++;
		switch (guesses) {
			case 1: 
				drawHead();
				
				break;
			case 2: 
				drawBody();
				
				break;
			case 3: 
				drawLeftArm();
				break;
			case 4:
				drawRightArm();
				break;
			case 5: 
				drawLeftLeg();
				break;
			case 6: 
				drawRightLeg();
				break;
			case 7: 
				drawLeftFoot();
				break;
			case 8: 
				drawRightFoot();
				break;
				
		default: throw new ErrorException("getWord: Illegal index");
	}
		
	}
	
	private void drawHead() {
		double x = getWidth() / 2 - HEAD_RADIUS;
		double y = rope.getY() + ROPE_LENGTH;
		head = new GOval (x,y, HEAD_RADIUS*2, HEAD_RADIUS*2);
		add(head);
	}
	
	private void drawBody() {
		double x = head.getX() + HEAD_RADIUS;
		double y = head.getY() + 2*HEAD_RADIUS;
		body = new GLine(x,y, x, y + BODY_LENGTH);
		add(body);
	}
	
	private void drawLeftArm() {
		double x = head.getX() + HEAD_RADIUS;
		double y = body.getY() + ARM_OFFSET_FROM_HEAD;
		leftarm = new GLine (x,y, x - UPPER_ARM_LENGTH, y);
		add(leftarm);
		x = leftarm.getX() - UPPER_ARM_LENGTH;
		y = leftarm.getY();
		lefthand = new GLine (x, y, x, y + LOWER_ARM_LENGTH);
		add(lefthand);
	}
	
	private void drawRightArm() {
		double x = head.getX() + HEAD_RADIUS;
		double y = body.getY() + ARM_OFFSET_FROM_HEAD;
		rightarm = new GLine (x,y, x + UPPER_ARM_LENGTH, y);
		add(rightarm);
		x = rightarm.getX() + UPPER_ARM_LENGTH;
		y = rightarm.getY();
		righthand = new GLine (x, y, x, y + LOWER_ARM_LENGTH);
		add(righthand);
	}
	
	private void drawLeftLeg() {
		double x = getWidth()/2 - HIP_WIDTH;
		double y = body.getY() + BODY_LENGTH;
		leg = new GLine(x,y, x + HIP_WIDTH, y);
		add(leg);
		leg = new GLine(x,y, x, y + LEG_LENGTH);
		add(leg);
	}
	
	private void drawRightLeg() {
		double x = getWidth()/2 + HIP_WIDTH;
		double y = body.getY() + BODY_LENGTH;
		leg = new GLine(x,y, x - HIP_WIDTH, y);
		add(leg);
		leg = new GLine(x,y, x, y + LEG_LENGTH);
		add(leg);
	}
	
	private void drawLeftFoot() {
		double x = leg.getX() -  (2*(HIP_WIDTH) + FOOT_LENGTH);
		double y = leg.getY() + LEG_LENGTH;
		foot = new GLine(x,y, x + FOOT_LENGTH, y);
		add(foot);
	}
	
	private void drawRightFoot() {
		double x = leg.getX();
		double y = foot.getY();
		foot = new GLine(x,y, x + FOOT_LENGTH, y);
		add(foot);
	}

/* Constants for the simple version of the picture (in pixels) */
	private GLabel label;
	private int guesses = 0;
	private String guessedwordsString;
	private GLine rope;
	private GLabel guessedwords;
	private GOval head;
	private GLine body;
	private GLine leftarm;
	private GLine lefthand;
	private GLine rightarm;
	private GLine righthand;
	private GLine leg;
	private GLine foot;

	
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Animation delay or pause time between ball moves */
	
/** Dimensions of game board.  On some platforms these may NOT actually
  * be the dimensions of the graphics canvas. */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;
	
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
