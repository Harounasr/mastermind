package mastermind.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * Panel with slots for Rating.
 */
public class EvalSlotsPanel extends ColorSlotsPanel{ 
	//ids for black and white eval codes
	private final static int CODE_BLACK = 0;
	private final static int CODE_WHITE = 1;
	private final static int CODE_NONE = -1;
	
	/**
	 * Creates the panel which contains slots with Rating in a grid.
	 * @param numX number of slots along X
	 * @param numY number of slots along Y
	 * @param slotSize size of color slot in pixels
	 */
	public EvalSlotsPanel(int numX, int numY, int slotSize) {
		super(numX, numY, slotSize);		
	}
	
	/**
	 * Creates a popup menu to set black/white color on clicked slot
	 * @return JPopupMenu menu object
	 */
	@Override
	protected JPopupMenu createPopupMenu() {
		JPopupMenu bwMenu = new JPopupMenu();
		
		//action on menu item click
		ActionListener setBWAction = new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				int chosenColorId = Integer.valueOf(e.getActionCommand());
				switch (chosenColorId) {
					case CODE_BLACK:
						repaintClickedSlot(Color.BLACK);
						break;
					case CODE_WHITE:
						repaintClickedSlot(Color.WHITE);
						break;
					case CODE_NONE:
						repaintClickedSlot(ColorPalette.defaultColor());
						break;
					default:
						repaintClickedSlot(ColorPalette.defaultColor());							
				}
				setColorCodeInput(getClickedSlotX(), chosenColorId);
			}
		};
		
		JMenuItem blackMenuItem = new JMenuItem("Black");
		
	    blackMenuItem.setActionCommand(""+CODE_BLACK);
	    blackMenuItem.setBackground(Color.BLACK);
	    blackMenuItem.setForeground(Color.WHITE);
	    blackMenuItem.addActionListener(setBWAction);
	    bwMenu.add(blackMenuItem);
	    
	    JMenuItem whiteMenuItem = new JMenuItem("White");
	    whiteMenuItem.setActionCommand(""+CODE_WHITE);
	    whiteMenuItem.setBackground(Color.WHITE);
	    whiteMenuItem.addActionListener(setBWAction);
	    bwMenu.add(whiteMenuItem);
	    
	    JMenuItem defaultMenuItem = new JMenuItem("None");
	    defaultMenuItem.setActionCommand(""+CODE_NONE);
	    defaultMenuItem.setBackground(ColorPalette.defaultColor());
	    defaultMenuItem.addActionListener(setBWAction);
	    bwMenu.add(defaultMenuItem);
		
		return bwMenu;
	}
	
	/**
	 * Paint evaluation slots according with black/white rating. 
	 * @param row slots row number
	 * @param black number of black slots
	 * @param white number of white slots
	 */
	void setEvalCode(int row, int black, int white) {		
		for (int blackNum = 0; blackNum<black; blackNum++)
			repaintSlot(row, blackNum, Color.BLACK);
		for (int whiteNum = black; whiteNum<black+white; whiteNum++)
			repaintSlot(row, whiteNum, Color.WHITE);			
	}
	
	/**
	 * Get black rating from user's input.	
	 * @return number of black strides in rating
	 */
	public int getBlackRate() {		
		return getRate(CODE_BLACK);
	}
	
	/**
	 * Get white rating from user's input.
	 * @return number of white strides in rating
	 */
	public int getWhiteRate() {		
		return getRate(CODE_WHITE);
	}	
	
	private int getRate(int code) {
		int[] currColorCode = getColorInput();
		int rate = 0;		
		for (int i=0; i<currColorCode.length; i++)
			if (currColorCode[i] == code)
				rate++;
		return rate;
	}
}
