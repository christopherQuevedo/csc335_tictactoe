package model;

import java.awt.Point;
import java.util.Random;

/**
 * This strategy selects the first available move at random.  It is easy to beat
 * 
 * @throws IGotNowhereToGoException whenever asked for a move that is impossible to deliver
 * 
 * @author Chris Quevedo
 */
public class RandomAI implements TicTacToeStrategy {

  // Find an open spot while ignoring possible wins and stops (block a guaranteed win)
  @Override
  public Point desiredMove(TicTacToeGame theGame) {
	Random randy = new Random(); 
    int randX = randy.nextInt(3);
    int randY = randy.nextInt(3);
    while(!theGame.available(randX, randY)) {
    	randX = randy.nextInt(3);
    	randY = randy.nextInt(3);
    }
    return new Point(randX, randY);
  }
  
}