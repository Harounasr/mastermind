package mastermind.model;

/**
 * class Rating.
 */
public class Rating {

    private final int blackNumber, whiteNumber;
    
    /**
     * Creates a new rating and sets the black and white numbers.
     * 
     * @param black the black spikes number.
     * @param white the white spikes number.
     */
    public Rating(int black, int white) {
        blackNumber = black;
        whiteNumber = white;
    }
    /**
     * Gets the the number of black spikes.
     * 
     * @return The rating in the number of black spikes.
     */
    public int getBlack() {
        return blackNumber;
    }
    
    /**
     * Gets the the number of white spikes.
     * 
     * @return The rating in the number of white spikes.
     */
    public int getWhite() {
        return whiteNumber;
    }
    
    /**
	 * Returns the string representation of this rating.
	 * 
	 * @return The string representation of this rating.
	 */
    @Override
    public String toString() {
    	return "black: " + getBlack() + " white: " + getWhite();  	
    }
    
    /**
     * Checks whether another object is similar to this one
     * 
     * @param o An object.
     * @return {code true} if the other object is equivalence to this object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
        	return true;
        } else if (o == null) {
        	return false;
        } else {
        	Rating rate = (Rating) o;
        	return this.toString().equals(rate.toString()); 	
        }       
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + blackNumber;
        hash = 31 * hash + whiteNumber;
        return hash;
    }

}

