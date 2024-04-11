package mastermind;

import mastermind.controller.GameController;
import mastermind.model.Game;
import mastermind.view.MainWindow;

public class MainGame {
	
	private MainGame() {}
	
	/**
	 * Main function to be executed to run the game. 
	 * @param args arguments string
	 */
	public static void main(String[] args) {		
		GameController game = new GameController(new MainWindow(), new Game());	
		game.run();
	}
}
