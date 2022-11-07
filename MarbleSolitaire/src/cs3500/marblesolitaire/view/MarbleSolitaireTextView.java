package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents the View of the Marble Solitaire Game.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  private final MarbleSolitaireModelState model;
  private final Appendable destination;

  /**
   * Constructor for our Marble Solitaire Game View.
   *
   * @param model the model that we intend to print
   * @throws IllegalArgumentException If the model is of type null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Invalid model of type null");
    }
    this.model = model;
    this.destination = System.out;
  }

  /**
   * Second Constructor for our Marble Solitaire Game View that uses an appendable object for its
   * destination.
   *
   * @param model the model that we intend to print
   * @param destination the destination of the appendable object
   * @throws IllegalArgumentException If the model or appendable is of type null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable destination)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model is null; cannot proceed.");
    }
    if (destination == null) {
      throw new IllegalArgumentException("Invalid appendable of type null");
    }
    this.model = model;
    this.destination = destination;
  }

  @Override
  public String toString() {
    StringBuilder textView = new StringBuilder();
    StringBuilder[] sbList = this.imageCreator();

    for (StringBuilder sb : sbList) {
      textView.append(sb.toString());
    }

    return textView.toString().stripTrailing();
  }

  protected StringBuilder[] imageCreator() {
    StringBuilder[] sbList = new StringBuilder[model.getBoardSize()];

    for (int row = 0; row < model.getBoardSize(); row += 1) {
      StringBuilder textView = new StringBuilder();
      for (int col = 0; col < model.getBoardSize(); col += 1) {
        switch (model.getSlotAt(row, col)) {
          case Invalid: textView.append(" ");
            break;
          case Marble: textView.append("O");
            break;
          case Empty: textView.append("_");
            break;
          default:
            throw new IllegalStateException("View does not support this state");
        }
        textView.append(" ");
      }
      textView = new StringBuilder(textView.toString().stripTrailing());
      textView.append("\n");
      sbList[row] = textView;
    }

    return sbList;
  }

  public void renderBoard() throws IOException {
    destination.append(this.toString());
  }

  public void renderMessage(String message) throws IOException {
    destination.append(message);
  }
}