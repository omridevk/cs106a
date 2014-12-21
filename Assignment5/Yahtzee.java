/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void init() {
		dice = new int[N_DICE];
	}
		
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		for (int i=0; i < N_SCORING_CATEGORIES; i++) {
			turn();
		}
	}
	

	
	private void turn() {
		for (int player=1; player < nPlayers +1; player++) {
			turn = 0;
			display.waitForPlayerToClickRoll(player);
			rollDice();
			while (turn < 2) {
				display.waitForPlayerToSelectDice();
				rollDiceCheat();
			}
			int category = display.waitForPlayerToSelectCategory();
			if (YahtzeeMagicStub.checkCategory(dice,category)) {
				int score =  checkScore(dice,category);
				totalscore = new int [nPlayers][2];
				totalscore[player-1][1] += score;
				display.updateScorecard(category, player, score);
				display.updateScorecard(TOTAL, player, totalscore[player-1][1]);
			}
		}
	}
	
	private int checkScore(int[] dice, int category) {
		int score = 0;
		println(category);
		if (category > 0 && category < 7) {
			for (int i = 0; i < dice.length; i++) {
				if (dice[i] == category) {
					score += dice[i];
				}
			}
			println("categories one to seven");
			println(score);
			return score;
		}
		else if (category == 9) {
			int counter = 0;
			println("Three of a kind");
			for (int i =0; i < dice.length; i++) {
				for (int j = i+1; j < dice.length; j++) {
					if (dice[i] == dice[j] && counter < 3) {
						score = dice[i]*3;
						counter++;
					}
				}
			}
			println(score);
			return score;
		} else {
			return score;
		}
	}
	
	
	

	private void rollDice() {
		println(N_DICE);
		for (int i = 0; i < dice.length ; i++) {
			if (turn != 0) { 
				if (display.isDieSelected(i)) {
					dice[i] = rgen.nextInt(1, 6);
				}
				else {
					println(dice[i]);
					dice[i] = dice[i];
				}
			}else {
				dice[i] = rgen.nextInt(1,6);
			}
		}
		turn++;
		display.displayDice(dice);
	}
		
	
	private void rollDiceCheat() {
		for (int i=0; i < dice.length; i++) {
			dice[i]=readInt("dice roll");
		}
		display.displayDice(dice);
		turn++;
	}
	
	
	
/* Private instance variables */
	private int[][] totalscore;
	private int turn;
	private int[] dice;
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}
