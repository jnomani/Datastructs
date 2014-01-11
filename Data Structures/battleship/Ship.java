public class Ship {

	private int length;
	private int hits = 0;
	private String ID = "Sh#"; // Ship ID

	public Ship(int length, int ID) {

		this.length = length;
		this.ID += ID;
	}

	public boolean checkSunk() { // Checks if ships is sunk
		return hits >= length;
	}

	public void addHit() { // add one to hit count
		hits++;
	}

	public String toString() { // prints ship

		return ID;
	}

	public String getID() { // gets ID
		return ID;
	}

}
