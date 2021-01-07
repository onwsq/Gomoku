import org.junit.*;
import static org.junit.Assert.*;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;


public class gomokuTester{
  
  @Test
  public void testcreateBoardandIsOpen(){
    Gomoku game = new Gomoku();
    game.createBoard(6,6);
    
    game.fillPiece(game.getButton(0,0), Color.BLACK);
    game.fillPiece(game.getButton(0,5), Color.BLACK);
    game.fillPiece(game.getButton(1,0), Color.BLACK);
    game.fillPiece(game.getButton(1,1), Color.BLACK);
    game.fillPiece(game.getButton(1,2), Color.BLACK);
    game.fillPiece(game.getButton(1,4), Color.BLACK);
    game.fillPiece(game.getButton(2,3), Color.BLACK);
    game.fillPiece(game.getButton(3,2), Color.BLACK);
    game.fillPiece(game.getButton(3,4), Color.BLACK);
    game.fillPiece(game.getButton(4,0), Color.BLACK);
    game.fillPiece(game.getButton(4,1), Color.BLACK);
    game.fillPiece(game.getButton(4,4), Color.BLACK);
    game.fillPiece(game.getButton(4,5), Color.BLACK);
    game.fillPiece(game.getButton(5,0), Color.BLACK);
    game.fillPiece(game.getButton(5,5), Color.BLACK);
    game.fillPiece(game.getButton(3,3), Color.BLACK);
    
    assertTrue("says spot is not empty when it is", game.isOpen(game.getBoard(), 1, 1, Gomoku.Directions.RIGHT)); //test is empty right direction
    assertTrue("says spot is not empty when it is", game.isOpen(game.getBoard(), 4, 0, Gomoku.Directions.RIGHT));  
    assertFalse("says spot is empty when it is not", game.isOpen(game.getBoard(), 4, 4, Gomoku.Directions.RIGHT)); //test filled right direction
    
    assertTrue("says spot is not empty when it is", game.isOpen(game.getBoard(), 2, 3, Gomoku.Directions.LEFT)); //test is empty left direction
    assertTrue("says spot is not empty when it is", game.isOpen(game.getBoard(), 3, 4, Gomoku.Directions.LEFT));  
    assertFalse("says spot is empty when it is not", game.isOpen(game.getBoard(), 4, 1, Gomoku.Directions.LEFT)); //test filled left direction
    
    assertTrue("says spot is not empty when it is", game.isOpen(game.getBoard(), 4, 4, Gomoku.Directions.UP)); //test is empty up direction
    assertTrue("says spot is not empty when it is", game.isOpen(game.getBoard(), 4, 1, Gomoku.Directions.UP));  
    assertFalse("says spot is empty when it is not", game.isOpen(game.getBoard(), 1, 0, Gomoku.Directions.UP)); //test filled up direction
    
    assertTrue("says spot is not empty when it is", game.isOpen(game.getBoard(), 3, 4, Gomoku.Directions.DOWN)); //test is empty down direction
    assertTrue("says spot is not empty when it is", game.isOpen(game.getBoard(), 1, 2, Gomoku.Directions.DOWN));  
    assertFalse("says spot is empty when it is not", game.isOpen(game.getBoard(), 4, 5, Gomoku.Directions.DOWN)); //test filled down direction
    
    assertTrue("says spot is not empty when it is", game.isOpen(game.getBoard(), 3, 4, Gomoku.Directions.UR_DIAGONAL)); //test is empty up right diag direction
    assertFalse("says spot is empty when it is not", game.isOpen(game.getBoard(), 2, 3, Gomoku.Directions.UR_DIAGONAL)); //test filled up right diag direction
    
    assertTrue("says spot is not empty when it is", game.isOpen(game.getBoard(), 2, 3, Gomoku.Directions.UL_DIAGONAL)); //test is empty up left direction
    assertFalse("says spot is empty when it is not", game.isOpen(game.getBoard(), 1, 1, Gomoku.Directions.UL_DIAGONAL)); //test filled up left direction
    
    
    assertTrue("says spot is not empty when it is", game.isOpen(game.getBoard(), 3, 2, Gomoku.Directions.DR_DIAGONAL)); //test is empty down right direction
    assertFalse("says spot is empty when it is not", game.isOpen(game.getBoard(), 1, 2, Gomoku.Directions.DR_DIAGONAL)); //test filled down right direction
    
    assertTrue("says spot is not empty when it is", game.isOpen(game.getBoard(), 1, 2, Gomoku.Directions.DL_DIAGONAL)); //test is empty down left direction  
    assertFalse("says spot is empty when it is not", game.isOpen(game.getBoard(), 3, 2, Gomoku.Directions.DL_DIAGONAL)); //test filled down left direction
    
    assertFalse("says spot is empty when it is not", game.isOpen(game.getBoard(), 10, 10, Gomoku.Directions.UP)); //test out of bounds direction
    
  }
  
