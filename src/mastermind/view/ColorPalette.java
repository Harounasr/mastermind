package mastermind.view;
import java.awt.Color;

import mastermind.model.MastermindGame;

/**
 * Class of color palette, consisting of "NUMBER_COLORS" colors
 */
public final class ColorPalette {
	private final static Color DEFAULT_COLOR = Color.GRAY;
	
	private ColorPalette() {}
	
	/**
	 * Get color from palette based on its index number.
	 * @param idx index [0..NUMBER_COLORS]
	 * @return Color 
	 */
	public static Color getColor(int idx) {
		/* produce color using HSB-color model.
		 * saturation and brightness are fixed to 1 
		 * hue varies based on index
		 */		
		return Color.getHSBColor((float)idx/MastermindGame.NUMBER_COLORS, 1, 1);		
	}	
	
	/**
	 * Get default color of color slots
	 * @return default color
	 */
	public static Color defaultColor() {
		return DEFAULT_COLOR;
	}
}
