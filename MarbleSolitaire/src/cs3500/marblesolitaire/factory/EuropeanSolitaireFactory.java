package cs3500.marblesolitaire.factory;

import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;

/**
 * Factory class to produce objects of the European Solitaire Model.
 */
public class EuropeanSolitaireFactory extends MarbleSolitaireFactory {
  /**
   * Constructor that creates the required model for the European Solitaire Model.
   *
   * @param scale The arm thickness (a positive odd integer)
   * @param rowEmpty The row of the empty slot
   * @param columnEmpty The column of the empty slot
   */
  public EuropeanSolitaireFactory(int scale, int rowEmpty, int columnEmpty) {
    super(new EuropeanSolitaireModel(scale, rowEmpty, columnEmpty));
  }
}
