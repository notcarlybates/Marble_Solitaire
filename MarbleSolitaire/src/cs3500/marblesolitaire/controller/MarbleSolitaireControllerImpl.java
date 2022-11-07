package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.Scanner;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Controller of the Marble Solitaire Game
 * used for interaction between the view and model.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private final MarbleSolitaireModel model;
  private final MarbleSolitaireView view;
  private final Readable read;

  /**
   * Initializes the controller with a non-null model, view, and readable input.
   *
   * @param model The model of the game board
   * @param view The view of the game board
   * @param read The read of the inputs
   * @throws IllegalArgumentException if model, view, or read are null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model,
                                       MarbleSolitaireView view,
                                       Readable read) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Null model.");
    }
    if (view == null) {
      throw new IllegalArgumentException("Null view.");
    }
    if (read == null) {
      throw new IllegalArgumentException("Null readable.");
    }

    this.model = model;
    this.view = view;
    this.read = read;

  }

  /**
   * Used to communicate with model and view using readable input.
   * Will take in positive integers, "q", or "Q".
   * Unexpected value will prompt the user to enter a valid move.
   *
   * @throws IllegalStateException If controller cannot transmit the output to the appendable
   */
  @Override
  public void playGame() throws IllegalStateException {
    Scanner sc = new Scanner(read);
    int[] move = new int[4];
    boolean gq = false;

    while (!model.isGameOver() && !gq) {
      this.printBoard();

      int i = 0;
      while (i < 4 && !gq) {
        if (!sc.hasNext()) {
          throw new IllegalStateException("Unable to successfully receive input");
        }

        String strInput = sc.next();

        if (strInput.equalsIgnoreCase("q")) {
          gq = true;
        } else if (posInt(strInput)) {
          move[i] = Integer.parseInt(strInput) - 1;

          i += 1;

          if (i == 4 && !canMove(move[0], move[1], move[2], move[3])) {
            i = 0;
          }

        } else {
          this.printMessage("Only enter a positive integer, or q/Q to quit the game. Not:"
                  + strInput);
        }
      }
    }

    if (gq) {
      this.printMessage("Game quit!");
      this.printMessage("State of game when quit:");
    } else {
      this.printMessage("Game over!");
    }
    this.printBoard();

  }

  private boolean canMove(int fromRow, int fromCol, int toRow, int toCol) {
    try {
      this.model.move(fromRow, fromCol, toRow, toCol);
      return true;
    } catch (IllegalArgumentException exe) {
      this.printMessage("Invalid move. Play again.");
      return false;
    }
  }

  private static boolean posInt(String s) {
    if (s == null) {
      return false;
    }
    try {
      int i = Integer.parseInt(s);
      return i > 0;
    } catch (NumberFormatException nfe) {
      return false;
    }
  }

  private void printMessage(String s) {
    try {
      this.view.renderMessage(s + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Unable to successfully transmit output");
    }
  }

  private void printBoard() {
    try {
      this.view.renderBoard();
      this.printMessage(String.format("\n" + "Score: %d", model.getScore()));
    } catch (IOException e) {
      throw new IllegalStateException("Unable to successfully transmit output");
    }
  }
}