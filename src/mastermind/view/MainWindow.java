package mastermind.view;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import mastermind.model.MastermindGame;

import java.awt.*;
import java.awt.event.ActionListener;


/**
 * The main interface window.
 *
 */
public class MainWindow{
	//window constants
	//min size of window
	private final static int MIN_WIDTH = 420; 
	private final static int MIN_HEIGHT = 640; 
	//slots sizes
	private final static int COLOR_SLOT_SIZE = 50;
	private final static int EVAL_SLOT_SIZE = 20;
	/**
	 * Background color
	 */
	final static Color BG_COLOR = Color.getHSBColor(0.08f, 0.3f, 0.97f);
	
	//window components
	private JFrame mainFrame;
	private ColorSlotsPanel colorsSlotsPanel;
	private EvalSlotsPanel bwSlotsPanel;
	private ColorSlotsPanel secretCodePanel;
	private JLabel statusLbl;
	private JButton newGameBtn;
	private JButton switchBtn;
	private JButton moveBtn;	

	/**
	 * Create main game window and its components
	 */
	public MainWindow() {
		mainFrame = new JFrame("Mastermind");
		Container pane = mainFrame.getContentPane(); //frame content 
		pane.setBackground(BG_COLOR);		
		pane.setLayout(new GridBagLayout()); //define layout of mainFrame as gridbag layout
		GridBagConstraints c = new GridBagConstraints();
		
		//Creating frame components
		//label with messages for user
		statusLbl = new JLabel("Master mind game");
		statusLbl.setMinimumSize(new Dimension((int)(MIN_WIDTH*0.8), (int)(MIN_HEIGHT*0.05)));
		statusLbl.setBackground(Color.ORANGE);
		statusLbl.setOpaque(true);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.weighty = 0.1;
		pane.add(statusLbl, c);
		
		//Panel with color slots
		colorsSlotsPanel = new ColorSlotsPanel(MastermindGame.NUMBER_SLOTS, MastermindGame.MAX_MOVES, COLOR_SLOT_SIZE);
		colorsSlotsPanel.setMinimumSize(new Dimension((int)(MIN_WIDTH*0.75), (int)(MIN_HEIGHT*0.75)));
		colorsSlotsPanel.setPreferredSize(new Dimension((int)(MIN_WIDTH*0.75), (int)(MIN_HEIGHT*0.75)));
		//c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTHEAST;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		//c.weightx = 0.5;
		c.weighty = 0.8;
		c.insets.top = 5;		
		pane.add(colorsSlotsPanel, c);
		
		//Panel with black/white slots for Rating
		bwSlotsPanel = new EvalSlotsPanel(MastermindGame.NUMBER_SLOTS, MastermindGame.MAX_MOVES, EVAL_SLOT_SIZE);
		bwSlotsPanel.setMinimumSize(new Dimension((int)(MIN_WIDTH*0.25), (int)(MIN_HEIGHT*0.75)));
		bwSlotsPanel.setPreferredSize(new Dimension((int)(MIN_WIDTH*0.25), (int)(MIN_HEIGHT*0.75)));
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		c.weighty = 0.8;
		pane.add(bwSlotsPanel, c);
		
		//Panel with slots for secret code
		secretCodePanel = new ColorSlotsPanel(MastermindGame.NUMBER_SLOTS, 1, COLOR_SLOT_SIZE);
		secretCodePanel.setMinimumSize(new Dimension((int)(MIN_WIDTH*0.75), (int)(MIN_HEIGHT*0.12)));
		secretCodePanel.setPreferredSize(new Dimension((int)(MIN_WIDTH*0.75), (int)(MIN_HEIGHT*0.12)));
		secretCodePanel.setBorder(new TitledBorder("Secret"));
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.weighty = 0.1;
		c.insets.top = 5;
		pane.add(secretCodePanel, c);		
		
		//New Game button
		newGameBtn = new JButton("New");
		c.anchor = GridBagConstraints.PAGE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.weightx = 0.3;
		c.weighty = 0.1;
		pane.add(newGameBtn, c);
		
		//Switch button
		switchBtn = new JButton("Switch");
		c.anchor = GridBagConstraints.PAGE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 0.3;
		c.weighty = 0.1;
		pane.add(switchBtn, c);
		
		//Move button
		moveBtn = new JButton("Move");
		c.anchor = GridBagConstraints.PAGE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		c.weightx = 0.3;
		c.weighty = 0.1;
		pane.add(moveBtn, c);
		
		mainFrame.revalidate();
		mainFrame.pack();
		mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		mainFrame.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		mainFrame.setSize(MIN_WIDTH, MIN_HEIGHT);		
	}		
	
