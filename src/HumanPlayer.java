
//import java.util.Random;
import java.util.Scanner;

public class HumanPlayer implements Player {
	//Ocean myocean = new Ocean();
	private Ship[][] ships = new Ship[10][10];
	int shotsFired;
	int hitCount;
	int shipsSunk;
    
	public HumanPlayer(){
		for(int i=0; i<10;i++){
			for (int j=0; j<10; j++){
				EmptySea emptysea = new EmptySea();
				ships[i][j] = emptysea;
				ships[i][j].setBowRow(i);
				ships[i][j].setBowColumn(j);
				
			}
		}
	}
	
	/**
	 * get the ships array which represents the ocean
	 * @return the ships array which represents the ocean
	 */
	Ship[][] getShipArray(){
		return this.ships;
	}
	
	/**
	 * tests whether a given row and column is occupied with a real ship.
	 * @param row
	 * @param column
	 * @return true if there is a real ship, false otherwise.
	 */
	boolean isOccupied(int row, int column){
		if(this.ships[row][column] instanceof EmptySea){
			return false;
		}
		return true;
	}
	
	/**
	 * print the ocean.  
	 */
	void print(){
		System.out.println("\t0 \t1 \t2 \t3 \t4 \t5 \t6 \t7 \t8 \t9");
		System.out.println();

		for(int row=0;row<10;row++) {
			
			System.out.print(row+"");
			for(int column =0; column<10; column++){
				boolean dir = this.ships[row][column].isHorizontal();
				int bowrow = this.ships[row][column].getBowRow();
				int bowcolumn = this.ships[row][column].getBowColumn();
				if(dir){
					if(this.ships[row][column].getHit()[column-bowcolumn]){
						
						System.out.print("\t"+this.ships[row][column].toString());
					}
					else{
						
						System.out.print("\t"+".");
					}
				}
				else{
					
					if(this.ships[row][column].getHit()[row-bowrow]){
						
						System.out.print("\t"+this.ships[row][column].toString());
					}
					else{
						System.out.print("\t"+".");
					}
				}

			}
			System.out.print("\n");
			System.out.println();

		}
	}
	
	boolean okToPlaceShipAt(Ship ship,int row, int column, int horizontal){
		boolean oktoplace=true;
		for (int i=0;i<ship.getLength();i++) {
				if(horizontal==1) {
					if(column+ship.getLength()>10) {
						return false;
					}
					else{
						if (!(this.ships[row][column+i] instanceof EmptySea) ){
							oktoplace=false;
						}
					}
				}else{
					if(row+ship.getLength()>10){
						return false;
					}
					else {
						if (!(this.ships[row+i][column] instanceof EmptySea) ){
							oktoplace=false;
						}
					}
					
				}
			}
		
		return oktoplace;
	}
	
	/**
	 * Put the ship in the ocean.This involves given value to the bowRow, bowColumn,and horizontal instance
	 * variable in the ship, and also involves putting a reference to the ship in each of 1 or more locations.
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 */
	void placeShipAt(Ship ship, int row, int column, int horizontal){
		ship.setBowRow(row);
		ship.setBowColumn(column);
		if(horizontal==1){
		ship.setHorizontal(true);
		}
		else{	
			ship.setHorizontal(false);
		}
		
		if(horizontal==1){
			for(int i =column; i< column+ship.getLength();i++){
		    this.ships[row][i]= ship;}
		}
		if (horizontal==0){
			for(int j= row; j<row+ship.getLength();j++){
				this.ships[j][column] = ship;}
		}
		    
	}
	
