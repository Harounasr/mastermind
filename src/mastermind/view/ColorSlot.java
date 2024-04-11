package mastermind.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;


/**
 * Color circle slot.
 */
class ColorSlot extends JComponent{
	
	private static final long serialVersionUID = 6727087899628011219L;
	private int grid_x, grid_y; //position in grid
	private Color color;
	private int circleSize;
	
	/**
	 * Creates color slot.
	 * @param grid_x x position on grid
	 * @param grid_y y position on grid
	 * @param circleSize circle diameter in pixels
	 */
	public ColorSlot(int grid_x, int grid_y, int circleSize) {
		super();
		this.grid_x = grid_x;
		this.grid_y = grid_y;
		this.circleSize = circleSize;
		this.setPreferredSize(new Dimension(circleSize, circleSize));
		color = ColorPalette.defaultColor();		
	}
	
	/**
	 * Get x grid coordinate of a slot.
	 * @return x coordinate
	 */
	public int getGridX() {
		return grid_x;
	}
	
	/**
	 * Get y grid coordinate of a slot.
	 * @return y coordinate
	 */
	public int getGridY() {
		return grid_y;
	}
	
	/**
	 * Get slot's color
	 * @return current color of a slot
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Set new color to a slot
	 * @param color new color
	 */
	public void setColor(Color color) {
		this.color = color;
	}	
	
	@Override
	 protected void paintComponent(Graphics g) {			
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;	
			g2.setColor(color);
			g2.fillOval(0, 0, circleSize, circleSize);			
	 }	
}