	/**
	 * Set text on Status-label.
	 * @param text
	 */
	public void setStatusLblText(String text) {
		this.statusLbl.setText(text);
	}	
	
	// functions to add action to buttons 
	/**
	 * Adds Actions (ActionListener) to the button "Move" 
	 * @param moveAction object of type ActionListener that defines action when button Move clicked
	 */
	public void addMoveAction(ActionListener moveAction) {
		moveBtn.addActionListener(moveAction);		
	}
	
	/**
	 * Adds Actions (ActionListener) to the button "New Game". 
	 * @param newGameAction object of type ActionListener that defines action when button "New Game" clicked
	 */
	public void addNewGameAction(ActionListener newGameAction) {
		newGameBtn.addActionListener(newGameAction);		
	}
	
	/**
	 * Adds Actions (ActionListener) to the button "Switch". 
	 * @param switchAction object of type ActionListener that defines action when button "Switch" clicked
	 */
	public void addSwitchAction(ActionListener switchAction) {
		switchBtn.addActionListener(switchAction);		
	}
	
	/**
	 * Get colors selected by user
	 * @return array with color codes
	 */
	public int[] getUserColorInput() {
		return colorsSlotsPanel.getColorInput();
	}
	
	/**
	 * Get black number from rating given by user.
	 * @return number of black
	 */
	public int getBlackInput() {
		return bwSlotsPanel.getBlackRate();
	}
	
	/**
	 * Get white number from rating given by user.
	 * @return number of white
	 */
	public int getWhiteInput() {
		return bwSlotsPanel.getWhiteRate();
	}
	
	/**
	 * Paint Eval-slots according to black/white rating.
	 * @param row row number
	 * @param black number of black 
	 * @param white number of white
	 */		
	public void setEvalResult(int row, int black, int white) {
		bwSlotsPanel.setEvalCode(row, black, white);
	}
	
	/**
	 * Paint color slots according with given color codes.
	 * @param row row number
	 * @param colorCodes an array of color codes
	 */
	public void setColorCodes(int row, int[] colorCodes) {
		colorsSlotsPanel.updateRowSlots(row, colorCodes);		
	}
	
	/**
	 * enable user edit of color slots at certain row
	 * @param row row number
	 */
	public void enableColorSlotsEdit(int row) {
		colorsSlotsPanel.enableEditRow(row);		
	}
	
	/**
	 * enable user edit of Eval slots at certain row.
	 * @param row row number
	 */
	public void enableEvalSlotsEdit(int row) {
		bwSlotsPanel.enableEditRow(row);		
	}
	
	/**
	 * Clear all progress made. Repaint all components in clear state.
	 */
	public void clearProgress() {
		colorsSlotsPanel.revertChanges();
		bwSlotsPanel.revertChanges();
		secretCodePanel.revertChanges();
	}
	
	/**
	 * Reveals the secret code on screen.
	 * @param secretCode an array with secret code.
	 */
	public void showSecret(int[] secretCode) {
		this.secretCodePanel.updateRowSlots(0, secretCode);		
	}
	
	/**
	 * Enable editing of secret-colors slots.
	 * @param enabled true to accept secret edit
	 */
	public void enableEditSecretSlots(boolean enabled) {
		if (enabled)
			secretCodePanel.enableEditRow(0);
		else
			secretCodePanel.enableEditRow(-1);
	}
	
	/**
	 * Get main Frame that contains user interface.
	 * @return JFrame main window in user interface
	 */
	public JFrame getMainFrame() {
		return this.mainFrame;
	}
}

