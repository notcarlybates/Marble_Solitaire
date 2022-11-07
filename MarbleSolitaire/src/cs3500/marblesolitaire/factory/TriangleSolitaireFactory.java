package cs3500.marblesolitaire.factory;

import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * Factory class to produce objects of the Triangle Solitaire Model.
 */
public class TriangleSolitaireFactory extends MarbleSolitaireFactory {
  /**
   * Constructor that creates the required model for the Triangle Solitaire Model.
   *
   * @param scale The arm thickness (a positive odd integer)
   * @param rowEmpty The row of the empty slot
   * @param columnEmpty The column of the empty slot
   */
  public TriangleSolitaireFactory(int scale, int rowEmpty, int columnEmpty) {
    super(new TriangleSolitaireModel(scale, rowEmpty, columnEmpty));
  }

  /**
   * Factory method to create the view that pairs with a specific model.
   *
   * @return The view that the controller will use to create View objects
   */
  @Override
  public MarbleSolitaireView createView() {
    return new TriangleSolitaireTextView(this.createModel());
  }

  /**
   * Factory method to create the view that pairs with a specific model.
   *
   * @param ap The destination of the view
   * @return The view that the controller will use to create View objects
   */
  @Override
  public MarbleSolitaireView createView(Appendable ap) {
    return new TriangleSolitaireTextView(this.createModel(), ap);
  }
}
