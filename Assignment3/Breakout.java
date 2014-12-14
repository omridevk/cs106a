/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

import com.sun.java.swing.plaf.windows.resources.windows;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels.  On some platforms 
  * these may NOT actually be the dimensions of the graphics canvas. */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Animation delay or pause time between ball moves */
	private static final int DELAY = 30;
	
/** Dimensions of game board.  On some platforms these may NOT actually
  * be the dimensions of the graphics canvas. */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 60;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;
	
/** Maximum speed for the ball */
	private static final int MAX_SPEED = 10;
	
/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 5;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/** Number of hits need to start increasing the speed of the ball */
	private static final int NHITS_SPEED_INCREASE = 3;
	
/** Paddle Y starting position */
	private static final double PADDLE_Y_POSITION = HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
	
/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		setup();
		
		while (turns > 0) {
			remove(startscreen);
			play();
		}
		gameOver();
	}
	
	/* Setup function, running the init methods to set the game */
	public void setup(){
		startingTitle();
		create_rows();
		create_paddle();
		addMouseListeners();
		waitForClick();
	}
	
	/* Play function, responsible for running starting the game */
	public void play(){
		createBall();
		vx = rgen.nextDouble(1.0, 3.0);
		vy = 6.0;
		if (rgen.nextBoolean(0.5)) vx = -vx;
		while (!checkforStrike()){
			moveBall();
			checkforWallCollision();
			GObject object = getCollidingObject();
			brickCollisionHandler(object);
			pause(DELAY);
		}
	}
	
	/* Opening title and life indicator */
	private void startingTitle() {
		startscreen = new GLabel("Click here to start playing");
		lifes = new GLabel(""+turns);
		lifes.setFont("SansSerif-28");
		lifes.setColor(Color.RED);
		startscreen.setFont("SansSerif-28");
		double x = (getWidth() - startscreen.getWidth()) / 2;
		double y = (getHeight() + startscreen.getAscent()) / 2;
		lifes.setLocation(x,20);
		startscreen.setLocation(x,y);
		add(lifes);
		add(startscreen);
	}
	
	/* Creating the bricks, row by row then setting the color based on row number */
	private void create_rows(){
		double x =(getWidth() - ((BRICK_WIDTH + BRICK_SEP) * NBRICKS_PER_ROW) + BRICK_SEP)/2;
		double y = BRICK_Y_OFFSET;
		for (int i =0; i < NBRICK_ROWS; i++){
			for (int j = 0; j < NBRICKS_PER_ROW; j++){
				GRect bricks = new GRect (x + j*(BRICK_WIDTH + BRICK_SEP),y + i*(BRICK_HEIGHT + BRICK_SEP), BRICK_WIDTH, BRICK_HEIGHT);
				bricks.setFilled(true);
				if (i < 2){
					bricks.setFillColor(Color.CYAN);
					bricks.setColor(Color.CYAN);
				} else if ( i > 1 && i < 4 ) {
					bricks.setFillColor(Color.GREEN);
					bricks.setColor(Color.GREEN);
				} else if ( i > 3 && i < 6){
					bricks.setFillColor(Color.YELLOW);
					bricks.setColor(Color.YELLOW);
				} else if ( i > 5 && i < 8){
					bricks.setFillColor(Color.ORANGE);
					bricks.setColor(Color.ORANGE);
				} else {
					bricks.setFillColor(Color.RED);
					bricks.setColor(Color.RED);
				}
				add(bricks);
			}
		}
	}
	/* Creating the paddle */
	private void create_paddle(){
		double x = mouselocation;
		paddle = new GRect (x, PADDLE_Y_POSITION, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
	}
	
	/* creating the mouse event and moves the paddle in the x direction */
	public void mouseMoved(MouseEvent e){
		mouselocation = e.getX();
		if (mouselocation > WIDTH-PADDLE_WIDTH){
			mouselocation = WIDTH - PADDLE_WIDTH; // ensuring that the paddle does not go off the screen.
		}
		paddle.setLocation(mouselocation, PADDLE_Y_POSITION);
		
	}
	

	
	private void createBall() {
		int x = WIDTH/2 - BALL_RADIUS; // set the x and y location of the ball to be middle of the screen
		int y = HEIGHT/2 + BALL_RADIUS;
		ball = new GOval(x,y,2*BALL_RADIUS,2*BALL_RADIUS);
		ball.setFilled(true);
		add(ball);
	}
	
	private void moveBall(){
		ball.move(vx,vy);
	}
	
	private void checkforWallCollision() {
		if (ball.getY() > HEIGHT - 2*BALL_RADIUS || ball.getY() < 0){
			vy = -vy;
		}
		if (ball.getX() > WIDTH - 2*BALL_RADIUS || ball.getX() < 0){
			vx = -vx;
		}
	}
	
	private void brickCollisionHandler(GObject object){
		if (object != null){
			if (object == paddle){
				vy = -vy; //changes y velocity when hitting the paddle
			} else{
				hittingtimes++;
				if (hittingtimes%NHITS_SPEED_INCREASE == 0 && vy > 0) {
					if (vy > MAX_SPEED){
						vy = MAX_SPEED;
					}else {
						vy ++; //changing the speed of the ball after number of hits
					}
				} if (hittingtimes%NHITS_SPEED_INCREASE == 0 && vy < 0){
					if (Math.abs(vy) > MAX_SPEED){
						vy = -MAX_SPEED;
					}else {
						vy --; //changing the speed of the ball after number of hits
					}
				}
				vy = -vy; //changes y velocity when hitting a brick and then remove it
				remove(object);

			}
		}
	}
	
	private GObject getCollidingObject(){
		for (int i = 0; i < 2; i++){
			for (int j = 0; j < 2; j++){
				point = new GPoint(ball.getX() + i*(2*BALL_RADIUS), ball.getY() + j*(2*BALL_RADIUS));
				collider = getElementAt(point);
				if (collider != null) return collider;
			}
			
		}
		return collider;
	}
	

	
	private boolean checkforStrike(){
		if (ball.getY() > PADDLE_Y_POSITION) {
			remove(ball);
			turns--;
			lifes.setLabel("" +turns + " " + getElementCount());
			return(true);
		}
		return(false);
	}
	
	private void gameOver(){
		GLabel endscreen = new GLabel("GAME OVER!");
		endscreen.setFont("SansSerif-28");
		endscreen.setColor(Color.RED);
		double x = (getWidth() - endscreen.getWidth()) / 2;
		double y = (getHeight() + endscreen.getAscent()) / 2;
		endscreen.setLocation(x,y);
		add(endscreen);
	}
	

	
	/* Private instance variables */
	private int hittingtimes;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private double vx, vy;
	private int mouselocation;
	private int turns = NTURNS;
	private GRect paddle;
	private GObject collider;
	private GPoint point;
	private GOval ball;
	private GLabel startscreen;
	private GLabel lifes;
}