  @Test
  public void testGetBoardValues(){
    Gomoku game = new Gomoku();
    game.createBoard(7,7);
    
    assertEquals(game.getBoardRowLength(), 7); //test row length
    assertEquals(game.getBoardColumnLength(), 7); //test column length
    
  }
  
  @Test
  public void testGetPieceColor(){
    Gomoku game = new Gomoku();
    game.createBoard(7,7);
    game.fillPiece(game.getButton(0,0), Color.WHITE);
    
    assertEquals(game.getPieceColor(0,0), Color.WHITE); //test filled piece
    assertEquals(game.getPieceColor(1,1), Color.GREEN); //test unfilled piece
  }
  
  @Test
  public void testfillPieceandIsFilled(){
    Gomoku game = new Gomoku();
    game.createBoard(19,19);
    
    assertFalse("says spot is filled when it is not", game.isFilled(game.getBoard(), 5, 10, Color.BLACK));
    assertFalse("says spot is filled when it is not", game.isFilled(game.getBoard(), 10, 10, Color.WHITE));
    
    game.fillPiece(game.getButton(3,4), Color.BLACK);
    game.fillPiece(game.getButton(4,4), Color.WHITE);
    
    assertTrue("says spot is not filled when it is", game.isFilled(game.getBoard(), 3, 4, Color.BLACK));
    assertFalse("says spot is filled when it is not", game.isFilled(game.getBoard(), 4, 4, Color.BLACK));
    
    assertTrue("says spot is not filled when it is", game.isFilled(game.getBoard(), 4, 4, Color.WHITE));
    assertFalse("says spot is filled when it is not", game.isFilled(game.getBoard(), 10, 4, Color.WHITE));
    
    
  }
  
