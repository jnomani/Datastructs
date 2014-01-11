public class ComputerBoard extends Board {

	// super constructor calls:
	public ComputerBoard(int x, int y) {
		super(x, y);
	}

	public ComputerBoard() {
		super();
	}

	public void PopulateHoriz() { // randomly populates horizontal ships
		for (int i = 0; i < 2; i++) {
			Coordinate co = new Coordinate(getBoard());
			int x = co.getX();
			int y = co.getY();
			int length = (int) (Math.random() * 4) + 2;
			Ship s;
			if (length <= 6) {
				s = new Ship(length, getCount() + 1);
				Coordinate[] c = new Coordinate[length];
				for (int j = 0; j < c.length && x + j < getBoard()[j].length; j++) {
					if (getBoard()[y][x + j] == null) {
						c[j] = new Coordinate(x + j, y);
					} else
						break;

				}
				if (c[c.length - 1] != null) {
					for (int j = 0; j < c.length; j++) {
						getBoard()[c[j].getY()][c[j].getX()] = s;

					}
					getShips().add(s);
					setCount(getCount() + 1);
				} else
					i--;
			}
		}

	}

	public void populateVert() { // randomly populates vertical ships
		for (int i = 0; i < 2; i++) {
			Coordinate co = new Coordinate(getBoard());
			int x = co.getX();
			int y = co.getY();
			int length = (int) (Math.random() * 4) + 2;
			Ship s;
			if (length <= 6) {
				s = new Ship(length, getCount() + 1);
				Coordinate[] c = new Coordinate[length];
				for (int j = 0; j < c.length && y + j < getBoard()[j].length; j++) {
					if (getBoard()[y + j][x] == null) {
						c[j] = new Coordinate(x, y + j);
					} else
						break;

				}
				if (c[c.length - 1] != null) {
					for (int j = 0; j < c.length; j++) {
						getBoard()[c[j].getY()][c[j].getX()] = s;

					}
					getShips().add(s);
					setCount(getCount() + 1);
				} else
					i--; // if ship not added successfully try try again.
			}
		}

	}
}
