
class Battleship extends Ship {

	public Battleship() {
		super();
		setLength(4);
		setHit(4);
	}

	/**
	 * return string "battleship"
	 */
	@Override
	String getShipType(){
		return "battleship";
	}

}