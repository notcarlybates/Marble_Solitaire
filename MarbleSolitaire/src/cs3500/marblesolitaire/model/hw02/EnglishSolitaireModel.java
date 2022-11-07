package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.AbstractSolitaireModel;

/**
 * Represents an English solitaire model with a board of a given arm thickness where each
 * slot on the board is either filled with a marble, empty, or not in use in the game.
 */
public class EnglishSolitaireModel extends AbstractSolitaireModel {

  /**
   *Initializes the game board with an arm thickness of 3 and an empty slot in center.
   */
  public EnglishSolitaireModel() {
    this(3, 3, 3);
  }

  /**
   *Initializes the game board with an arm thickness of 3 and an empty slot at a given location.
   *
   * @param sRow Row of the empty slot within the game board
   * @param sCol Column of the empty slot within the game board
   * @throws IllegalArgumentException If the empty slot is out of bounds/is invalid
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this(3, sRow, sCol);
  }

  /**
   * Initializes the English game board with a armThickness and with an empty slot in the centre.
   * @param armThickness The arm thickness (a positive odd integer)
   * @throws IllegalArgumentException If the armThickness size is
   *                                  not a positive number, is not odd,
   *                                  or is 1 since the game would not be playable.
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    this(armThickness, armThickness + armThickness / 2 - 1, armThickness + armThickness / 2 - 1);
  }

  /**
   * Helps create the game board for an English Marble Solitaire Game with an arm-thickness
   * and an empty slot.
   *
   * @param armThickness A positive odd integer
   * @param sRow Row of empty slot within the game board
   * @param sCol Column of empty slot within the board
   * @throws IllegalArgumentException
   *    Throws an error if the arm thickness is
   *     below 3, or if the arm thickness is
   *     an even number   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    super(armThickness, sRow, sCol);
  }

  protected boolean boardLogic(int armThickness, int toRow, int toCol) {
    return ((toCol > armThickness - 2) && (toCol < 2 * armThickness - 1))
            || ((toRow > armThickness - 2) && (toRow < 2 * armThickness - 1));
  }

}