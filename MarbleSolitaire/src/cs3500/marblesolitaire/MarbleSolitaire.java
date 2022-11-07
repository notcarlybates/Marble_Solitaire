package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.factory.MarbleSolitaireAbstractFactory;
import cs3500.marblesolitaire.factory.MarbleSolitaireFactoryBuilder;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Runs the different versions and configurations
 * of the Marble Solitaire game over the command line interface.
 */
public final class MarbleSolitaire {

  /**
   * Runs and plays different configurations of the Marble Solitaire game.
   *    @param args Takes in the type of Marble Solitaire game to be played:
   *                - English
   *                - Triangular
   *                - European
   *                And optionally takes in an arm thickness to play with.
   */
  public static void main(String[] args) {
    MarbleSolitaireAbstractFactory factory;

    if (args.length > 0) {
      MarbleSolitaireFactoryBuilder builder = new MarbleSolitaireFactoryBuilder(args[0]);
      for (int i = 0; i < args.length; i += 1) {
        switch (args[i]) {
          case "-size":
            builder.armThickness(Integer.parseInt(args[i + 1]));
            break;
          case "-hole":
            builder.rowEmpty(Integer.parseInt(args[i + 1]))
                .columnEmpty(Integer.parseInt(args[i + 2]));
            break;
          default:
            break;
        }
      }
      factory = builder.build();
    } else {
      throw new IllegalArgumentException("Invalid configuration for game");
    }

    MarbleSolitaireModel model = factory.createModel();
    MarbleSolitaireView view = factory.createView();
    Readable rd = new InputStreamReader(System.in);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
  }
}