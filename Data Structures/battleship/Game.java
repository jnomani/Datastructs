import java.util.Scanner;

public class Game {

	public static void main(String[] r) {

		try {
			Scanner sc = new Scanner(System.in);
			do {

				Board pBoard = new Board();
				ComputerBoard cBoard = new ComputerBoard();
				// inital variables and initializations:
				pBoard.printBoard(cBoard.getHmn(), cBoard.getShips());
				System.out.println("\nHorizontal Ships:");
				pBoard.populateHoriz(sc, pBoard.getBoard().length / 4);
				cBoard.PopulateHoriz();
				pBoard.printBoard(cBoard.getHmn(), cBoard.getShips());
				System.out.println("\nVertical Ships:");
				pBoard.populateVert(sc, pBoard.getBoard().length / 4);
				cBoard.populateVert();
				pBoard.printBoard(cBoard.getHmn(), cBoard.getShips());

				while (pBoard.checkLoss() == false
						&& cBoard.checkLoss() == false) { // Main game loop.
															// Take turn firing.
					System.out.print("X: ");
					int x = sc.nextInt();
					System.out.print("Y: ");
					int y = sc.nextInt();
					cBoard.Fire(new Coordinate(x, y));
					pBoard.Fire(new Coordinate((int) (Math.random()
							* pBoard.getBoard()[0].length - 1), (int) (Math
							.random() * pBoard.getBoard().length - 1)));
					pBoard.printBoard(cBoard.getHmn(), cBoard.getShips());
				}
				if (pBoard.getShips().size() > cBoard.getShips().size())
					System.out.println("Congrats you won... Want a cookie?");
				else
					System.out
							.println("How do you lose to a computer with no logic behind its attack?");
				System.out.println("Play Again?");
			} while (sc.nextLine().equalsIgnoreCase("y")
					|| sc.nextLine().equalsIgnoreCase("yes"));
		} catch (Exception e) {
			System.out.println("No no, one must not do that.");
			main(null);
		}
	}
}