  @Test
  public void testNumberInLine(){
    Gomoku game = new Gomoku();
    game.createBoard(5,6);
    
    //testing directions for zero in line
    assertEquals("says there are pieces when board is empty", game.numberInLine(game.getBoard(), 4, 3, Gomoku.Directions.RIGHT), 0); //test 0 pieces in a row, right 
    assertEquals("says there are pieces when board is empty", game.numberInLine(game.getBoard(), 4, 3, Gomoku.Directions.LEFT), 0); //test 0 pieces in a row, left
    assertEquals("says there are pieces when board is empty", game.numberInLine(game.getBoard(), 4, 3, Gomoku.Directions.DOWN), 0); //test 0 pieces in a row, down 
    assertEquals("says there are pieces when board is empty", game.numberInLine(game.getBoard(), 4, 3, Gomoku.Directions.UP), 0); //test 0 pieces in a row, up
    
    assertEquals("says there are pieces when board is empty", game.numberInLine(game.getBoard(), 1, 1, Gomoku.Directions.DR_DIAGONAL), 0); //test 0 pieces in a row, down right diag
    assertEquals("says there are pieces when board is empty", game.numberInLine(game.getBoard(), 1, 1, Gomoku.Directions.DL_DIAGONAL), 0); //test 0 pieces in a row, down left diag
    assertEquals("says there are pieces when board is empty", game.numberInLine(game.getBoard(), 1, 1, Gomoku.Directions.UR_DIAGONAL), 0); //test 0 pieces in a row, up right diag
    assertEquals("says there are pieces when board is empty", game.numberInLine(game.getBoard(), 1, 1, Gomoku.Directions.UL_DIAGONAL), 0); //test 0 pieces in a row, up left diag
    
    
    
    game.fillPiece(game.getButton(3,2), Color.WHITE);
    game.fillPiece(game.getButton(3,3), Color.WHITE);
    game.fillPiece(game.getButton(3,4), Color.WHITE);
    game.fillPiece(game.getButton(2,4), Color.WHITE);
    game.fillPiece(game.getButton(1,4), Color.WHITE);
    game.fillPiece(game.getButton(3,0), Color.WHITE);
    game.fillPiece(game.getButton(4,5), Color.WHITE);
    game.fillPiece(game.getButton(2,3), Color.WHITE);
    game.fillPiece(game.getButton(1,5), Color.WHITE);
    
    
    //testing directions for one in line
    assertEquals("says wrong number of pieces in a row", game.numberInLine(game.getBoard(), 3, 4, Gomoku.Directions.RIGHT), 1); //test one piece in a row, right
    assertEquals("says wrong number of pieces in a row", game.numberInLine(game.getBoard(), 3, 2, Gomoku.Directions.LEFT), 1); //test one piece in a row, left
    assertEquals("says wrong number of pieces in a row", game.numberInLine(game.getBoard(), 3, 2, Gomoku.Directions.DOWN), 1); //test one piece in a row, down
    assertEquals("says wrong number of pieces in a row", game.numberInLine(game.getBoard(), 1, 5, Gomoku.Directions.DOWN), 1); //test one piece in a row, down
    
    assertEquals("says wrong number of pieces in a row", game.numberInLine(game.getBoard(), 3, 2, Gomoku.Directions.DR_DIAGONAL), 1); //test one piece in a row, down right diag
    assertEquals("says wrong number of pieces in a row", game.numberInLine(game.getBoard(), 3, 2, Gomoku.Directions.DL_DIAGONAL), 1); //test one piece in a row, down left diag
    assertEquals("says wrong number of pieces in a row", game.numberInLine(game.getBoard(), 1, 4, Gomoku.Directions.UR_DIAGONAL), 1); //test one piece in a row, up right diag
    assertEquals("says wrong number of pieces in a row", game.numberInLine(game.getBoard(), 3, 2, Gomoku.Directions.UL_DIAGONAL), 1); //test one piece in a row, up left diag
    
    
    //testing directions for many in line
    assertEquals("says wrong number of pieces in a row", game.numberInLine(game.getBoard(), 3, 2, Gomoku.Directions.RIGHT), 3); //test many pieces in a row, right
    assertEquals("says wrong number of pieces in a row", game.numberInLine(game.getBoard(), 3, 4, Gomoku.Directions.LEFT), 3); //test many pieces in a row, left
    assertEquals("says wrong number of pieces in a row", game.numberInLine(game.getBoard(), 1, 4, Gomoku.Directions.DOWN), 3); //test many pieces in a row, down
    assertEquals("says wrong number of pieces in a row", game.numberInLine(game.getBoard(), 3, 4, Gomoku.Directions.UP), 3); //test many pieces in a row, up
    
    assertEquals("says wrong number of pieces in a row", game.numberInLine(game.getBoard(), 2, 3, Gomoku.Directions.DR_DIAGONAL), 3); //test many pieces in a row, down right diag
    assertEquals("says wrong number of pieces in a row", game.numberInLine(game.getBoard(), 1, 5, Gomoku.Directions.DL_DIAGONAL), 3); //test many pieces in a row, down left diag
    assertEquals("says wrong number of pieces in a row", game.numberInLine(game.getBoard(), 3, 2, Gomoku.Directions.UR_DIAGONAL), 3); //test many pieces in a row, up right diag
    assertEquals("says wrong number of pieces in a row", game.numberInLine(game.getBoard(), 4, 5, Gomoku.Directions.UL_DIAGONAL), 3); //test many pieces in a row, up left diag
    
  }
  
