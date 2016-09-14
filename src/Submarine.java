
class Submarine extends Ship {

	public Submarine() {
		super();
		setLength(3);
		setHit(3);
	}
	
	@Override
	String getShipType(){
		return "submarine";
	}

}