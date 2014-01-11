
public class Coordinate {
private int x;
private int y;

public Coordinate(int x, int y){ 
	this.x = x;
	this.y = y;
}

public Coordinate(Ship[][] board){ //Generates a random coordinate on a board which is still available.
	do{
		x = (int)(Math.random() * board[0].length);
		y = (int)(Math.random() * board.length);
	}while(board[y][x] != null);
	
	
}

public int getX() { //returns x
	return x;
}

public int getY() { //returns y
	return y;
}


}