  @Test
  public void testOverline(){
    Gomoku game2 = new Gomoku();
    game2.createBoard(7,7);
    
    game2.fillPiece(game2.getButton(1,1), Color.BLACK);
    game2.fillPiece(game2.getButton(2,2), Color.BLACK);
    game2.fillPiece(game2.getButton(3,3), Color.BLACK);
    game2.fillPiece(game2.getButton(4,4), Color.BLACK);
    game2.fillPiece(game2.getButton(5,5), Color.BLACK);
    
    game2.fillPiece(game2.getButton(0,5), Color.WHITE);
    
    assertTrue("says five in a row does not exist when it does", game2.overline()); //test true
    
    
    Gomoku game1 = new Gomoku();
    game1.createBoard(7,7);
    game1.fillPiece(game1.getButton(4,4), Color.BLACK);
    game1.fillPiece(game1.getButton(5,5), Color.BLACK);
    game1.fillPiece(game1.getButton(0,5), Color.WHITE);
    
    assertFalse("says five in a row exists when it does not", game1.overline()); //test false, less than
    
    Gomoku game = new Gomoku();
    game.createBoard(7,7);
    
    game.fillPiece(game.getButton(1,1), Color.BLACK);
    game.fillPiece(game.getButton(2,2), Color.BLACK);
    game.fillPiece(game.getButton(3,3), Color.BLACK);
    game.fillPiece(game.getButton(4,4), Color.BLACK);
    game.fillPiece(game.getButton(6,6), Color.BLACK);
    game.fillPiece(game.getButton(5,5), Color.BLACK);
    
    assertFalse("says five in a row exists when it does not", game.overline()); //test false, more than
    
    Gomoku game3 = new Gomoku();
    game3.createBoard(7,7);
    
    game3.setGomoku(7, 7, 6, 5, 4);
    
    game3.fillPiece(game3.getButton(1,1), Color.BLACK);
    game3.fillPiece(game3.getButton(2,2), Color.BLACK);
    game3.fillPiece(game3.getButton(3,3), Color.BLACK);
    game3.fillPiece(game3.getButton(4,4), Color.BLACK);
    game3.fillPiece(game3.getButton(5,5), Color.BLACK);
    game3.fillPiece(game3.getButton(6,6), Color.BLACK);
    
    assertTrue("says six in a row does not exist when it does", game3.overline());
    
  }
  
  @Test
  public void testFourFour(){
    Gomoku game = new Gomoku();
    game.createBoard(6,6);
    
    game.fillPiece(game.getButton(1,1), Color.BLACK);
    game.fillPiece(game.getButton(1,2), Color.BLACK);
    game.fillPiece(game.getButton(1,3), Color.BLACK);
    game.fillPiece(game.getButton(2,0), Color.BLACK);
    game.fillPiece(game.getButton(3,0), Color.BLACK);
    game.fillPiece(game.getButton(4,0), Color.BLACK);
    game.fillPiece(game.getButton(3,2), Color.BLACK);
    game.fillPiece(game.getButton(3,3), Color.BLACK);
    game.fillPiece(game.getButton(3,4), Color.BLACK);

    assertTrue("says false when true", game.fourFour(game.getButton(1,0), Color.BLACK)); //testing where fourfour occurs in the vertical and horizontal directions
    assertFalse("says true when false", game.fourFour(game.getButton(3,5), Color.BLACK)); //testing where fourfour does not occur in the vertical and horizontal directions
    
    
    Gomoku game1 = new Gomoku();
    game1.createBoard(8,7);
    
    game1.fillPiece(game1.getButton(0,3), Color.BLACK);
    game1.fillPiece(game1.getButton(1,2), Color.BLACK);
    game1.fillPiece(game1.getButton(1,3), Color.BLACK);
    game1.fillPiece(game1.getButton(2,1), Color.BLACK);
    game1.fillPiece(game1.getButton(2,4), Color.BLACK);
    game1.fillPiece(game1.getButton(3,5), Color.BLACK);
    game1.fillPiece(game1.getButton(4,1), Color.BLACK);
    game1.fillPiece(game1.getButton(5,2), Color.BLACK);
    game1.fillPiece(game1.getButton(5,5), Color.BLACK);
    game1.fillPiece(game1.getButton(6,3), Color.BLACK);
    game1.fillPiece(game1.getButton(6,4), Color.BLACK);
    game1.fillPiece(game1.getButton(7,3), Color.BLACK);
    
    assertTrue("says false when true", game1.fourFour(game1.getButton(3,0), Color.BLACK)); //testing another instance where four four occurs in the diagonal directions
    assertTrue("says false when true", game1.fourFour(game1.getButton(4,6), Color.BLACK)); //testing another instance where four four occurs in the diagonal directions
    
    Gomoku game2 = new Gomoku();
    game2.createBoard(6,6);
    
    game2.fillPiece(game2.getButton(1,2), Color.BLACK);
    
    assertFalse("says true when false", game2.fourFour(game2.getButton(0,3), Color.BLACK)); //testing where four four does not occur
    
  }
  
