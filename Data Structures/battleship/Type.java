public class Type {

	private String hitState = "";

	public void setHitState(boolean hit) { // SETS HIT OR MISS
		if (hit) {
			hitState = "Hit!";

		} else
			hitState = "Miss";
	}

	public String toString() { // returns hit miss or ~~~~
		if (hitState.equals(""))
			return "~~~~"; // Ocean pattern
		return hitState;
	}

	public boolean getHitState() { // gets hitstate

		return hitState.equals("");
	}

}
