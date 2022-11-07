package cs3500.marblesolitaire.factory;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * An interface that will enable us to create a consistent model-view pair.
 */
public interface MarbleSolitaireAbstractFactory {

  /**
   * Factory method to create the model that pairs with a specific view.
   *
   * @return The model that the controller will use to create Model objects
   */
  MarbleSolitaireModel createModel();

  /**
   * Factory method to create the view that pairs with a specific model.
   *
   * @return The view that the controller will use to create View objects
   */
  MarbleSolitaireView createView();

  /**
   * Factory method to create the view that pairs with a specific model.
   *
   * @param ap The destination of the view
   * @return The view that the controller will use to create View objects
   */
  MarbleSolitaireView createView(Appendable ap);

}
