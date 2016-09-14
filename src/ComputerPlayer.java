
import java.util.Random;

public class ComputerPlayer implements Player{
	
	private Ship[][] ships = new Ship[10][10];
	int targetx;
	int targety;
	int shotsFired;
	int hitCount;
	int shipsSunk;
	
	public ComputerPlayer(){
		for(int i=0; i<10;i++){
			for (int j=0; j<10; j++){
				EmptySea emptysea = new EmptySea();
				this.ships[i][j] = emptysea;
				this.ships[i][j].setBowRow(i);
				this.ships[i][j].setBowColumn(j);
				
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
		if(ships[row][column] instanceof EmptySea){
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
				boolean dir = ships[row][column].isHorizontal();
				int bowrow = ships[row][column].getBowRow();
				int bowcolumn = ships[row][column].getBowColumn();
				if(dir){
					if(ships[row][column].getHit()[column-bowcolumn]){
						
						System.out.print("\t"+ships[row][column].toString());
					}
					else{
						
						System.out.print("\t"+".");
					}
				}
				else{
					
					if(ships[row][column].getHit()[row-bowrow]){
						
						System.out.print("\t"+ships[row][column].toString());
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
						if (!(ships[row][column+i] instanceof EmptySea) ){
							oktoplace=false;
						}
					}
				}else{
					if(row+ship.getLength()>10){
						return false;
					}
					else {
						if (!(ships[row+i][column] instanceof EmptySea) ){
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
		    ships[row][i]= ship;}
		}
		if (horizontal==0){
			for(int j= row; j<row+ship.getLength();j++){
				ships[j][column] = ship;}
		}
		    
	}

	/**
	 * This method will place a ship on the grid.
	 * This method should guarantee correctness of location (no overlaps, no ships over the edge of the board, etc.)
	 * @param size the size of the ship to place
	 * @param retry if an earlier call to this method returned an invalid position, this method will be called again with retry set to true.
	 * @return The Location of the ship
	 */
	public Location placeShip(int size, boolean retry){
		
		
		Aircraft_Carrier carrier =new Aircraft_Carrier();
		Battleship battleship =new Battleship();
		Submarine submarine =new Submarine();
		Destroyer destroyer = new Destroyer();
		int setBowRow=0;
		int setBowColumn=0;
		int ishorizontal=0;
		while(retry==true){
			Random random = new Random();
			setBowRow =random.nextInt(10);
			setBowColumn = random.nextInt(10);
			ishorizontal =random.nextInt(2);
		    
		if(size==5){
			
			
			if (okToPlaceShipAt(carrier,setBowRow, setBowColumn, ishorizontal)) {
				placeShipAt(carrier,setBowRow, setBowColumn, ishorizontal);
				System.out.println("carrier!");
				System.out.print(setBowRow);
				System.out.print(setBowColumn);
				System.out.print(ishorizontal);
				retry = false;
			} else {
				retry = true;
			}
		}
		if(size==4){
			if (okToPlaceShipAt(battleship,setBowRow, setBowColumn, ishorizontal)) {
				placeShipAt(battleship,setBowRow, setBowColumn, ishorizontal);
				System.out.println("battleship!");
				System.out.print(setBowRow);
				System.out.print(setBowColumn);
				System.out.print(ishorizontal);
				retry = false;
			} else {
				retry = true;
			}
		}
		
		if(size==3){
			if (okToPlaceShipAt(submarine,setBowRow, setBowColumn, ishorizontal)) {
				placeShipAt(submarine,setBowRow, setBowColumn, ishorizontal);
				System.out.println("submarine!");
				System.out.print(setBowRow);
				System.out.print(setBowColumn);
				System.out.print(ishorizontal);
				retry = false;
			} else {
				retry = true;
			}
		}
		
		if(size==2){
			if (okToPlaceShipAt(destroyer,setBowRow, setBowColumn, ishorizontal)) {
				placeShipAt(destroyer,setBowRow, setBowColumn, ishorizontal);
				System.out.println("destroyer!");
				System.out.print(setBowRow);
				System.out.print(setBowColumn);
				System.out.print(ishorizontal);
				retry = false;
			} else {
				retry = true;
			}
		}
		}
		
		return new MyLocation(setBowRow,setBowColumn);
	}
	
	/**
	 * This method will get a new target.
	 * The Player can choose whichever location it wants to aim for next and return that location. 
	 * @return The Location of the target
	 */
	public Location getTarget(){
		Random random = new Random();
		targetx =random.nextInt(10);
		targety = random.nextInt(10);
		MyLocation shot = new MyLocation(targetx, targety);
		return shot;
		
	}
	

	/**
	 * This method will notify the Player of the result of the previous shot
	 * @param hit true, if it was a hit; false otherwise
	 * @param sunk true, if a ship is sunk; false otherwise
	 */
	public void setResult(boolean hit, boolean sunk){
	
		shotsFired++;
		if(hit==true){
			hitCount++;
			//System.out.println("hit!");
			if(sunk==true){
				shipsSunk++;
				
			}
		}
		else{}
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



