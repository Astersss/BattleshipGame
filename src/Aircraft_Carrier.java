
public class Aircraft_Carrier extends Ship {

	public Aircraft_Carrier() {
		super();
		setLength(5);
		setHit(5);
	}

	/**
	 * return string "battleship"
	 */
	@Override
	String getShipType(){
		return "aircraft carrier";
	}

}