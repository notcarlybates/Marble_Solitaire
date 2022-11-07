package cs3500.marblesolitaire.model.hw04;

/**
 * Represents the model for a European Solitaire Model. Is a triangular, three-sided game board that
 * takes in one integer that determines the number of slots on the bottom row, which then creates
 * the number of slots on the rows above it.
 * */
public class TriangleSolitaireModel extends AbstractSolitaireModel {

  /**
   * Initializes a Triangle Solitaire game board with 5 rows,
   * and with the empty slot right at the top.
   */
  public TriangleSolitaireModel() {
    this(5, 0, 0);
  }

  /**
   * Initializes the Triangle game board with a armThickness
   * and with a default empty slot at the top.
   *
   * @param armThickness The bottom-most row size (a positive odd integer)
   * @throws IllegalArgumentException If the armThickness size is not a positive number, or
   *                                  is 1 since the game would not be playable.
   */
  public TriangleSolitaireModel(int armThickness) throws IllegalArgumentException {
    this(armThickness, 0, 0);
  }

  /**
   * Initializes the Triangle game board with bottom row of size 5 and with an empty slot as
   * specified.
   * @param rowEmpty The row of the empty slot
   * @param columnEmpty The column of the empty slot
   * @throws IllegalArgumentException If the empty slot is out of bounds/is invalid
   */
  public TriangleSolitaireModel(int rowEmpty, int columnEmpty) throws IllegalArgumentException {
    this(5, rowEmpty, columnEmpty);
  }

  /**
   * Initializes the Triangle game board with a armThickness and an empty
   * slot.
   * @param armThickness The arm thickness (a positive odd integer)
   * @param rowEmpty The row of the empty slot
   * @param columnEmpty The column of the empty slot
   * @throws IllegalArgumentException If the armThickness size is not a positive number,
   *                                  or is 1,
   *                                  or if the empty slot is out of bounds/is invalid
   */
  public TriangleSolitaireModel(int armThickness, int rowEmpty, int columnEmpty)
          throws IllegalArgumentException {
    super(armThickness, rowEmpty, columnEmpty);
  }

  @Override
  protected void canConstruct(int armThickness, int rowEmpty, int columnEmpty) {
    if (armThickness <= 0) {
      throw new IllegalArgumentException("Arm thickness is invalid.");
    }
    if (armThickness == 1) {
      throw new IllegalArgumentException("Arm thickness is invalid.");
    }
  }

  @Override
  protected SlotState[][] setBoardSize(int armThickness) {
    return new SlotState[armThickness][armThickness];
  }

  @Override
  protected boolean checkMoves(int row, int col) {
    return super.checkMoves(row, col)
            || validMove(row, col, row - 2, col - 2)
            || validMove(row, col, row + 2, col + 2);
  }

  @Override
  protected boolean twoSlotsAway(int fromRow, int fromCol, int toRow, int toCol) {
    return super.twoSlotsAway(fromRow, fromCol, toRow, toCol)
            || (Math.abs(fromRow - toRow) == 2 && Math.abs(fromCol - toCol) == 2
            && Math.abs((fromRow + fromCol) - (toRow + toCol)) == 4);
  }

  protected boolean boardLogic(int armThickness, int toRow, int toCol) {
    return toCol <= toRow;
  }
}
