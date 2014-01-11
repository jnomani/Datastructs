import java.util.Scanner;
import java.util.ArrayList;

public class Board {

	private Ship[][] board;
	private ArrayList<Ship> ships = new ArrayList<Ship>();
	private int count;
	private Type[][] hmn;

	public Board(int x, int y) {
		board = new Ship[y][x];
		hmn = new Type[y][x];
		initType();
	}

	public Board() {
		board = new Ship[8][8];
		count = 0;
		hmn = new Type[8][8];
		initType();
	}

	public void populateVert(Scanner sc, int num) { // Populates vertical ships
													// to board.
		int x, y;

		for (int i = 1; i <= num; i++) {
			System.out.println("Ship: " + i);
			do {
				System.out.println("Enter a Coordinate:");
				System.out.print("X:");
				x = sc.nextInt();

				System.out.print("Y:");
				y = sc.nextInt();

			} while (board[y][x] != null);
			int length = sc.nextInt();
			Ship s;
			if (length <= 6) {
				s = new Ship(length, count + 1);
				Coordinate[] c = new Coordinate[length];
				for (int j = 0; j < c.length && y + j < board.length; j++) {
					if (board[y + j][x] == null) {
						c[j] = new Coordinate(x, y + j);
					} else
						break;

				}
				if (c[c.length - 1] != null) {
					for (int j = 0; j < c.length; j++) {
						board[c[j].getY()][c[j].getX()] = s;

					}
					ships.add(s);
					count++;
				} else
					i--;
			}
			// break;
			// Coordinate[] c = new Coordinate[length];

		}

	}

	public void populateHoriz(Scanner sc, int num) { // adds horizontal ships to
														// board
		int x, y;
		for (int i = 1; i <= num; i++) {
			System.out.println("Ship: " + i);
			do {
				System.out.println("Enter a Coordinate:");
				System.out.print("X:");
				x = sc.nextInt();

				System.out.print("Y:");
				y = sc.nextInt();

			} while (board[y][x] != null);
			int length = sc.nextInt();
			Ship s;
			if (length <= 6) {
				s = new Ship(length, count + 1);
				Coordinate[] c = new Coordinate[length];
				for (int j = 0; j < c.length && x + j < board[j].length; j++) {
					if (board[y][x + j] == null) {
						c[j] = new Coordinate(x + j, y);
					} else
						break;

				}
				if (c[c.length - 1] != null) {
					for (int j = 0; j < c.length; j++) {
						board[c[j].getY()][c[j].getX()] = s;

					}
					ships.add(s);
					count++;
				}
			}
			// break;
			// Coordinate[] c = new Coordinate[length];

		}

	}

	private void initType() { // intializes type array
		for (int i = 0; i < hmn.length; i++) {
			for (int j = 0; j < hmn[i].length; j++) {
				hmn[i][j] = new Type();
			}
		}
	}

	public void printBoard(Type[][] cBoard, ArrayList<Ship> sh) { // prints out
																	// the
																	// updated
																	// player
																	// board as
																	// well as
																	// computer
																	// target
																	// board.
		System.out.println("Your Board:     "
				+ "                                          "
				+ "                Computer's Board:");
		System.out.print("Ships Remaining: ");
		System.out.print(ships.size());
		System.out.print("                                   "
				+ "                     ");
		System.out.print("Ships Remaining: ");
		System.out.println(sh.size() + "\n");
		System.out.print("   ");
		for (int i = 0; i < board[0].length; i++) {
			System.out.print("|" + i + " | ");
		}
		System.out.print("                                  ");

		for (int i = 0; i < board[0].length; i++) {
			System.out.print("|" + i + " | ");
		}
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			System.out.print("|" + i + "|");
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] != null) {
					System.out.print(board[i][j] + " ");
				} else
					System.out.print(hmn[i][j] + " ");

			}
			System.out.print("                               ");
			System.out.print("|" + i + "|");
			for (int j = 0; j < cBoard[i].length; j++) {
				System.out.print(cBoard[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void Fire(Coordinate c) { // checks if hit or miss
		int x = c.getX();
		int y = c.getY();

		if (board[y][x] == null) {
			if (hmn[y][x].getHitState())
				hmn[y][x].setHitState(false);

		} else {
			hmn[y][x].setHitState(true);
			board[y][x].addHit();
			if (board[y][x].checkSunk()) {
				for (int i = 0; i < ships.size(); i++) {
					if (ships.get(i).getID().equals(board[y][x].getID())) {
						if (this instanceof ComputerBoard)
							System.out
									.println("\nYou sunk the enemy's ship!\n");
						else
							System.out
									.println("\nOh no! The enemy sunk one of your ships!\n");
						ships.remove(i);
						break;
					}
				}
			}
			board[y][x] = null;
		}
	}

	// getter and setter for subclass
	public boolean checkLoss() {
		return ships.size() == 0;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Ship[][] getBoard() {
		return board;
	}

	public ArrayList<Ship> getShips() {
		return ships;
	}

	public Type[][] getHmn() {
		return hmn;
	}

}
