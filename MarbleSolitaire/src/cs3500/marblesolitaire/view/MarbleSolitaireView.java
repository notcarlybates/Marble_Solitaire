package cs3500.marblesolitaire.view;

import java.io.IOException;

/**
 * This interface represents operations that should be offered by
 * a view for the Marble solitaire game.
 */

public interface MarbleSolitaireView {
  /**
   * Return a string that represents the current state of the board. Each slot on the
   * game board is a single character (O, _, "[space]" for a marble, empty and
   * invalid position respectively). The string should have one line per row of the game board.
   * Slots in a row will be separated by a
   * space. Each row has no space before the first slot and after the last slot.
   *
   *  @return the game state as a string
   */
  String toString();

  /**
   * Render the board to the provided data destination. The board should be rendered exactly
   * in the format produced by the toString method above
   *
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  void renderBoard() throws IOException;

  /**
   * Renders a message to a given data definition.
   *
   * @param message the transmitted message
   * @throws IOException if the transmission fails
   */
  void renderMessage(String message) throws IOException;
}
