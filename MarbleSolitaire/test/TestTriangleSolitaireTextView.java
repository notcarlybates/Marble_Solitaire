import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;

/**
 * Tests the TriangleSolitaireTextView class.
 */
public class TestTriangleSolitaireTextView {


  private TriangleSolitaireModel model1;
  private TriangleSolitaireModel model2;

  private TriangleSolitaireTextView view1;
  private TriangleSolitaireTextView view2;


  /**
   * Initializes the models.
   */
  @Before
  public void init() {
    model1 = new TriangleSolitaireModel();
    view1 = new TriangleSolitaireTextView(model1);
    model2 = new TriangleSolitaireModel(5, 2, 2);
    view2 = new TriangleSolitaireTextView(model2);
  }

  /**
   * Tests the expected exception for a null model input given to the view constructor.
   */
  @Test
  public void testConstructorNull() {
    try {
      new MarbleSolitaireTextView(null);
    } catch (IllegalArgumentException n) {
      assertEquals("Invalid model of type null", n.getMessage());
    }
  }

  /**
   * Tests the toString method for TriangleSolitaireModel.
   */

  @Test
  public void testToStringTri() {
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", view1.toString());

    model1.move(2, 0, 0, 0);

    assertEquals("    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O", view1.toString());


    assertEquals("    O\n" +
            "   O O\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O", view2.toString());

    model2.move(4, 2, 2, 2);

    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O _ O\n" +
            "O O _ O O", view2.toString());

  }

}
