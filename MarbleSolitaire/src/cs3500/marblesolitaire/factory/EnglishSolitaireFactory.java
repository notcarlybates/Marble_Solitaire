package cs3500.marblesolitaire.factory;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;

/**
 * Produces objects of the English Solitaire Model in the factory cass.
 */
public class EnglishSolitaireFactory extends MarbleSolitaireFactory {
  /**
   * Constructor that creates the required model for the English Solitaire Model.
   *
   * @param armThickness A positive, odd integer
   * @param rowEmpty Row of the empty slot within the game board
   * @param columnEmpty Column of the empty slot within the game board
   */
  public EnglishSolitaireFactory(int armThickness, int rowEmpty, int columnEmpty) {
    super(new EnglishSolitaireModel(armThickness, rowEmpty, columnEmpty));
  }
}