  @Test
  public void testGetButtonLocations(){
    Gomoku game = new Gomoku();
    game.createBoard(6,6);
    
    game.fillPiece(game.getButton(1,3), Color.BLACK);
    game.fillPiece(game.getButton(2,4), Color.WHITE);
    
    assertEquals(game.buttonRow(game.getButton(1,3)), 1); //test rows
    assertEquals(game.buttonColumn(game.getButton(2,4)), 4); //test columns
    
    
  }
  
  @Test
  public void testThreeThree(){
    
    Gomoku game = new Gomoku();
    game.createBoard(8,8);
    game.fillPiece(game.getButton(1,2), Color.BLACK);
    game.fillPiece(game.getButton(1,3), Color.BLACK);
    game.fillPiece(game.getButton(2,4), Color.BLACK);
    game.fillPiece(game.getButton(3,4), Color.BLACK);
    game.fillPiece(game.getButton(4,1), Color.BLACK);

    game.fillPiece(game.getButton(5,2), Color.BLACK);
    game.fillPiece(game.getButton(5,3), Color.BLACK);
    game.fillPiece(game.getButton(5,7), Color.BLACK);
    game.fillPiece(game.getButton(6,1), Color.BLACK);
    game.fillPiece(game.getButton(6,7), Color.BLACK);
    game.fillPiece(game.getButton(7,5), Color.BLACK);
    game.fillPiece(game.getButton(7,6), Color.BLACK);
    
    
    assertTrue("says false when true", game.threeThree(game.getButton(1,4), Color.BLACK)); //testing vertical and horizontal directions, open boundaries
    assertTrue("says false when true", game.threeThree(game.getButton(5,1), Color.BLACK));//testing vertical and horizontal directions, open boundaries
    assertFalse("says true when false", game.threeThree(game.getButton(7,7), Color.BLACK)); //testing vertical and horizontal directions, closed boundary
    
    
    Gomoku game1 = new Gomoku();
    game1.createBoard(9,10);
    game1.fillPiece(game1.getButton(1,1), Color.BLACK);
    game1.fillPiece(game1.getButton(1,8), Color.BLACK);
    game1.fillPiece(game1.getButton(2,2), Color.BLACK);
    game1.fillPiece(game1.getButton(2,7), Color.BLACK);
    game1.fillPiece(game1.getButton(4,2), Color.BLACK);
    game1.fillPiece(game1.getButton(4,7), Color.BLACK);
    game1.fillPiece(game1.getButton(5,1), Color.BLACK);
    game1.fillPiece(game1.getButton(5,8), Color.BLACK);
    game1.fillPiece(game1.getButton(6,2), Color.BLACK);
    game1.fillPiece(game1.getButton(6,6), Color.BLACK);
    game1.fillPiece(game1.getButton(7,3), Color.BLACK);
    game1.fillPiece(game1.getButton(7,5), Color.BLACK);
    
    
    assertTrue("says false when true", game1.threeThree(game1.getButton(3,3), Color.BLACK)); //testing diagonal directions, open boundaries
    assertTrue("says false when true", game1.threeThree(game1.getButton(3,6), Color.BLACK));//testing diagonal directions, open boundaries
    assertFalse("says true when false", game1.threeThree(game1.getButton(8,4), Color.BLACK)); //testing diagonal directions, closed boundary
    
    
  }
  
}