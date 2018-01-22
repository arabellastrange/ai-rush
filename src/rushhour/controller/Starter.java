package rushhour.controller;

import rushhour.view.GameFrame;

public class Starter {

	public static void main(String[] args) {
		final RushHour controller = new RushHour();
		new GameFrame("Rush Hour", controller);
	}
	
}
