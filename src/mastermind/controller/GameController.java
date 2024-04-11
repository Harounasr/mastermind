package mastermind.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import mastermind.model.ColorCode;
import mastermind.model.Game;
import mastermind.model.MastermindGame;
import mastermind.model.Rating;
import mastermind.view.MainWindow;

/**
 * Game controller class that binds logic and UI.
 */
public class GameController {
	private MastermindGame gameLogic;
	private MainWindow gameWindow;
	
	/**
	 * Game Controller that binds game UI and logic.
	 * @param gameWindow game user interface
	 * @param gameLogic game logic component
	 */
	public GameController(MainWindow gameWindow, MastermindGame gameLogic) {
		this.gameWindow = gameWindow;
		this.gameLogic = gameLogic;		
	}
	
	/**
	 * Runs the game with GUI.
	 */
	public void run() {
		newGame(false);		
		setMoveAction();
		setNewGameAction();
		setSwitchAction();		
		gameWindow.getMainFrame().setVisible(true);
		updateStatusLabel();		
	}
	
	private void newGame(boolean machineGuess) {
		gameWindow.clearProgress();	
		gameLogic = new Game(machineGuess);	
    	
    	if (gameLogic.isMachineGuessing())
    		makeMachineGuess();
    	updateEditRow();
	}
	
	private void updateEditRow() {
		if (gameLogic.isMachineGuessing()) {
			gameWindow.enableEvalSlotsEdit(gameLogic.getMoveCount()-1);	
			gameWindow.enableEditSecretSlots(true);
		}else {
			gameWindow.enableColorSlotsEdit(gameLogic.getMoveCount());
			gameWindow.enableEditSecretSlots(false);
		}		
	}	
	
	private void updateStatusLabel() {
		if (gameLogic.isMachineGuessing())
			gameWindow.setStatusLblText("Machile guess. Select black/white");		
		else
			gameWindow.setStatusLblText("Your turn to guess. Select colors");		
	}	
	
	private void setMoveAction() {
		ActionListener move = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (gameLogic.isMachineGuessing()) {
		    		Rating userRating = new Rating(gameWindow.getBlackInput(), gameWindow.getWhiteInput());
		    		gameLogic.eval(userRating);
		    		if (userRating.getBlack() == MastermindGame.NUMBER_SLOTS) //check if machine already won 
			    		JOptionPane.showMessageDialog(gameWindow.getMainFrame(), "Wow, Machine won!");
		    		else 		    		
			    		makeMachineGuess();
		    	
		    	} else { //when user guessing colors 
		    		if (isColorInputValid()) 
		    			makeHumanMove();
		    	}
		    	updateEditRow();
		    }          
		 };
		 gameWindow.addMoveAction(move); 		
	}
	
	private boolean isColorInputValid() {
		int[] colorInput = gameWindow.getUserColorInput();
		for (int i = 0; i < colorInput.length; i++) 
			if (colorInput[i] == -1)
				return false;		
		return true;		
	}	
	
	private void makeHumanMove() {
		Rating moveRating = gameLogic.humanMove(new ColorCode(gameWindow.getUserColorInput()));
    	gameWindow.setEvalResult(gameLogic.getMoveCount()-1, moveRating.getBlack(), moveRating.getWhite());
    	if (moveRating.getBlack() == MastermindGame.NUMBER_SLOTS) { //check if user won
    		revealSecret(); 
    		JOptionPane.showMessageDialog(gameWindow.getMainFrame(), "You won!");				    		
    	} else if (gameLogic.getMoveCount() == MastermindGame.MAX_MOVES) { //if max number of moves reached
    		revealSecret();
    		JOptionPane.showMessageDialog(gameWindow.getMainFrame(), "Game over: No more moves");				    		
    	}    	
	}
	
	private void makeMachineGuess() {		
		ColorCode machineGuess = gameLogic.machineMove();
		if (machineGuess == null)
			JOptionPane.showMessageDialog(gameWindow.getMainFrame(), "You have been cheating!");
		else	
			gameWindow.setColorCodes(gameLogic.getMoveCount()-1, machineGuess.toIntArray());		
	}
	
	private void revealSecret() {
		gameWindow.showSecret(
		gameLogic.getSecret().toIntArray());
	}	
	
	private void setNewGameAction() {
		ActionListener newGameAction = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	newGame(gameLogic.isMachineGuessing());		       
		    }          
		 };
		 gameWindow.addNewGameAction(newGameAction);		
	}
	
	private void setSwitchAction() {
		ActionListener switchAction = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {		    	
		    newGame(!gameLogic.isMachineGuessing());
		    updateStatusLabel();
		    }
		 };
		 gameWindow.addSwitchAction(switchAction);		
	}
}
