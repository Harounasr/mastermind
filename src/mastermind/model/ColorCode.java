package mastermind.model;

/**
 * Class color code.
 */
public class ColorCode implements Cloneable {
	
 	private final int[] colorCode; 
 	
 	/**
 	 * Creates a new color code and initialize the color code length. 
 	 */
 	public ColorCode() {
 		colorCode = new int[MastermindGame.NUMBER_SLOTS];	
 	}
		
	/**
	 * Creates a new color code and sets the codes. 
	 * 
	 * @param code The Array that includes the color code.
	 */
    public ColorCode(int[] code) {
	    colorCode = new int[MastermindGame.NUMBER_SLOTS];
	    for (int i = 0; i < Game.NUMBER_SLOTS; i++) {
	    	colorCode[i] = code[i];
	    }
    }
	      
	/**   
	 * Sets the color{@code color} in the position{@code pos} of the color
	 * code.
	 * 
	 * @param pos The color position in the color code.
	 * @param color The color.
	 */
    public void setColor(int pos, int color) {
	    colorCode[pos] = color;
	}
	    	
	/**   
	 * Compares two color codes and calculates the number of black spikes , and
	 * using the getWhite method also calculates the white spikes. the number
	 * of black spikes is increased by one if both codes have the same colors
	 * in the same positions.
	 * 
	 * It is stored in the two positions in the both color codes (where 
	 * the black spikes occur) -1 to not count again when calculating from the
	 * white spikes.
	 *  
	 * @param otherCode The second color code.
	 * @return The resulting rating for black and white spikes.
	 */
	public Rating compare(ColorCode otherCode) {
	    int black = 0;
	    ColorCode firstCode = new ColorCode(this.colorCode);
	    ColorCode secondCode = new ColorCode(otherCode.colorCode);
	    for (int i = 0; i < colorCode.length; i++) {
	        if (secondCode.colorCode[i] == firstCode.colorCode[i]) {
	            black++;
	            
	            // -1 that it isn't counted when calculating the white spikes.
	            secondCode.colorCode[i] = -1;
	            firstCode.colorCode[i] = -1;
	        }
	    }       
	    return new Rating(black, getWhiteNumber(firstCode, secondCode));
	}
	    
	/**
	 * To clone a color code
	 *  
	 *  @return The cloned color code.
	 */
    @Override
    public ColorCode clone() { 
	    ColorCode code = new ColorCode();
	    for (int i = 0; i < MastermindGame.NUMBER_SLOTS; i++) {
	        code.colorCode[i] = colorCode[i];
	    }
	    return code;    	
	    }
	 
    /**
     * Calculates the white spikes. The number of white spikes is increased if
     * the two codes contain the same color but in different positions.
     * It is stored in the two positions -1 to not count again 
     * 
     * @param firstCode The first color code.
     * @param secondCode The second color code.
     * @return The number of white spikes.
     */
	public int getWhiteNumber(ColorCode firstCode, ColorCode secondCode) {
	    int white = 0;
	    for (int i = 0; i < colorCode.length; i++) {
	        if (secondCode.colorCode[i] != -1) {
	            for (int j = 0; j < colorCode.length; j++) {
	                if (secondCode.colorCode[i] != -1 
	                		&& secondCode.colorCode[i] 
	                				== firstCode.colorCode[j]) {
	                    white++;
	                    secondCode.colorCode[i] = -1;
	                    firstCode.colorCode[j] = -1;
	                }
	            }
	        }
	    }
	    return white;
	}
	   
	/**
	 * Returns the string representation of this color code.
	 * 
	 * @return The string representation of this color code.
	 */
	@Override
	public String toString() {
		StringBuilder stringbuilder = new StringBuilder();
		for (int i = 0; i < Game.NUMBER_SLOTS; i++) {
			stringbuilder.append(colorCode[i] + " ");			
		}
		return stringbuilder.toString();
	}
	/**
	 * returns int[] representation of this code 
	 * @return int[] color code
	 */
	public int[] toIntArray() {
		return colorCode;
	}
}
	    
	    
	    
	    
	
