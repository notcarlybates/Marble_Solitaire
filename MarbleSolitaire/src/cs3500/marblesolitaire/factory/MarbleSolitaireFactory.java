package cs3500.marblesolitaire.factory;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * The Marble Solitaire Factory that is implemented by different Model factories.
 * This factory helps instantiate the objects of the Solitaire classes.
 */
public class MarbleSolitaireFactory implements MarbleSolitaireAbstractFactory {
  private final MarbleSolitaireModel model;

  MarbleSolitaireFactory(MarbleSolitaireModel model) {
    this.model = model;
  }

  /**
   * Factory method to create the model that pairs with a specific view.
   *
   * @return The model that the controller will use to create Model objects
   */
  @Override
  public MarbleSolitaireModel createModel() {
    return model;
  }

  /**
   * Factory method to create the view that pairs with a specific model.
   *
   * @return The view that the controller will use to create View objects
   */
  @Override
  public MarbleSolitaireView createView() {
    return new MarbleSolitaireTextView(model);
  }

  /**
   * Factory method to create the view that pairs with a specific model.
   *
   * @param ap The destination of the view
   * @return The view that the controller will use to create View objects
   */
  @Override
  public MarbleSolitaireView createView(Appendable ap) {
    return new MarbleSolitaireTextView(model, ap);
  }
}
