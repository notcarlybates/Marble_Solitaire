import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;


import static org.junit.Assert.assertEquals;

/**
 * Tests MarbleSolitaireTextView using different constructors and different valid moves.
 */
public class TestMarbleSolitaireTextView {

  private MarbleSolitaireTextView view1;
  private MarbleSolitaireTextView view2;

  @Before
  public void init() {
    view1 = new MarbleSolitaireTextView(new EnglishSolitaireModel());
    view2 = new MarbleSolitaireTextView(new EnglishSolitaireModel(5, 5, 4));
  }

  @Test
  public void testConstructorNull() {
    try {
      new MarbleSolitaireTextView(null);
    } catch (IllegalArgumentException n) {
      assertEquals("Invalid model of type null", n.getMessage());
    }
  }

  @Test
  public void testToString() {
    System.out.println(view1.toString());
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", view1.toString());

    assertEquals("        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O _ O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O", view2.toString());

  }

}
