
/**
 * @author aosun
 *
 */
public class MyLocation implements Location{
	private int xcood;
	private int ycood;
	boolean horizontal;
	
	public MyLocation(int x, int y, boolean ishorizontal){
	    xcood=x;
	    ycood=y;
	    horizontal = ishorizontal;
	}
	public MyLocation(int x, int y){
		xcood=x;
		ycood=y; 
	}
	public MyLocation(){
		
	}
	/**
	 * Gets the x coordinate.
	 * It should return a number between 0 and 9. 
	 * The top-left position will be 0, 0.
	 * @return the x coordinate
	 */
	public int getX(){
		
		return this.xcood;
	}
    
	/**
	 * Gets the y coordinate.
	 * It should return a number between 0 and 9. 
	 * The top-left position will be 0, 0.
	 * @return the y coordinate
	 */
	public int getY(){
		return this.ycood;
	}
	
	/**
	 * This method will indicate whether the ship is horizontal or vertical.
	 * Can return an arbitrary value if the location is used to indicate a shot (and not a ship)
	 * @return true if ship is horizontal, false otherwise
	 */
	public boolean isShipHorizontal(){
		return this.horizontal;
	}
	

	

}