	/**
	 * This method will place a ship on the grid. This method should guarantee
	 * correctness of location (no overlaps, no ships over the edge of the
	 * board, etc.)
	 * 
	 * @param size
	 *            the size of the ship to place
	 * @param retry
	 *            if an earlier call to this method returned an invalid
	 *            position, this method will be called again with retry set to
	 *            true.
	 * @return The Location of the ship
	 */
	public Location placeShip(int size, boolean retry) {
		String inputRow;
		String inputColumn;
		String ishorizontal;
		int placeRow = 0;
		int placeColumn = 0;
		int horizontal;
		while (retry == true) {
			Scanner scanner = new Scanner(System.in);
			if (size == 5) {
				Aircraft_Carrier carrier = new Aircraft_Carrier();
				System.out
						.println("Please specify the bowrow to place carrier:");
				//Scanner scanner = new Scanner(System.in);
				inputRow = scanner.nextLine();
				//System.out.println(inputRow);
				System.out
						.println("Please specify the bowcolumn to place carrier:");
				inputColumn = scanner.nextLine();
				//System.out.println(inputColumn);
				System.out
						.println("Please specify horizontal or vertical, 1 for horizontal and 0 for vertical");
				ishorizontal = scanner.nextLine();
				//System.out.println(ishorizontal);
				
					placeRow = Integer.parseInt(inputRow);
					placeColumn = Integer.parseInt(inputColumn);
					horizontal = Integer.parseInt(ishorizontal);

					
						if (okToPlaceShipAt(carrier,placeRow, placeColumn,
								horizontal)) {
							placeShipAt(carrier,placeRow, placeColumn,
									horizontal);
							System.out.println("carrier!");
							retry = false;
						} else {
							retry = true;
						}
					}
				
			if (size == Game.BATTLESHIP) {
				System.out.println("Please specify the bowrow to place battleship:");
				//Scanner scanner = new Scanner(System.in);
				inputRow = scanner.nextLine();
				//System.out.println(inputRow);
				System.out.println("Please specify the bowcolumn to place battleship:");
				inputColumn = scanner.nextLine();
				//System.out.println(inputColumn);
				System.out
						.println("Please specify horizontal or vertical, 1 for horizontal and 0 for vertical");
				ishorizontal = scanner.nextLine();
				//System.out.println(ishorizontal);
				
					placeRow = Integer.parseInt(inputRow);
					placeColumn = Integer.parseInt(inputColumn);
					horizontal = Integer.parseInt(ishorizontal);
			
				Battleship battleship = new Battleship();
				if (okToPlaceShipAt(battleship,placeRow, placeColumn,
						horizontal)) {
					placeShipAt(battleship,placeRow, placeColumn, horizontal);
					retry = false;
					System.out.println("batteship!");
				} else {
					retry = true;
				}
			}

			if (size == Game.SUBMARINE) {
				System.out.println("Please specify the bowrow to place sub:");
				//Scanner scanner = new Scanner(System.in);
				inputRow = scanner.nextLine();
				System.out.println(inputRow);
				System.out.println("Please specify the bowcolumn to place sub:");
				inputColumn = scanner.nextLine();
				System.out.println(inputColumn);
				System.out
						.println("Please specify horizontal or vertical, 1 for horizontal and 0 for vertical");
				ishorizontal = scanner.nextLine();
				System.out.println(ishorizontal);
				
				
				placeRow = Integer.parseInt(inputRow);
				placeColumn = Integer.parseInt(inputColumn);
				horizontal = Integer.parseInt(ishorizontal);
	
				Submarine submarine = new Submarine();
				if (okToPlaceShipAt(submarine,placeRow, placeColumn,
						horizontal)) {
					placeShipAt(submarine,placeRow, placeColumn, horizontal);
					retry = false;
					System.out.println("submarine!");
				} else {
					retry = true;
				}
			}
				
			if (size == Game.DESTROYER) {
				System.out.println("Please specify the bowrow to place destroyer:");
				//Scanner scanner = new Scanner(System.in);
				inputRow = scanner.nextLine();
				System.out.println(inputRow);
				System.out.println("Please specify the bowcolumn to place destroyer:");
				inputColumn = scanner.nextLine();
				System.out.println(inputColumn);
				System.out
						.println("Please specify horizontal or vertical, 1 for horizontal and 0 for vertical");
				ishorizontal = scanner.nextLine();
				System.out.println(ishorizontal);
				
				placeRow = Integer.parseInt(inputRow);
				placeColumn = Integer.parseInt(inputColumn);
				horizontal = Integer.parseInt(ishorizontal);
	
				Destroyer destroyer = new Destroyer();
				if (okToPlaceShipAt(destroyer,placeRow, placeColumn,
						horizontal)) {
					placeShipAt(destroyer,placeRow, placeColumn, horizontal);
					retry = false;
					System.out.println("destroyer!");
				} else {
					retry = true;
				}
			}
		}
	
		
		return new MyLocation(placeRow, placeColumn);
	}

	/**
	 * This helps to find out whether the given string is an integer
	 * 
	 * @param str
	 * @return true if the string represents an integer, false if it is another
	 *         type.
	 */
	public boolean isInteger(String str) {

		if (str == null) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return str.length() > 0;
	}

	/**
	 * This method will get a new target. The Player can choose whichever
	 * location it wants to aim for next and return that location.
	 * 
	 * @return The Location of the target
	 */
	public Location getTarget() {
		String inputRow=null;
		String inputCol=null;
		int guessRow=0;
		int guessCol=0;
		Boolean isInt = false;
		while(! isInt){
			
		System.out.println("Please specify the bowrow to shot:");
		Scanner scanner = new Scanner(System.in);
		inputRow = scanner.nextLine();
		System.out.println("Please spcify the bowcolumn to shot:");
		inputCol = scanner.nextLine();
		
		if(isInteger(inputRow) && isInteger(inputCol)){
	    guessRow=Integer.parseInt(inputRow);
		guessCol=Integer.parseInt(inputCol);
		
		if(guessRow<10 && guessCol<10 && guessCol>=0 && guessCol>=0){
			isInt = true;
			continue;
		}
		}
		}
		MyLocation myshot = new MyLocation(guessRow, guessCol);
		return myshot;

	}

	/**
	 * This method will notify the Player of the result of the previous shot
	 * 
	 * @param hit
	 *            true, if it was a hit; false otherwise
	 * @param sun
	 *            true, if a ship is sunk; false otherwise
	 */
	public void setResult(boolean hit, boolean sunk) {
		shotsFired++;
		if (hit == true) {
			hitCount++;
			if (sunk == true) {
				shipsSunk++;
			}
		} else {
		}
	}
	/**
	 * get the value of shotsFired
	 * @return
	 */
	int getShotsFired(){
		return shotsFired;
	}
	
	/**
	 * get the value of hitCount
	 * @return
	 */
	int getHitCount(){
		return this.hitCount;
	}
	int getshipsSunk(){
		return this.shipsSunk;
	}
	
	/**
	 * tests whether all the ships are sunk
	 * @return true if all the ships are sunk, false otherwise
	 */
	boolean isGameOver(){
		if (this.shipsSunk ==5){
			return true;
		}
		return false;
	}
}
