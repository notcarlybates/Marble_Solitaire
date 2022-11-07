package cs3500.marblesolitaire.controller;

/**
 * Used to interact with the whole program - the model and the view -
 *with some readable input. Its implementation is responsible for controlling the entire program.
 * */
public interface MarbleSolitaireController {

  /**
   /**
   * Used to communicate with model and view using readable input.
   * Will take in positive integers, "q", or "Q".
   * Unexpected value will prompt the user to enter a valid move.
   *
   * @throws IllegalStateException If controller cannot transmit the output to the appendable
   */
  void playGame() throws IllegalStateException;

}