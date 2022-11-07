import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests the EuropeanSolitaireModel class.
 */
public class TestEuropeanSolitaireModel {

  private EuropeanSolitaireModel model1;
  private EuropeanSolitaireModel model2;
  private EuropeanSolitaireModel model3;
  private EuropeanSolitaireModel model4;
  private EuropeanSolitaireModel model5;


  /**
   * Initializes the models.
   */
  @Before
  public void init() {
    model1 = new EuropeanSolitaireModel();
    model2 = new EuropeanSolitaireModel(7);
    model3 = new EuropeanSolitaireModel(5, 3);
    model4 = new EuropeanSolitaireModel(5, 5, 4);
    model5 = new EuropeanSolitaireModel(3, 4, 4);
    model5 = new EuropeanSolitaireModel(3, 1, 1);
  }

  /**
   * Tests incorrect supplied arm thicknesses with the constructor.
   */
  @Test
  public void testArmThickness() {
    try {
      EuropeanSolitaireModel aT1 = new EuropeanSolitaireModel(1);
      fail("bad constructor");
    } catch (IllegalArgumentException aT1) {
      assertEquals("Arm thickness is invalid.", aT1.getMessage());
    }
    try {
      EuropeanSolitaireModel aT0 = new EuropeanSolitaireModel(0);
      fail("bad constructor");
    } catch (IllegalArgumentException aT1) {
      assertEquals("Arm thickness is invalid.", aT1.getMessage());
    }
    try {
      EuropeanSolitaireModel aTMinus1 = new EuropeanSolitaireModel(-1);
    } catch (IllegalArgumentException aTMinus1) {
      assertEquals("Arm thickness is invalid.", aTMinus1.getMessage());
    }
    try {
      EuropeanSolitaireModel aT10 = new EuropeanSolitaireModel(10);
    } catch (IllegalArgumentException aT10) {
      assertEquals("Arm thickness is invalid.", aT10.getMessage());

    }

  }

  /**
   * Tests incorrect supplied empty space with the constructor.
   */
  @Test
  public void testEmptySpace() {
    try {
      EuropeanSolitaireModel eS1 = new EuropeanSolitaireModel(-1, 2);
      fail("bad constructor");
    } catch (IllegalArgumentException eS1) {
      assertEquals("Invalid empty cell position (-1,2)", eS1.getMessage());
    }
    try {
      EuropeanSolitaireModel eS2 = new EuropeanSolitaireModel(9, 9);
      fail("bad constructor");
    } catch (IllegalArgumentException eS2) {
      assertEquals("Invalid empty cell position (9,9)", eS2.getMessage());
    }

  }

  /**
   * Tests incorrect supplied arm thickness and empty space.
   */
  @Test
  public void testATAndEmptySpace() {
    try {
      EuropeanSolitaireModel ate1 = new EuropeanSolitaireModel(-1, 3, 3);
      fail("bad constructor");
    } catch (IllegalArgumentException ate1) {
      assertEquals("Arm thickness is invalid.", ate1.getMessage());
    }
    try {
      EuropeanSolitaireModel ate2 = new EuropeanSolitaireModel(3, -1, -1);
      fail("bad constructor");
    } catch (IllegalArgumentException ate2) {
      assertEquals("Invalid empty cell position (-1,-1)", ate2.getMessage());
    }
    try {
      EuropeanSolitaireModel ate3 = new EuropeanSolitaireModel(2, 1, 1);
      fail("bad constructor");
    } catch (IllegalArgumentException ate3) {
      assertEquals("Arm thickness is invalid.", ate3.getMessage());
    }
  }

