package cs3500.marblesolitaire.model.hw02;

/**
 * Represents operations that are used to monitor the
 * state of an English Marble Solitaire model without changing the model.
 */
public interface MarbleSolitaireModelState {
  /**
   * This enum represents the state of a single slot on the board. Every slot
   * can either be empty, have a marble, or be an invalid position.
   */
  enum SlotState { Empty, Marble, Invalid }

  /**
   * Return the size of this board. The size is roughly the longest dimension of a board.
   *
   * @return the size as an integer
   */
  int getBoardSize();

  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the slot at the given row and column
   * @throws IllegalArgumentException if the row or the column are beyond
   *         the dimensions of the board
   */
  SlotState getSlotAt(int row, int col) throws IllegalArgumentException;

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  int getScore();
}
