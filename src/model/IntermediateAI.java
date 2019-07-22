package model;

import java.awt.Point;
import java.util.Random;

/**
 * This TTT strategy tries to prevent the opponent from winning by checking
 * for a space where the opponent is about to win. If none found, it randomly
 * picks a place to win, which an sometimes be a win even if not really trying.
 * 
 * @author Chris Quevedo
 */
public class IntermediateAI implements TicTacToeStrategy {

	@Override
	// Precondition: During testing the AI is associated with the 'O', the odd number move.
  	public Point desiredMove(TicTacToeGame theGame) {
		// TODO: Return a Point that would win, if not possible to block, 
		// if not possible to block return an available Point of your own choosing 
		
		char[][] board = theGame.getTicTacToeBoard();
		Point dPoint;
	    //check for win
		dPoint = winOrBlock(board,'O');
		if(dPoint != null) {
			System.out.println("winning");
			return dPoint;
		}
		//check for block
		dPoint = winOrBlock(board,'X');
		if(dPoint != null) {
			System.out.println("blocking");
			return dPoint;
		}
		//otherwise pick random loc
		Random randy = new Random(); 
	    int randX = randy.nextInt(3);
	    int randY = randy.nextInt(3);
	    while(!theGame.available(randX, randY)) {
	    	randX = randy.nextInt(3);
	    	randY = randy.nextInt(3);
	    }
	    System.out.println("random");
	    return new Point(randX, randY);
	}

	
	//Finds a location on the board where a row, col, or diagonal have two
	// char match and need just one more to make three.  Thus this static method
	// find a place to block as well as win.
	private static Point winOrBlock(char[][] board, char match) {
		int rd;
		int cd;
		if(board[0][0] == match) {
			if(board[0][1] == match) {
				rd = 0;
				cd = 2;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
			if(board[0][2] == match) {
				rd = 0;
				cd = 1;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
			if(board[1][0] == match) {
				rd = 2;
				cd = 0;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
			if(board[1][1] == match) {
				rd = 2;
				cd = 2;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
			if(board[2][0] == match) {
				rd = 1;
				cd = 0;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
			if(board[2][2] == match) {
				rd = 1;
				cd = 1;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
		}
		if(board[0][1] == match) {
			if(board[0][2] == match) {
				rd = 0;
				cd = 0;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
			if(board[1][1] == match) {
				rd = 2;
				cd = 1;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
			if(board[2][1] == match) {
				rd = 1;
				cd = 1;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
		}
		if(board[0][2] == match) {
			if(board[1][1] == match) {
				rd = 2;
				cd = 0;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
			if(board[1][2] == match) {
				rd = 2;
				cd = 2;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
			if(board[2][0] == match) {
				rd = 1;
				cd = 1;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
			if(board[2][2] == match) {
				rd = 1;
				cd = 2;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
		}
		if(board[1][0] == match) {
			if(board[1][1] == match) {
				rd = 1;
				cd = 2;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
			if(board[1][2] == match) {
				rd = 1;
				cd = 1;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
			if(board[2][0] == match) {
				rd = 0;
				cd = 0;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
		}
		if(board[1][1] == match) {
			if(board[1][2] == match) {
				rd = 1;
				cd = 0;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
			if(board[2][0] == match) {
				rd = 0;
				cd = 2;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
			if(board[2][1] == match) {
				rd = 0;
				cd = 1;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
			if(board[2][2] == match) {
				rd = 0;
				cd = 0;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
		}
		if(board[1][2] == match) {
			if(board[2][2] == match) {
				rd = 0;
				cd = 2;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
		}
		if(board[2][0] == match) {
			if(board[2][1] == match) {
				rd = 2;
				cd = 2;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
			if(board[2][2] == match) {
				rd = 2;
				cd = 1;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
		}
		if(board[2][1] == match) {
			if(board[2][2] == match) {
				rd = 2;
				cd = 0;
				if(board[rd][cd] == '_') {
					return new Point(rd,cd);
				}
			}
		}
		return null;
	}
}