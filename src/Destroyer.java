
class Destroyer extends Ship{

	public Destroyer() {
		super();
		setLength(2);
		setHit(2);	
	}

/**
 * return string "destroyer"	
 */
@Override
String getShipType(){
	return "destroyer";
}
}