package com.pyro.skd;

import java.awt.Color;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class randomGlow {

	private int chunks;
	private int chances;
	private int currLabel;
	private int prevLabel;
	private int speed;
	private int current;
	private JLabel labelsBlur[];
	private boolean buttonClick = false;
	private boolean[] labelFlag = new boolean[imageLayers.chunks]; //already initialized to null (primitive type)
	
	public randomGlow() {
		// TODO Auto-generated constructor stub
	}
	
	void setter() {
		chunks = imageLayers.chunks;
		labelsBlur = imageLayers.labelsBlur;
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
	 	int randomNum = rand.nextInt((max - min) + 1) + min;
 		return randomNum;
	}
	
	void gameLoop() throws InterruptedException {
		for(int i = chances; i>=0; i--)	//no. of chances a player gets for guessing
		{
			while(buttonClick == false)
			{
				/* set previous label's border to null */ 
				current = randInt(0, chunks);
				Thread.sleep(speed);	//sleep is the milliseconds before skipping to next label
				Border border = BorderFactory.createLineBorder(Color.blue);
		        labelsBlur[current].setBorder(border);	//only include those numbers which are false in labelFlag
		        prevLabel = currLabel;
			}
		}
	}
}
