package view;

import java.util.Observable;

import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.TicTacToeGame;

/**This creates another view of the game where a move is inputted by reading from 
 * two textfields.  A button executes the move and clears the textfields.  The game
 * is written in text format near the lower part of scene.
 * 
 * 
 * @author Christopher Quevedo
 *
 */

public class TextAreaView extends BorderPane implements Observer {
   
  private TicTacToeGame theGame;
  private Button execute;
  private Label rowLabel;
  private Label colLabel;
  private TextField rowField;
  private TextField colField;
  BorderPane textPane;

  public TextAreaView(TicTacToeGame TicTacToeGame) {
    theGame = TicTacToeGame;
    initializePane();
  }

  private void initializePane() {
    Label label = new Label("TextAreaView");
    label.setFont(new Font("serif", 24));
    setCenter(label);
    //////////////////
    GridPane pane = new GridPane();
    pane.setHgap(10);
    pane.setVgap(10);
    execute = new Button("Make move");
    rowLabel = new Label("row");
    colLabel = new Label("column");
    rowField = new TextField();
    //rowField.setPrefColumnCount(1);
    colField = new TextField();
    //colField.setPrefColumnCount(1);
    pane.add(rowField, 1, 1);
    pane.add(colField, 1, 2);
    pane.add(rowLabel, 2, 1);
    pane.add(colLabel, 2, 2);
    pane.add(execute,1,4);
    setCenter(pane);
    
    textPane = new BorderPane();
    Text text = new Text(theGame.toString());
    text.setFont(Font.font("Courier", FontWeight.NORMAL, 50));
    textPane.setCenter(text);
    this.setBottom(textPane);
    
    ButtonListener handler = new ButtonListener();
    execute.setOnAction(handler);
    
  }

  @Override
  public void update(Observable o, Object arg) {
    // TODO Auto-generated method stub
	theGame = (TicTacToeGame) o;
    System.out.println("\nIn TextAreaView.update() \n" + o);
    updateText();
    if (theGame.didWin('X')) {
        execute.setText("X wins");
    }
    else if (theGame.didWin('O')) {
    	execute.setText("O wins");
    }
    else if (theGame.tied()) {
        execute.setText("Tie");
    }
    else {
    	execute.setText("Make move");
    }
  }
  
  //After every move, the game board will be written again.
  public void updateText() {
	  	Text text = new Text(theGame.toString());
	  	text.setFont(Font.font("Courier", FontWeight.NORMAL, 50));
	  	textPane.setCenter(text);
	    this.setBottom(textPane);
  }
  
  //Executes a button push.  
  private class ButtonListener implements EventHandler<ActionEvent> {

	    @Override
	    public void handle(ActionEvent arg0) {
	    	if(theGame.stillRunning()) {
	    		int row = 0;
	    		int col = 0;
	    		try {
	    			row = Integer.parseInt(rowField.getText());
	    			col = Integer.parseInt(colField.getText());
	    		}
	    		catch(NumberFormatException e) {
	    			rowField.setText("");
	    			colField.setText("");
	    			execute.setText("Invalid choice");
	    			return;
	    		}
	    		rowField.setText("");
	    		colField.setText("");
	    		if(row > 2 || row < 0 || col > 2 || col < 0) {
	    			execute.setText("Invalid choice");
	    			return;
	    		}
	    		if(theGame.getTicTacToeBoard()[row][col] != '_') {
	    			execute.setText("Invalid choice");
	    			return;
	    		}
	    	
	    		if (theGame.stillRunning() && theGame.getTicTacToeBoard()[row][col] == '_') {
	    			// Human user makes a move and the ComputerPlayer makes a move
	    			theGame.humanMove(row, col, false);
	    			if(theGame.stillRunning()) {
	    				execute.setText("Make move");
	    				updateText();
	    			}
	    		} 
	    		else {
	    			return;
	    		}
	    	}
	    }
  }
  
}