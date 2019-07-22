package view;

import java.util.Observable;

import java.util.Observer;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import model.TicTacToeGame;
//Images are 32 by 32 pixels

public class DrawingView extends BorderPane implements Observer {

	private TicTacToeGame theGame;
	private Canvas canvas;
	private GraphicsContext gc;
	private Label message;
	private Image ex;
	private Image oh;
	
	public DrawingView(TicTacToeGame tttGame) {
		theGame = tttGame;
		message = new Label("Make move");
		canvas = new Canvas(254, 254);
		this.setBottom(canvas);
		this.setCenter(message);
		drawBlankBoard();
		//ex = new Image("file:///C:/cygwin64/home/Christopher/csc335/assignment2/ttt-patterns-cquevedo61/images/x.png", false);
		oh = new Image("o.png", false);
		ex = new Image("x.png",false);
		canvas.setOnMouseClicked(new MouseclickHandler());
		
	}
	
	//Draw a blank grid
	public void drawBlankBoard() {
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, 254, 254);
		gc.strokeRect(27, 27, 200, 200);
		gc.strokeLine(93.67, 27, 93.67, 227);
		gc.strokeLine(160.33, 27, 160.33, 227);
		gc.strokeLine(27, 93.67, 227, 93.67);
		gc.strokeLine(27, 160.33, 227, 160.33);
		
	}
	
	@Override
	public void update(Observable observable, Object arg1) {
		// TODO Auto-generated method stub
		theGame = (TicTacToeGame) observable;
		updateDrawing();
	    if (theGame.didWin('X'))
	      message.setText("X wins");
	    else if (theGame.didWin('O'))
	      message.setText("O wins");
	    else if (theGame.tied())
	      message.setText("Tie");
	    else
	      message.setText("Make move");
	}
	
	//update drawing by making a blank canvas then drawing the grid then drawing
	//any exs and ohs that the game has
	public void updateDrawing() {
		
		drawBlankBoard();
		
		char[][] temp = theGame.getTicTacToeBoard();
	    for (int r = 0; r < 3; r++) {
	      for (int c = 0; c < 3; c++) {
	        if(temp[r][c] == 'X') {
	        	drawAnImage(ex, r, c);
	        }
	        else if(temp[r][c] == 'O') {
	        	drawAnImage(oh, r, c);
	        }
	      }
	    }
	}
	
	//Will draw an Image letter at the specified row or col
	public void drawAnImage(Image letter, int row, int col) {
		
		if(row == 0 && col == 0) {
			gc.drawImage(letter, 44.35, 44.35);
		}
		else if(row == 0 && col == 1) {
			gc.drawImage(letter, 111, 44.35);
		}
		else if(row == 0 && col == 2) {
			gc.drawImage(letter, 177.65, 44.35);
		}
		else if(row == 1 && col == 0) {
			gc.drawImage(letter, 44.35, 111);
		}
		else if(row == 1 && col == 1) {
			gc.drawImage(letter, 111, 111);
		}
		else if(row == 1 && col == 2) {
			gc.drawImage(letter, 177.65, 111);
		}
		else if(row == 2 && col == 0) {
			gc.drawImage(letter, 44.35, 177.65);
		}
		else if(row == 2 && col == 1) {
			gc.drawImage(letter, 111, 177.65);
		}
		else if(row == 2 && col == 2) {
			gc.drawImage(letter, 177.65, 177.65);
		}
	}
	
	/*1 2 3
	  4 5 6
      7 8 9	
      
      Depending on where mouse is clicked, program will try to draw an X in one of the 
      9 squares as indicated above.
	*/
	private class MouseclickHandler implements EventHandler<MouseEvent> {

	    @Override
	    public void handle(MouseEvent event) {
	      double x = event.getX();      
	      double y = event.getY();   
	      int row = -1;
	      int col = -1;
	      if(y >= 27 && y <= 93.7) {
	    	  if(x >= 27 && x <= 93.7) {
	    		  //square 1
	    		  row = 0;
	    		  col = 0;
	    	  }
	    	  else if(x > 93.7 && x <= 160.3) {
	    		  //square 2
	    		  row = 0;
	    		  col = 1;
	    	  }
	    	  else if(x > 160.3 && x <= 227) {
	    		  //square 3
	    		  row = 0;
	    		  col = 2;
	    	  }
	      }
	      else if(y > 93.7 && y <= 160.3) {
	    	  if(x >= 27 && x <= 93.7) {
	    		  //square 4
	    		  row = 1;
	    		  col = 0;
	    	  }
	    	  else if(x > 93.7 && x <= 160.3) {
	    		  //square 5
	    		  row = 1;
	    		  col = 1;
	    	  }
	    	  else if(x > 160.3 && x <= 227) {
	    		  //square 6
	    		  row = 1;
	    		  col = 2;
	    	  }
	      }
	      else if(y > 160.3 && y <= 227) {
	    	  if(x >= 27 && x <= 93.7) {
	    		  //square 7
	    		  row = 2;
	    		  col = 0;
	    	  }
	    	  else if(x > 93.7 && x <= 160.3) {
	    		  //square 8
	    		  row = 2;
	    		  col = 1;
	    	  }
	    	  else if(x > 160.3 && x <= 227) {
	    		  //square 9
	    		  row = 2;
	    		  col = 2;
	    	  }
	      }
	      
	      if(col != -1) {
	    	  if (theGame.stillRunning() && theGame.getTicTacToeBoard()[row][col] == '_') {
	    		  // Human user makes a move and the ComputerPlayer makes a move
	    		  theGame.humanMove(row, col, false);
	    		  updateDrawing();
	    	  } 
	    	  else {
	    		  return; // The clicked button was already chosen
	    	  }
	      }
	      
	    }

	  }
	

}
