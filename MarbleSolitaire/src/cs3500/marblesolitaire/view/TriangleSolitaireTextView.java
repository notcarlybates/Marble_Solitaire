package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * The view of the Triangle version of the English Solitaire Game.
 * Implements the toString method. */
public class TriangleSolitaireTextView extends MarbleSolitaireTextView {
  /**
   * Constructor for our Triangle Marble Solitaire Game View.
   *
   * @param model the model that we are trying to print
   * @throws IllegalArgumentException If the model is of type null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model)
            throws IllegalArgumentException {
    super(model);
  }

  /**
   * Second Constructor for our Triangle Marble Solitaire Game View that uses an appendable object
   * for its destination.
   *
   * @param model the model we are printing of the game
   * @param destination appendable object that is used as a destination for the view
   * @throws IllegalArgumentException if the model or destination are invalid or null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable destination)
            throws IllegalArgumentException {
    super(model, destination);
  }

  @Override
  public String toString() {
    StringBuilder textView = new StringBuilder();
    StringBuilder[] lst = this.imageCreator();

    for (int i = 0; i < lst.length; i += 1) {
      textView.append(" ".repeat(lst.length - 1 - i)).append(lst[i].toString());
    }

    return textView.toString().stripTrailing();
  }
}
