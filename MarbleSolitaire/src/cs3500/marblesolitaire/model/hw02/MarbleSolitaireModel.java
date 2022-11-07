package cs3500.marblesolitaire.model.hw02;

/**
 * This interface represents the operations offered by the marble solitaire
 * model. One object of the model represents one game of marble solitaire
 */
public interface MarbleSolitaireModel extends MarbleSolitaireModelState {
  /**
   * Move a single marble from a given position to another given position.
   * A move is valid only if the from and to positions are valid. Specific
   * implementations may place additional constraints on the validity of a move.
   *
   * @param fromRow the row (starting at zero) of the slot the user intends to move the marble from
   * @param fromCol the column (starting at zero) of the slot the user intends to move the marble to
   * @param toRow   the row (starting at zero) of the slot the user intends to move the marble to
   * @param toCol   the column (starting at zero) of the slot the user intends to move the marble to
   * @throws IllegalArgumentException if the move is not possible
   */
  void move(int fromRow, int fromCol, int toRow, int toCol) throws
      IllegalArgumentException;

  /**

   * If there are not any more moves possible, will return that the game is over.
   * If there are still moves to be made, will return the running state of the game.
   * @return game
   */
  boolean isGameOver();
}
