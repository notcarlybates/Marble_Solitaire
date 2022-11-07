package cs3500.marblesolitaire.model.hw04;

/**
 * Represents the model for a European Solitaire Model. Is an octagonal game board that
 * takes in a row length, column length, but most distinctively - the side length.
 */
public class EuropeanSolitaireModel extends AbstractSolitaireModel {

  /**
   * Initializes a European Solitaire octagonal game board with an arm thickness of 3,
   * and with the empty slot in the centre.
   */
  public EuropeanSolitaireModel() {
    this(3, 3, 3);
  }

  /**
   * Initializes the European game board with a armThickness and with an empty slot in the centre.
   * @param armThickness The arm thickness (a positive odd integer)
   * @throws IllegalArgumentException If the armThickness size is not a positive number,
   *                                  is not odd,
   *                                  or is 1.
   */
  public EuropeanSolitaireModel(int armThickness) throws IllegalArgumentException {
    this(armThickness, armThickness + armThickness / 2 - 1, armThickness + armThickness / 2 - 1);
  }

  /**
   * Initializes the European game board with an arm thickness of 3 and with an empty slot.
   * @param rowEmpty The row of the empty slot
   * @param columnEmpty The column of the empty slot
   * @throws IllegalArgumentException If the empty slot is out of bounds/is invalid
   */
  public EuropeanSolitaireModel(int rowEmpty, int columnEmpty) throws IllegalArgumentException {
    this(3, rowEmpty, columnEmpty);
  }

  /**
   * Initializes the European game board for an
   * English Solitaire Game with a armThickness and an empty slot.
   *
   * @param armThickness The arm thickness (a positive odd integer)
   * @param rowEmpty The row of the empty slot
   * @param columnEmpty The column of the empty slot
   * @throws IllegalArgumentException If the armThickness size is not a positive number,
   *                                  is not odd,
   *                                  or is 1,
   *                                  or if the empty slot is out of bounds/is invalid
   */
  public EuropeanSolitaireModel(int armThickness, int rowEmpty, int columnEmpty)
          throws IllegalArgumentException {
    super(armThickness, rowEmpty, columnEmpty);
  }

  protected boolean boardLogic(int armThickness, int toRow, int toCol) {
    int centre = armThickness + armThickness / 2 - 1;
    return (this.manhattanDistance(centre, centre, toRow, toCol) < (2 * armThickness - 1));
  }

  private int manhattanDistance(int fromRow, int fromCol, int toRow, int toCol) {
    return Math.abs(fromRow - toRow) + Math.abs(fromCol - toCol);
  }
}
