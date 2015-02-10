package com.pyro.skd;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.Border;

public class randomGlow implements ActionListener {

	private int chunks;
	private int chances = 3;
	private int currLabel = randInt(0, chunks - 1);
	private int prevLabel = randInt(0, chunks - 1);
	private int speed;
	private JLabel labelsBlur[];
	static JLayeredPane lpane;
	private JButton funcButton;
	private Border border;
	private boolean[] labelFlag = new boolean[imageLayers.chunks]; //already initialized to null (primitive type)
	
	void getterSetter() {
		chunks = imageLayers.chunks;
		labelsBlur = imageLayers.labelsBlur;
		funcButton = imageLayers.funcButton;
		lpane = imageLayers.lpane;
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
	 	int randomNum = rand.nextInt((max - min) + 1) + min;
 		return randomNum;
	}
	
	void gameLoop() throws InterruptedException {
		if(chances == 0)
		{
			funcButton.setText("No more guesses!");
			funcButton.setEnabled(false);
		}
		while (chances!=0)
		{
			funcButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					lpane.setLayer(lpane.getComponent(currLabel), Integer.MAX_VALUE);
					labelFlag[currLabel] = true;
					chances--;
				}
			});
			
			border = BorderFactory.createEmptyBorder();
			labelsBlur[prevLabel].setBorder(border);
			
			currLabel = randInt(0, chunks - 1);
			
			while(labelFlag[currLabel] == true)
			{
				currLabel = (currLabel + 1)%16;
			}
			
			Thread.sleep(speed);
			//sleep is the milliseconds before skipping to next label
			border = BorderFactory.createLineBorder(Color.blue);
	        labelsBlur[currLabel].setBorder(border);	//only include those numbers which are false in labelFlag
	        
	        prevLabel = currLabel;
		}
	}
	
	public void runGameLoop()
	{
		new Thread(new Runnable()
		{
			public void run()
			{
				try {
					gameLoop();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
