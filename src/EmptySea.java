

class EmptySea extends Ship{

	public EmptySea() {
		super();
		setLength(1);
		setHit(1);
	}

	/**
	 * This method overrides shootAt(int row, int column) that is inherited from Ship.
	 * and always return false to indicate that nothing was hit.
	 */
	@Override
	boolean shootAt(int row, int column) {
		setHittrue(1);
		return false;
	}
	
	/**
	 * This method overrides isSunk() that is inherited from Ship, and always returns false
	 * to indicate that you didn't sink anything
	 */
	@Override
	boolean isSunk(){
		return false;
	
	}
	
	/**
	 * returns a single-character String to use in the Ocean's print method.
	 */
	@Override
	public String toString(){
		if(this.getHit()[0]){
		return "-";}
		return ".";
	}
	
	/**
	 * this method just returns the string empty.
	 */
	@Override
	String getShipType(){
		return "empty";
	}
}
