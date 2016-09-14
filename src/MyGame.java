import java.util.Scanner;


/**
 * @author aosun
 *
 */
public class MyGame implements Game {
	

	/**
	 * 
	 */
	public MyGame() {
		
	}
	
	

	/**
	 * This method will initialize the game. 
	 * At the end of this method, the board has been set up and the game can be started
	 * @param p1 Player 1
	 * @param p2 Player 2
	 */
	public void initialize(Player p1, Player p2){
		System.out.println("Welcome to the battleship game\nyour goal is to sink all of the ships as fast as possible!");
		boolean retry=true;
	    p1.placeShip(5, retry);
	    p1.placeShip(4, retry);
	    p1.placeShip(3, retry);
	    p1.placeShip(3,retry);
	    p1.placeShip(2, retry);
	    //System.out.println("COMPUTER COMPLETED");
	    
	    
	    p2.placeShip(5, retry);
	    p2.placeShip(4, retry);
	    p2.placeShip(3, retry);
	    p2.placeShip(3, retry);
	    p2.placeShip(2,retry);
	    
	    
	}
	
	/**
	 * This is the start point of playing the game. 
	 * The game will alternate between the players letting them take shots at the other team.
	 * @return Player who won
	 */
	public Player playGame(){
		//System.out.println("miss!");
		ComputerPlayer computer =new ComputerPlayer();
		HumanPlayer human = new HumanPlayer();
		boolean humanhit=false;
		boolean computerhit=false;
		boolean humansunk=false;
		boolean computersunk =false;
		initialize(computer, human); //Place all 10 ships and print the board
		
		
		Scanner scanner = new Scanner(System.in);
		while(!human.isGameOver() && !computer.isGameOver()){
			//initialize(computer, human); //Place all 10 ships and print the board
			System.out.println("--------------------------human ocean-------------------------\n");
			human.print();
			System.out.println("--------------------------computer ocean----------------------\n");
			computer.print();
			
			MyLocation humanLocation = (MyLocation) human.getTarget();
			int humanguessRow = humanLocation.getX();
			int humanguessCol = humanLocation.getY();
			MyLocation computerLocation = (MyLocation) computer.getTarget();
			int computerguessRow = computerLocation.getX();
			int computerguessCol = computerLocation.getY();
			
			if(!human.isOccupied(computerguessRow, computerguessCol)||(human.getShipArray()[computerguessRow][computerguessCol].isSunk())){
				computerhit=false;
				computersunk=false;
				System.out.println("miss!");
				human.getShipArray()[computerguessRow][computerguessCol].setHittrue(1);
			}
			else{
				if (human.isOccupied(computerguessRow, computerguessCol)) {
					if (human.getShipArray()[computerguessRow][computerguessCol].shootAt(computerguessRow, computerguessCol)) {
						computerhit=true;
						System.out.println("hit!");
						if (human.getShipArray()[computerguessRow][computerguessCol].isSunk()) {
							computersunk=true;
							//System.out.println("You just sank a "+human.myocean.getShipArray()[computerguessRow][computerguessCol].getShipType());
						}
					}
				}
			}
				computer.setResult(computerhit, computersunk);
				if(computersunk==true){
				System.out.println("You just sank a "+human.getShipArray()[computerguessRow][computerguessCol].getShipType());
				computerhit=false;
				computersunk=false;
				
				}
			
				if(!computer.isOccupied(humanguessRow, humanguessCol)||(computer.getShipArray()[humanguessRow][humanguessCol].isSunk())){
					humanhit=false;
					humansunk=false;
					System.out.println("miss!");
					computer.getShipArray()[humanguessRow][humanguessCol].setHittrue(1);
				}
				else{
					if (computer.isOccupied(humanguessRow, humanguessCol)) {
//						System.out.print(humanguessRow);
//						System.out.println();
//						System.out.print(humanguessCol);
						if (computer.getShipArray()[humanguessRow][humanguessCol].shootAt(humanguessRow, humanguessCol)) {
							humanhit=true;
							System.out.println("hit!");
							if (computer.getShipArray()[humanguessRow][humanguessCol].isSunk()) {
								humansunk=true;
								//System.out.println("You just sank a "+computer.ocean.getShipArray()[humanguessRow][humanguessCol].getShipType());
								
							}
						}
					}
				}
					human.setResult(humanhit, humansunk);
//				    System.out.print(human.getHitCount());
//				    System.out.println();
//				    System.out.print(human.getshipsSunk());
//				    
					if(humansunk==true){
					System.out.println("You just sank a "+computer.getShipArray()[humanguessRow][humanguessCol].getShipType());
					}
					humanhit=false;
					humansunk=false;
		}
		
		if(human.isGameOver()){
			System.out.println("It takes " + computer.getShotsFired()
					+ " shots for human to win the game!");
			return computer;
		}
		else{
			System.out.println("It takes " + human.getShotsFired()
					+ " shots for computer to win the game!");
			return human;
		}
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Player winner =new ComputerPlayer();
		MyGame game = new MyGame();
		winner = game.playGame();

	}

}
