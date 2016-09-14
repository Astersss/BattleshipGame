
/**
 * @author aosun
 *
 */
public abstract class Ship {
	private int bowRow;
	private int bowColumn;
	private int length;
	private boolean horizontal;
	private boolean [] hit =new boolean[5];
	//private Ship[][] ships = new Ship[10][10];
	//private MyLocation shiplocation = new MyLocation();
	
	/**
	 * This method just return the length of this particular ship
	 * @return int type length of a particular ship
	 */
	public int getLength(){
		return this.length;
	}
	
	/**
	 * getter function used to get the bow row of a ship
	 * @return the row number where the ship's bow is located.
	 */
	int getBowRow(){
		return this.bowRow;
	}
	
	/**
	 * getter function used to get the bow column of a ship
	 * @return the column number where the ship's bow is located 
	 */
	int getBowColumn(){
		return this.bowColumn;
	}
	
	/**
	 * getter function of a how a ship is placed
	 * @return true is the ship occupies a single row, false if it occupies a single column.
	 */
	boolean isHorizontal(){
		return this.horizontal;
	}
	
	/**
	 * getter function of the hit array of a ship
	 * @return the boolean type hit array of a ship
	 */
	boolean[] getHit(){
		return this.hit;
	}
	
	/**
	 * Setter function: Set the value of a given boolean array hit to be false
	 * @param len
	 */
	public void setHit(int len){
		for(int i=0; i<len; i++){
			this.hit[i] = false;
		}	
	}
	
	/**
	 * Setter function: Set the value of a given length boolean array hit to be true
	 * @param len
	 */
	public void setHittrue(int len){
		for(int i=0;i<len;i++){
			this.hit[i]=true;
		}
	}
	
	/**
	 * Setter function: Set the length of the ship
	 * @param len
	 */
	public void setLength(int len){
		this.length = len;
	}
	
	/**
	 * Set the value of bowRow with the given value of row.
	 * @param row
	 */
	void setBowRow(int row){
		this.bowRow =row;
	}
	
	/**
	 * Set the value of bowColumn with the given value of column.
	 * @param column
	 */
	void setBowColumn(int column){
		this.bowColumn=column;
	}
	
	/**
	 * Set the value of the instance variable horizontal
	 * @param horizontal
	 */
	void setHorizontal(boolean horizontal){
		this.horizontal = horizontal;
	}
	
	boolean isSunk(){
		for(int i=0; i<this.length;i++){
			if(this.hit[i] == false){
				return false;
			}
		}
		return true;
	}
	
	/*
	 * this is an abstract method with no body
	 */
	abstract String getShipType();
	
	
	
	
	boolean shootAt(int row, int column) {
		if(!isSunk()) {
			if (!horizontal){
				if (((bowRow <= row) && (row < bowRow+length)) && (column == bowColumn)) {
					hit[row-bowRow] = true;
					//System.out.println(getHit()[row - bowRow]);
					return true;}
				else return false;}
			else {
				if (((bowColumn <= column) && (column < bowColumn + length)) && (row == bowRow)) {
					hit[column-bowColumn] = true;
					return true;}
				else return false;}}
		else return false;} 
	
	
	
	public String toString(){
		if(this.isSunk()==true){
			return "x";}
		else{
			return "S";
		}
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
