package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * The abstract model for a marble solitaire game. This class represents common features/methods of
 * multiple solitaire implementations. This class is more oriented towards English and European
 * models because they take the majority - and are the more common implementations.
 */
public abstract class AbstractSolitaireModel implements MarbleSolitaireModel {
  private final int armThickness;
  private final int rowEmpty;
  private final int columnEmpty;
  private final SlotState[][] board;

  /**
   * Initializes the game board for any model that has a armThickness, empty row, and empty column.
   *
   * @param armThickness The arm thickness (implementation depends on the type of model)
   * @param rowEmpty The row of the empty slot
   * @param columnEmpty The column of the empty slot
   * @throws IllegalArgumentException depending on the implementation details of the type of model
   */
  public AbstractSolitaireModel(int armThickness, int rowEmpty, int columnEmpty)
          throws IllegalArgumentException {
    this.canConstruct(armThickness, rowEmpty, columnEmpty);

    this.armThickness = armThickness;
    this.rowEmpty = rowEmpty;
    this.columnEmpty = columnEmpty;
    board = this.setBoardSize(armThickness);
    this.makeBoard();
  }

  protected SlotState[][] setBoardSize(int armThickness) {
    return new SlotState[3 * armThickness - 2][3 * armThickness - 2];
  }

  protected void canConstruct(int armThickness, int rowEmpty, int columnEmpty) {
    if (armThickness <= 0) {
      throw new IllegalArgumentException("Arm thickness is invalid.");
    }
    if (armThickness == 1) {
      throw new IllegalArgumentException("Arm thickness is invalid.");
    }
    if (armThickness % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness is invalid.");
    }
  }

  private void makeBoard() throws IllegalArgumentException {
    for (int row = 0; row < this.getBoardSize(); row += 1) {
      for (int col = 0; col < this.getBoardSize(); col += 1) {
        if (this.boardLogic(armThickness, row, col)) {
          board[row][col] = SlotState.Marble;
        } else {
          board[row][col] = SlotState.Invalid;
        }
      }
    }

    if (this.outOfBounds(rowEmpty, columnEmpty)
            || board[rowEmpty][columnEmpty] == SlotState.Invalid) {
      throw new IllegalArgumentException("Invalid empty cell position (" + rowEmpty + ","
              + columnEmpty + ")");
    }

    board[rowEmpty][columnEmpty] = SlotState.Empty;
  }

  protected abstract boolean boardLogic(int armThickness, int row, int col);

  /**
   * Returns the size of the game. (number of rows or columns since).
   *
   * @return an int representing the size
   */
  @Override
  public int getBoardSize() {
    return board.length;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (this.outOfBounds(row, col)) {
      throw new IllegalArgumentException("Out of bounds.");
    }
    return board[row][col];
  }

  private boolean outOfBounds(int row, int col) {
    return row >= this.getBoardSize() || col >= this.getBoardSize() || row < 0 || col < 0;
  }

  @Override
  public int getScore() {
    int count = 0;
    for (SlotState[] sArr : board) {
      for (SlotState s : sArr) {
        if (s == SlotState.Marble) {
          count += 1;
        }
      }
    }
    return count;
  }

  protected boolean twoSlotsAway(int fromRow, int fromCol, int toRow, int toCol) {
    return ((Math.abs(fromRow - toRow) == 2 && (fromCol - toCol == 0))
            ^ (Math.abs(fromCol - toCol) == 2 && (fromRow - toRow == 0)));
  }

  protected boolean checkMoves(int row, int col) {
    return validMove(row, col, row - 2, col)
            || validMove(row, col, row, col + 2)
            || validMove(row, col, row + 2, col)
            || validMove(row, col, row, col - 2);
  }

  /**
   * The method for moving from one coordinate to another on the board.
   * A move is valid if all these conditions are true:
   *  - the "to" and "from" positions are exactly two positions away - but more restrictions apply
   *  according to specification.
   *  - the "to" position is empty.
   *  - the "from" and "to" positions are valid (inside the board).
   *  - there is a marble in the slot between the "to" and "from" positions.
   *  - there is a marble at the specified "from" position.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if any one of the conditions above are not true
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (!validMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("Invalid move.");
    }

    board[fromRow][fromCol] = SlotState.Empty;
    board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = SlotState.Empty;
    board[toRow][toCol] = SlotState.Marble;
  }

  protected boolean validMove(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    return !outOfBounds(fromRow, fromCol) && !outOfBounds(toRow, toCol)
            && this.getSlotAt(fromRow, fromCol) == SlotState.Marble
            && this.getSlotAt((fromRow + toRow) / 2, (fromCol + toCol) / 2)
            == SlotState.Marble
            && this.getSlotAt(toRow, toCol) == SlotState.Empty
            && this.twoSlotsAway(fromRow, fromCol, toRow, toCol);
  }

  /**
   * The game is over if there are no valid moves left for any marble on the board.
   * This method finds all marbles on the board,and if they have a valid move some spaces away
   * according to the game specification then the game is over.
   *
   * @return a boolean value that is true if the game is over and false if it is not.
   */
  @Override
  public boolean isGameOver() {
    for (int row = 0; row < this.getBoardSize(); row += 1) {
      for (int col = 0; col < this.getBoardSize(); col += 1) {
        if (board[row][col] == SlotState.Marble) {
          if (this.checkMoves(row, col)) {
            return false;
          }
        }
      }
    }
    return true;
  }
}