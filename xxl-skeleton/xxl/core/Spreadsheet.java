package xxl.core;

import java.io.IOException;

// FIXME import classes

import java.io.Serial;
import java.io.Serializable;

import java.util.List;
import java.util.ArrayList;

import xxl.core.exception.InexistentCellException;
import xxl.core.exception.UnrecognizedEntryException;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {
  @Serial
  private static final long serialVersionUID = 202308312359L;

  private List<Cell> _cells;
  private int _rows;
  private int _columns;
  private List<User> _users;
  private CutBuffer _cutBuffer;
  private boolean _changed;

  public Spreadsheet (int rows, int columns) {
    _rows = rows;
    _columns = columns;
    _cells = new ArrayList<>();
    _users = new ArrayList<>();
  }

  public Spreadsheet(){

  }
  
  // FIXME define attributes
  // FIXME define contructor(s)
  // FIXME define methods
  
  /**
   * Insert specified content in specified address.
   *
   * @param row the row of the cell to change 
   * param column the column of the cell to change
   * @param contentSpecification the specification in a string format of the content to put
   *        in the specified cell.
   */
  public void insertContent(int row, int column, String contentSpecification) throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
    //FIXME implement method
  }

  /**
   * Gets a cell in app and checks if it exits in _cells
   * @param cell Cell created in app
   * @return true if cell exists
   */
  public boolean isValidCell(Cell cell){
      for (Cell c: _cells)
      if (c.equals(cell))
        return true;
      return false;
  }


  /**
   * returns the cell we're looking for
   * it´s only used if isValidCell() returns true
   * @param row 
   * @param column
   * @return  the correspondent cell from _cells
   */
  public Cell getCell(int row, int column) {
    Cell aux = new Cell(row, column);
    for (Cell c: _cells)
      if(c.equals(aux))
        return c;
    return aux;
  }

  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   */
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
    Parser parse = new Parser(this);
    //parse.parseFile(filename);
  }
}
