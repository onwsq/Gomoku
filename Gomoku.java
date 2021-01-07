/**
 * Gomoku class represents the gomoku game
 * 
 * @author Olivia Weng 
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Paint;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;


public class Gomoku extends Application{
  //board of button arrays
  private Button[][] button;
  //primary stage
  private Stage primaryStage = null;
  //game board
  private GridPane board;
  //individual board piece
  private Button piece;
  //end message
  private TextArea endMessage;
  //where end message is displayed
  private BorderPane end;
  //board rows
  private int startRows = 19;
  //board columns
  private int startColumns = 19;
  //value needed to win
  private int winValue = 5;
  //four four rule
  private int fourFour = 4;
  //three three fule
  private int threeThree = 3;
  

    
  //enum to set the directions
  public enum Directions{
    RIGHT, LEFT, DOWN, UP, DR_DIAGONAL, 
      DL_DIAGONAL, UR_DIAGONAL, UL_DIAGONAL 
  }
  
  public enum MainDirections{
    HORIZONTAL, VERTICAL, NEG_DIAGONAL, POS_DIAGONAL
  }
  
  
  /**constructor to set values of the board and rules
    */
  public void setGomoku(int rows, int columns, int winValue, int fourFour, int threeThree){
    this.startRows = rows;
    this.startColumns = columns;
    this.winValue = winValue;
    this.fourFour = fourFour;
    this.threeThree = threeThree;
   // createBoard(this.startRows, this.startColumns);
    System.out.println("SET RULES row "+this.startRows+" columns "+this.startColumns+" winValue "+this.winValue+" fourFour "+this.fourFour+" threeThree "+this.threeThree);
  }
  
  
  /**creates the board of the game
    * @param int rows int columns on board
    */
  public void createBoard(int rows, int cols){
    board = new GridPane();
    button = new Button[rows][cols];
    ButtonAction handler = new ButtonAction(this);
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < cols; j++){
        button[i][j] = new Button();
        button[i][j].setMinSize(30,30);
        button[i][j].setPrefSize(30,30);
        button[i][j].setBackground(new Background
                                     (new BackgroundFill
                                        (Color.GREEN, CornerRadii.EMPTY, new Insets(1.0))));
        board.add(button[i][j], j, i);
        button[i][j].setOnAction(handler);
      }
    }
  }
  
    
  /**start method
    * @param the stage that is being used
    */
  public void start(Stage stage){
    this.createBoard(this.startRows, this.startColumns);
    Scene scene = new Scene(this.getBoard());
    stage.setTitle("Gomoku");
    stage.setScene(scene);
    stage.show();
  }
  
  /**gets the board that was created
    * @return the GridPane board
    */
  public GridPane getBoard(){
    return board;
  }
  
  /**gets the specific board piece
    * @param int row int column the piece is located at (starting at 0)
    * @return the button at that point
    */
  public Button getButton(int row, int col){
    return button[row][col];
  }
  
  /**gets the number of rows in the board
    * @return int number of rows
    */
  public int getBoardRowLength(){
    return button.length;
  }
  
  /**gets the number of columns in the boar
    * @return int number of columns
    */
  public int getBoardColumnLength(){
    return button[0].length;
  }
  
  /**gets the color of the board piece
    * @param int row int column of the piece
    * @return the paint value of the piece
    */
  public Paint getPieceColor(int row, int col){
    int numFills = getButton(row, col).getBackground().getFills().size();
    //gets list of fills, and then gets last fill list, ie the color of the piece
    return getButton(row, col).getBackground().getFills().get(numFills-1).getFill();
  }
  
  /**checks whether or not the board piece has a checker on it
    * @param the GridPane board being played, int row and int col of the board piece, Paint color to check
    * @return true or false if it is filled
    */
  public boolean isFilled(GridPane board, int row, int col, Paint color){
    int numFills = getButton(row, col).getBackground().getFills().size();
    //more than one background, meaning that there is something on the spot
    if(numFills > 1){
      if(getButton(row, col).getBackground().getFills().get(numFills-1).getFill() == color){
        return true;
      }
    }
    return false;
  }
  
  public int buttonRow(Button b){
    int row = -1;
    for(int i = 0; i < getBoardRowLength(); i++){
      for(int j = 0; j < getBoardColumnLength(); j++){
        if(b == getButton(i, j)){
          row = i;
          return row;
        }
      }
    }
    return row;
  }
  
    
  public int buttonColumn(Button b){
    int col = -1;
    for(int i = 0; i < getBoardRowLength(); i++){
      for(int j = 0; j < getBoardColumnLength(); j++){
        if(b == getButton(i, j)){
          col = j;
          return col;
        }
      }
    }
    return col;
  }
  
  
  /**counts the number of checkers of the same color in a row
    * @param GridPane board being played, int row and int col location of the piece, direction to check
    * @return int number of pieces in a row, including starting point
    */
  public int numberInLine(GridPane board, int row, int col, Directions dir){
    int countPieces = 1;
    int move = 1;
    if(row >= getBoardRowLength() || col >= getBoardColumnLength() || getPieceColor(row, col) == Color.GREEN){
      return 0;
    }
    switch(dir){
      case RIGHT:
        while((col + move) < getBoardColumnLength()
                && getPieceColor(row, col + (move - 1)) == getPieceColor(row, col + move)){
        countPieces++;
        move++;
      }
        
        break;
      case LEFT:
        while(col - (move - 1) > 0
                && getPieceColor(row, col - (move - 1)) == getPieceColor(row, col - move)){
        countPieces++;
        move++;
      }
        
        break;
      case DOWN:
        while((row + move < getBoardRowLength())
                && getPieceColor(row + (move - 1), col) == getPieceColor(row + move, col)){
        countPieces++;
        move++;
      }
        break;
      case UP:
        while(row - (move - 1) > 0
                && getPieceColor(row - (move - 1), col) == getPieceColor(row - move, col) && row > 0){
        countPieces++;
        move++;
      }
        break;
      case DR_DIAGONAL:
        while((row + move < getBoardRowLength()) && (col + move < getBoardColumnLength())
                && getPieceColor(row + (move - 1), col + (move - 1)) == getPieceColor(row + move, col + move)){
        countPieces++;
        move++;
      }
        break;
      case DL_DIAGONAL:
        while((row + move < getBoardRowLength()) && col - (move - 1) > 0
                && getPieceColor(row + (move - 1), col - (move - 1)) == getPieceColor(row + move, col - move)){
        countPieces++;
        move++;
      }
        break;
      case UR_DIAGONAL:
        while((row - (move - 1) > 0) && ((col + move) < getBoardColumnLength())
                && getPieceColor(row - (move - 1), col + (move - 1)) == getPieceColor(row - move, col + move) && row > 0){
        countPieces++;
        move++;
      }
        break;
      case UL_DIAGONAL:
        while((row - (move - 1) > 0) && (col - (move - 1) > 0)
                && getPieceColor(row - (move - 1), col - (move - 1)) == getPieceColor(row - move, col - move) && row > 0){
        countPieces++;
        move++;
      }
        break;
      default:
        break;  
    }
    return countPieces;
  }
  
  /**checks whether or not the next square is open
    * @param GridPane board being played, int row and int col location of the check, direction to check
    * @return true if it is open, false if it not
    */
  public boolean isOpen(GridPane board, int row, int col, Directions dir){
    boolean isOpen = false;
    int rightMoves = numberInLine(getBoard(), row, col, Gomoku.Directions.RIGHT);
    int leftMoves = numberInLine(getBoard(), row, col, Gomoku.Directions.LEFT);
    int downMoves = numberInLine(getBoard(), row, col, Gomoku.Directions.DOWN);
    int upMoves = numberInLine(getBoard(), row, col, Gomoku.Directions.UP);
    int upRightMoves = numberInLine(getBoard(), row, col, Gomoku.Directions.UR_DIAGONAL);
    int upLeftMoves = numberInLine(getBoard(), row, col, Gomoku.Directions.UL_DIAGONAL);
    int downRightMoves = numberInLine(getBoard(), row, col, Gomoku.Directions.DR_DIAGONAL);
    int downLeftMoves = numberInLine(getBoard(), row, col, Gomoku.Directions.DL_DIAGONAL);
    
    if(row >= getBoardRowLength() && col >= getBoardColumnLength()){
      return isOpen;
    }
    switch(dir){
      case RIGHT:
        if((col + rightMoves) < getBoardColumnLength()){
        if(getButton(row, col + rightMoves).getBackground().getFills().size() 
             == 1){
          isOpen = true;
        }
      }
        break;
      case LEFT:
        if((col - leftMoves) >= 0 && (col > 0)){
        if(getButton(row, col - leftMoves).getBackground().getFills().size() 
             == 1){
          isOpen = true;
        }
      }
        break;
      case DOWN:
        if((row + downMoves) < getBoardRowLength()){
        if(getButton(row + downMoves, col).getBackground().getFills().size() 
             == 1){
          isOpen = true;
        }
      }
        break;
      case UP:
        if((row - upMoves) >= 0 && (row > 0)){
        if(getButton(row - upMoves, col).getBackground().getFills().size() 
             == 1){
          isOpen = true;
        }
      }
        break;
      case DR_DIAGONAL:
        if((row + downRightMoves) < getBoardRowLength() && col + downRightMoves < getBoardColumnLength()){
        if(getButton(row + downRightMoves, col + downRightMoves).getBackground().getFills().size() 
             == 1){
          isOpen = true;
        }
      }
        break;
      case DL_DIAGONAL:
        if((row + downLeftMoves) < getBoardRowLength() && col - downLeftMoves >= 0){
        if(getButton(row + downLeftMoves, col - downLeftMoves).getBackground().getFills().size() 
             == 1){
          isOpen = true;
        }
      }
        break;
      case UR_DIAGONAL:
        if((row - upRightMoves) < getBoardRowLength() && col + upRightMoves < getBoardColumnLength() 
             && (row > 0)){
        if(getButton(row - upRightMoves, col + upRightMoves).getBackground().getFills().size() 
             == 1){
          isOpen = true;
        }
      }
        break;
      case UL_DIAGONAL:
        if((row - upLeftMoves) >= 0 && col - upLeftMoves >=0){
        if(getButton(row - upLeftMoves, col - upLeftMoves).getBackground().getFills().size() 
             == 1){
          isOpen = true;
        }
      }
        break;
      default:
        break;
    }
    return isOpen;
  }
  
  /**checks whether if there are exactly 5(or another number) in a row somewhere on the board
    * @return true if there are exactly 5 in a row somewhere, false if not
    */
  public boolean overline(){
    System.out.println("OVERLINE WIN VALUE" +winValue);
    boolean fiveInARow = false;
    for(int i = 0; i < getBoardRowLength(); i++){
      for(int j = 0; j < getBoardColumnLength(); j++){
        if(((numberInLine(getBoard(), i, j, Gomoku.Directions.RIGHT)) 
              + (numberInLine(getBoard(), i, j, Gomoku.Directions.LEFT))) - 1 == winValue){
          fiveInARow = true;
        }
        else if(((numberInLine(getBoard(), i, j, Gomoku.Directions.UP))
                   + (numberInLine(getBoard(), i, j, Gomoku.Directions.DOWN))) - 1 == winValue){
          fiveInARow = true;
        }
        else if(((numberInLine(getBoard(), i, j, Gomoku.Directions.DR_DIAGONAL)) 
                   + (numberInLine(getBoard(), i, j, Gomoku.Directions.UL_DIAGONAL))) - 1 == winValue){
          fiveInARow = true;
        }
        else if((numberInLine(getBoard(), i, j, Gomoku.Directions.DL_DIAGONAL)
                   + (numberInLine(getBoard(), i, j, Gomoku.Directions.UR_DIAGONAL))) - 1 == winValue){
          fiveInARow = true;
        }   
      }
    }
    return fiveInARow;
  }
  
  
  /**checks whether or not the move will create two or moregroups of four in a row
    * @return true if the move will do so, false if not
    **/
  public boolean fourFour(Button piece, Paint color){
    boolean exists = false;
    int fourInARow = 0;
    int row = buttonRow(piece);
    int col = buttonColumn(piece);
    System.out.println("row "+row+" column "+col);
    
    Paint ogColor = getPieceColor(row, col);
    
    fillPiece(piece, color);
    
    
    for(MainDirections dir: MainDirections.values()){
      switch(dir){
        case HORIZONTAL:
          if(((numberInLine(getBoard(), row, col, Gomoku.Directions.RIGHT)) 
                + (numberInLine(getBoard(), row, col, Gomoku.Directions.LEFT))) - 1 == fourFour){
          fourInARow++;
          System.out.println("horizontal four in a row "+fourInARow);
        }
          break;
        case VERTICAL:
          if(((numberInLine(getBoard(), row, col, Gomoku.Directions.UP))
                + (numberInLine(getBoard(), row, col, Gomoku.Directions.DOWN))) - 1 == fourFour){
          fourInARow++;
          System.out.println("vertical four in a row"+fourInARow);
        }
          break;
        case NEG_DIAGONAL:
          if(((numberInLine(getBoard(), row, col, Gomoku.Directions.DR_DIAGONAL)) 
                + (numberInLine(getBoard(), row, col, Gomoku.Directions.UL_DIAGONAL))) - 1 == fourFour){
          fourInARow++;
          System.out.println("negative diag four in a row"+fourInARow);
        }
          break;
        case POS_DIAGONAL:
          if((numberInLine(getBoard(), row, col, Gomoku.Directions.DL_DIAGONAL)
                + (numberInLine(getBoard(), row, col, Gomoku.Directions.UR_DIAGONAL))) - 1 == fourFour){
          fourInARow++;
          System.out.println("positive diag four in a row"+fourInARow);
        }
          break;
        default:
          break;
      }
    }
      if(ogColor == Color.GREEN){
        getButton(row, col).setBackground(new Background
                                            (new BackgroundFill
                                               (Color.GREEN, CornerRadii.EMPTY, new Insets(1.0))));
      }
      else if (ogColor == Color.WHITE){
        getButton(row, col).setBackground(new Background
                                            (new BackgroundFill
                                               (Color.GREEN, CornerRadii.EMPTY, new Insets(1.0)),
                                             new BackgroundFill
                                               (Color.WHITE, new CornerRadii(100, true), new Insets(1.0))));
      }
      else if (ogColor == Color.BLACK){
        getButton(row, col).setBackground(new Background
                                            (new BackgroundFill
                                               (Color.GREEN, CornerRadii.EMPTY, new Insets(1.0)),
                                             new BackgroundFill
                                               (Color.BLACK, new CornerRadii(100, true), new Insets(1.0))));
      }
      if(fourInARow > 1){
        exists = true;
      }
    return exists;
  }
  
  
  
  
  
   public boolean threeThree(Button piece, Paint color){
    boolean exists = false;
    int threeInARow = 0;
    int row = buttonRow(piece);
    int col = buttonColumn(piece);
    int upMoves;
    int downMoves;
    int rightMoves = (numberInLine(getBoard(), row, col, Gomoku.Directions.RIGHT)) - 1;
    int leftMoves = (numberInLine(getBoard(), row, col, Gomoku.Directions.LEFT)) - 1;
     
    
    System.out.println("row "+row+" column "+col);
    
    Paint ogColor = getPieceColor(row, col);
    
    fillPiece(piece, color);
    
    for(MainDirections dir: MainDirections.values()){
      switch(dir){
        case HORIZONTAL:
          //filling the piece creates a three in a row
          if(((numberInLine(getBoard(), row, col, Gomoku.Directions.RIGHT)) 
                + (numberInLine(getBoard(), row, col, Gomoku.Directions.LEFT))) - 1 == threeThree){
          //if either boundary is closed, or a different colored piece is there
          if(isOpen(getBoard(), row, col, Gomoku.Directions.RIGHT) == true
            && isOpen(getBoard(), row, col, Gomoku.Directions.LEFT) == true){
            threeInARow++;
            System.out.println("horiztonal three in row"+threeInARow);
          } 
        }
          break;
        case VERTICAL:
          //filling the piece creates a three in a row
          if(((numberInLine(getBoard(), row, col, Gomoku.Directions.UP)) 
                + (numberInLine(getBoard(), row, col, Gomoku.Directions.DOWN))) - 1 == threeThree){
            //if either boundary is closed
            if(isOpen(getBoard(), row, col, Gomoku.Directions.UP) == true
                 && isOpen(getBoard(), row, col, Gomoku.Directions.DOWN) == true){
              threeInARow++;
              System.out.println("vertical three in row "+threeInARow);
            } 
          }
          break;
        case NEG_DIAGONAL:
          if(((numberInLine(getBoard(), row, col, Gomoku.Directions.DR_DIAGONAL)) 
                + (numberInLine(getBoard(), row, col, Gomoku.Directions.UL_DIAGONAL))) - 1 == threeThree){
            //if either boundary is closed
            if(isOpen(getBoard(), row, col, Gomoku.Directions.DR_DIAGONAL) == true
                 && isOpen(getBoard(), row, col, Gomoku.Directions.UL_DIAGONAL) == true){
              threeInARow++;
              System.out.println("neg diag three in row "+threeInARow);
            } 
          }
          break;
        case POS_DIAGONAL:
          if((numberInLine(getBoard(), row, col, Gomoku.Directions.UR_DIAGONAL)
                + (numberInLine(getBoard(), row, col, Gomoku.Directions.DL_DIAGONAL))) - 1 == threeThree){
          threeInARow++;
          System.out.println("positive diag three in a row"+threeInARow);
        }
          break;
        default:
          break;
      }
    }
    if(threeInARow > 1){
      exists = true;
    }
      if(ogColor == Color.GREEN){
        getButton(row, col).setBackground(new Background
                                            (new BackgroundFill
                                               (Color.GREEN, CornerRadii.EMPTY, new Insets(1.0))));
      }
      else if (ogColor == Color.WHITE){
        getButton(row, col).setBackground(new Background
                                            (new BackgroundFill
                                               (Color.GREEN, CornerRadii.EMPTY, new Insets(1.0)),
                                             new BackgroundFill
                                               (Color.WHITE, new CornerRadii(100, true), new Insets(1.0))));
      }
      else if (ogColor == Color.BLACK){
        getButton(row, col).setBackground(new Background
                                            (new BackgroundFill
                                               (Color.GREEN, CornerRadii.EMPTY, new Insets(1.0)),
                                             new BackgroundFill
                                               (Color.BLACK, new CornerRadii(100, true), new Insets(1.0))));
      }
    return exists;
  }
   

    

  

  /**main method
    * @param the string of arguments for the main method
    */
  public static void main(String[] args){
    int rows = 19;
    int columns = 19;
    int winValue = 5;
    int fourFour = 4;
    int threeThree = 3;
    
    if(args.length > 1){
      rows = Integer.parseInt(args[0]);
      columns = Integer.parseInt(args[1]);
      winValue = Integer.parseInt(args[2]);
      fourFour = winValue - 1;
      threeThree = winValue - 2;
    }
    else if (args.length == 1){
      winValue = Integer.parseInt(args[0]);
      fourFour = winValue - 1;
      threeThree = winValue - 2;
    }
    System.out.println("row "+rows+" columns "+columns+" winValue "+winValue+" fourFour "+fourFour+" threeThree "+threeThree);
    Gomoku game = new Gomoku();
    game.setGomoku(rows, columns, winValue, fourFour, threeThree);
    game.launch(args);
  }
  

  
  
  /**fills the piece with a checker, with rules
    * @param the Button b to fill, Paint color of the checker
    */
  public void fillPiece(Button b, Paint color){
    b.setBackground(new Background
                      (new BackgroundFill
                         (Color.GREEN, CornerRadii.EMPTY, new Insets(1.0)),
                       new BackgroundFill
                         (color, new CornerRadii(100, true), new Insets(1.0))));
  }
  
  
  /**event handler class
    */
  private class ButtonAction implements EventHandler<ActionEvent>{
    private Gomoku game;
    //constructor to set the board
    public ButtonAction(Gomoku game){
      this.game = game;
    }
    int count = 1;
    public void handle(ActionEvent e) {
      Button b = (Button) e.getSource(); // this points to what b points to!
      if(b.getBackground().getFills().size() == 1
           && overline() != true
           && fourFour(b, Color.WHITE) != true
           && threeThree(b, Color.WHITE) != true
           && count%2 ==0){
        game.fillPiece(b, Color.WHITE);
        count++;
      }
      else if(b.getBackground().getFills().size() == 1
                && overline() != true
                && fourFour(b, Color.BLACK) != true
                && threeThree(b, Color.BLACK) != true
                && count%2 != 0){
        game.fillPiece(b, Color.BLACK);
        count++;
      }
    }
  }
}

