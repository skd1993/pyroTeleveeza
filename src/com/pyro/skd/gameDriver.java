package com.pyro.skd;

public class gameDriver {

	public gameDriver() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String argsp[])
	{
		imageLayers split = new imageLayers();
		split.createUI();
		randomGlow game = new randomGlow();
		game.getterSetter();
		game.runGameLoop();
	}

}
