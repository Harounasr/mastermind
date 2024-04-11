package mastermind.view;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import mastermind.model.MastermindGame;


/**
 * Panel with color slots.
 */
public class ColorSlotsPanel extends JPanel{
	
	private ColorSlot[][] slotsGrid;
	private JPopupMenu popup;
	private int numX, numY;	
	private int clickedSlotX = -1; //x-grid coordinate of clicked slot
	private int[] currColorCode; //color code input by user
	private int editRow = -1; //row number which is enabled for user edit 
		
	/**
	 * Creates the panel which contains color slots in a grid.
	 * @param numX number of slots along X
	 * @param numY number of slots along Y
	 * @param slotSize size of color slot in pixels
	 */
	public ColorSlotsPanel(int numX, int numY, int slotSize) {
		this.numX = numX;
		this.numY = numY;		
		
		this.setBackground(MainWindow.BG_COLOR);	
		this.setLayout(new GridLayout(numY, numX));
		
		currColorCode = new int[numX];
		setDefaultColorCode();
		
		//add color slots as a grid to panel
		slotsGrid = new ColorSlot[numY][numX];
		for (int row = 0; row < numY; row++)
			for (int col = 0; col < numX; col++) {
				ColorSlot cl = new ColorSlot(col, row, slotSize);
				this.add(cl);
				//slotsGrid.add(cl);
				slotsGrid[row][col] = cl;				
			}
		
		popup = createPopupMenu();	
		addMouseListener(new mouseAdapterOnSlot());		
	}	
	
	//defines default color code.
	private void setDefaultColorCode() {
		for (int i=0; i<numX; i++)
			currColorCode[i] = -1;
	}
	
	/**
	 * revert all changes on slots to default state (before beginning of new game).
	 */	
	public void revertChanges() {		
		for (int row = 0; row < numY; row++)
			for (int col = 0; col < numX; col++)
				repaintSlot(row, col, ColorPalette.defaultColor());
		setDefaultColorCode();
		editRow = -1;		
	}
	
	/**
	 * Enables edit on color slots in given row.
	 * @param row row number
	 */
	public void enableEditRow(int row) {		
		editRow = row;
		//set current color code input to default
		setDefaultColorCode();
	}	
	
	/**
	 * Creates a popup menu for a colored circle where player can choose new color
	 * @return popup-menu object
	 */
	protected JPopupMenu createPopupMenu(){
		JPopupMenu colorsMenu = new JPopupMenu();
		
		for (int i=0; i < MastermindGame.NUMBER_COLORS; i++) { //for each color create corresponding menu item
			JMenuItem colorMenuItem = new JMenuItem("Color "+i);
		    colorMenuItem.setActionCommand(""+i);
		    colorMenuItem.setBackground(ColorPalette.getColor(i));	  
		    		    
		    /* Add ActionListener to each menu item
		     * Listens what item(color) from color Popup menu was chosen and changes the color of given circle
		     */		  
		    colorMenuItem.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					int chosenColorIndex = Integer.valueOf(e.getActionCommand());
					setColorCodeInput(getClickedSlotX(), chosenColorIndex);
					repaintClickedSlot(ColorPalette.getColor(chosenColorIndex));
				
				}
			});
		    colorsMenu.add(colorMenuItem);
		}
		return colorsMenu;
	}
	
	/**
	 * repaints slot positioned at [row, col] with new color
	 * @param row row number of slot
	 * @param col column number of slot
	 * @param newColor new color to be repaint
	 */	
	protected void repaintSlot(int row, int col, Color newColor) {
		if (validIndex(row, col)) {
			slotsGrid[row][col].setColor(newColor);
			slotsGrid[row][col].repaint();
		}
	}
	
	private boolean validIndex(int row, int col) {
		return (row >= 0 && col >= 0 && row < numY && col < numX);
	}
	
	/**
	 * Updates colors at color slots in given row.
	 * @param row row number
	 * @param updateColors new colors to set into slots. 
	 */
	public void updateRowSlots(int row, int[] updateColors) {
		for(int col = 0; col < numX; col++) {
			repaintSlot(row, col, ColorPalette.getColor(updateColors[col]));
		}
	}
	
	/**
	 * Repaints a slot clicked by user
	 * @param newColor new color for clicked slot
	 */
	protected void repaintClickedSlot(Color newColor) {
		repaintSlot(getClickedSlotY(), getClickedSlotX(), newColor);		
	}
	
	
	/**
	 * Returns user input colors of active row.  
	 * @return int[] array with color codes
	 */
	public int[] getColorInput() {
		return currColorCode;
	}
	
	/**
	 * Get x-coordinate of clicked slot
	 * @return x grid coordinate of a slot which user clicked
	 */
	protected int getClickedSlotX() {
		return clickedSlotX;		
	}
	
	/**
	 * Get y-coordinate of clicked slot
	 * @return y grid coordinate of a slot which user clicked
	 */
	protected int getClickedSlotY() {
		return editRow;		
	}
	
	/**
	 * Set new entry in Color-code array.
	 * @param index index of new entry in color code array
	 * @param code new color code
	 */
	protected void setColorCodeInput(int index, int code) {
		currColorCode[index] = code;
	}	
	
	/**
	 * Mouse adapter that defines action that happens at mouse click on a slot. 
	 *
	 */
	private class mouseAdapterOnSlot extends MouseAdapter{
		@Override 
		public void mouseClicked(MouseEvent e) {  
        	 showPopup(e);	        	 
         } 
        @Override
         public void mousePressed(MouseEvent e) {  
        	 showPopup(e);	        	 
         } 
        @Override
         public void mouseReleased(MouseEvent e) {  
        	 showPopup(e);	        	 
         } 
         
         void showPopup(MouseEvent e) {        	 
        	 if (e.isPopupTrigger()) //popup menu only if clicked on color slot
        		 if (e.getComponent().getComponentAt(e.getX(), e.getY()) instanceof ColorSlot) {
        			 //save the slot that was clicked in local var
        			 ColorSlot clickedSlot = (ColorSlot)e.getComponent().getComponentAt(e.getX(), e.getY());
        			 
        			 if (clickedSlot.getGridY() == editRow) { //show popup only if slot from editing row clicked        				 
        				popup.show(e.getComponent(), e.getX(), e.getY());
        			 	clickedSlotX = clickedSlot.getGridX();
        			 }
        		 }	        	 
         } 
	}
}