  /**
   * Tests the move method.
   */
  @Test
  public void testMove() {

    // tests invalid moves
    try {
      model1.move(1, 1, 3, 3);
      fail("bad constructor");
    } catch (IllegalArgumentException m) {
      assertEquals("Invalid move.", m.getMessage());
    }

    try {
      model5.move(1, 3, 5, 3);
      fail("bad constructor");
    } catch (IllegalArgumentException m) {
      assertEquals("Invalid move.", m.getMessage());
    }

    // tests a positive horizontal move
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(3, 2));
    model1.move(3, 1, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 2));

    // tests a positive vertical move
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(1, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 2));
    model1.move(1, 2, 3, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(1, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(2, 2));

    // tests a negative vertical move
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(1, 1));
    model1.move(0, 0, 2, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(1, 1));

    // tests a negative vertical move
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(3, 2));
    model1.move(4, 2, 2, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(4, 2));

    //tests a move in the European-specific area of the board
  }

  /**
   * Tests the isGameOver method.
   */
  @Test
  public void testIsGameOver() {

    // tests whether is game over realizes the game is in fact over
    init();
    assertFalse(model1.isGameOver());

    model1.move(5, 3, 3, 3);
    model1.move(4, 5, 4, 3);
    model1.move(6, 4, 4, 4);
    model1.move(6, 2, 6, 4);
    model1.move(3, 4, 5, 4);
    model1.move(6, 4, 4, 4);
    model1.move(1, 4, 3, 4);
    model1.move(2, 6, 2, 4);
    model1.move(4, 6, 2, 6);
    model1.move(2, 3, 2, 5);
    model1.move(2, 6, 2, 4);
    model1.move(2, 1, 2, 3);
    model1.move(0, 2, 2, 2);
    model1.move(0, 4, 0, 2);
    model1.move(3, 2, 1, 2);
    model1.move(0, 2, 2, 2);
    model1.move(5, 2, 3, 2);
    model1.move(4, 0, 4, 2);
    model1.move(2, 0, 4, 0);
    model1.move(4, 3, 4, 1);
    model1.move(4, 0, 4, 2);
    model1.move(2, 3, 2, 1);
    model1.move(2, 1, 4, 1);
    model1.move(4, 1, 4, 3);
    model1.move(4, 3, 4, 5);
    model1.move(4, 5, 2, 5);
    model1.move(2, 5, 2, 3);
    model1.move(3, 3, 3, 5);
    model1.move(1, 3, 3, 3);
    model1.move(3, 2, 3, 4);
    model1.move(3, 5, 3, 3);

    assertTrue(model1.isGameOver());

  }

  /**
   * Tests the getBoardSize method.
   */
  @Test
  public void testGetBoardSize() {
    assertEquals(7, model1.getBoardSize());
    assertEquals(19, model2.getBoardSize());
    assertEquals(7, model3.getBoardSize());
    assertEquals(13, model4.getBoardSize());
    assertEquals(7, model5.getBoardSize());
  }

  /**
   * Tests the getScore method.
   */
  @Test
  public void testGetScore() {
    init();
    assertEquals(36, model1.getScore());
    assertEquals(276, model2.getScore());
    assertEquals(36, model3.getScore());
    assertEquals(128, model4.getScore());
    assertEquals(36, model5.getScore());
  }

  /**
   * Tests the getSlotAt method.
   */
  @Test
  public void testGetSlotAt() {
    try {
      model1.getSlotAt(-5, -3);
    } catch (IllegalArgumentException gs1) {
      assertEquals("Out of bounds.", gs1.getMessage());
    }

    try {
      model4.getSlotAt(-5, 3);
    } catch (IllegalArgumentException gs1) {
      assertEquals("Out of bounds.", gs1.getMessage());
    }

    try {
      model4.getSlotAt(100, 100);
    } catch (IllegalArgumentException gs1) {
      assertEquals("Out of bounds.", gs1.getMessage());
    }

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model2.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model2.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model3.getSlotAt(5, 3));
  }

  /**
   * Tests the constructor(s) for valid inputs.
   */
  @Test
  public void testValidConstructor() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model2.getSlotAt(9, 9));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model3.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model4.getSlotAt(5, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model5.getSlotAt(4, 4));

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model2.getSlotAt(8, 9));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model3.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model4.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model5.getSlotAt(3, 4));

    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model2.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model3.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model4.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model5.getSlotAt(0, 0));

  }


}
