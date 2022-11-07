import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests the TriangleSolitaireModel class.
 */
public class TestTriangleSolitaireModel {

  private TriangleSolitaireModel model1;
  private TriangleSolitaireModel model2;
  private TriangleSolitaireModel model3;
  private TriangleSolitaireModel model4;
  private TriangleSolitaireModel model5;
  private TriangleSolitaireModel model6;


  /**
   * Initializes the models.
   */
  @Before
  public void init() {
    model1 = new TriangleSolitaireModel();
    model2 = new TriangleSolitaireModel(7);
    model3 = new TriangleSolitaireModel(0, 0);
    model4 = new TriangleSolitaireModel(5, 0, 0);
    model5 = new TriangleSolitaireModel(3, 0, 0);
    model6 = new TriangleSolitaireModel(5, 2, 0);


  }

  /**
   * Tests incorrect supplied arm thicknesses with the constructor.
   */
  @Test
  public void testArmThickness() {
    try {
      TriangleSolitaireModel aT1 = new TriangleSolitaireModel(1);
      fail("Invalid arm thickness");
    } catch (IllegalArgumentException aT1) {
      assertEquals("Arm thickness is invalid.", aT1.getMessage());
    }
    try {
      TriangleSolitaireModel aT0 = new TriangleSolitaireModel(0);
      fail("Invalid arm thickness");
    } catch (IllegalArgumentException aT1) {
      assertEquals("Arm thickness is invalid.", aT1.getMessage());
    }
    try {
      TriangleSolitaireModel aTMinus1 = new TriangleSolitaireModel(-1);
      fail("Invalid arm thickness");
    } catch (IllegalArgumentException aTMinus1) {
      assertEquals("Arm thickness is invalid.", aTMinus1.getMessage());
    }

  }

  /**
   * Tests incorrect supplied empty space with the constructor.
   */
  @Test
  public void testEmptySpace() {
    try {
      TriangleSolitaireModel eS1 = new TriangleSolitaireModel(-1, 2);
      fail("Invalid empty space");
    } catch (IllegalArgumentException eS1) {
      assertEquals("Invalid empty cell position (-1,2)", eS1.getMessage());
    }
    try {
      TriangleSolitaireModel eS2 = new TriangleSolitaireModel(9, 9);
      fail("Invalid empty space");
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
      TriangleSolitaireModel ate1 = new TriangleSolitaireModel(-1, 3, 3);
      fail("Invalid empty space and/or arm thickness");
    } catch (IllegalArgumentException ate1) {
      assertEquals("Arm thickness is invalid.", ate1.getMessage());
    }
    try {
      TriangleSolitaireModel ate2 = new TriangleSolitaireModel(3, -1, -1);
      fail("Invalid empty space and/or arm thickness");
    } catch (IllegalArgumentException ate2) {
      assertEquals("Invalid empty cell position (-1,-1)", ate2.getMessage());
    }
    try {
      TriangleSolitaireModel ate3 = new TriangleSolitaireModel(2, 2, 2);
      fail("Invalid empty space and/or arm thickness");
    } catch (IllegalArgumentException ate3) {
      assertEquals("Invalid empty cell position (2,2)", ate3.getMessage());
    }
  }

  /**
   * Tests the constructor(s) for valid inputs.
   */
  @Test
  public void testValidConstructor() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model2.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model3.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model5.getSlotAt(0, 0));

    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model2.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model3.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model5.getSlotAt(1, 1));

    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(1, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model2.getSlotAt(2, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model3.getSlotAt(2, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model5.getSlotAt(0, 1));

  }

  /**
   * Tests the move method.
   */
  @Test
  public void testMove() {


    // tests invalid moves
    try {
      model1.move(-1, -1, 0, 0);
      fail("bad move");
    } catch (IllegalArgumentException m) {
      assertEquals("Invalid move.", m.getMessage());
    }

    try {
      model1.move(0, 2, 0, 0);
      fail("bad move");
    } catch (IllegalArgumentException m) {
      assertEquals("Invalid move.", m.getMessage());
    }

    try {
      model6.move(3, 0, 1, 0);
      fail("bad move");
    } catch (IllegalArgumentException m) {
      assertEquals("Invalid move.", m.getMessage());
    }


    // tests a positive vertical move
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(1, 0));
    model1.move(2, 0, 0, 0);


    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(1, 0));

    // tests a positive horizontal move
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 2));
    model1.move(2, 2, 2, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(2, 1));
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
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(3, 2));
    model1.move(4, 3, 2, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 2));


    // tests a negative horizontal move
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(4, 2));
    model1.move(4, 1, 4, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(4, 2));


    // tests a negative horizontal move
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(3, 1));
    model1.move(2, 1, 4, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 1));
  }

  /**
   * Tests the isGameOver method.
   */
  @Test
  public void testIsGameOver() {

    // tests premature game end
    assertFalse(model1.isGameOver());

    model1.move(2, 0, 0, 0);
    model1.move(2, 2, 2, 0);
    model1.move(0, 0, 2, 2);
    model1.move(4, 3, 2, 1);
    model1.move(4, 1, 4, 3);
    model1.move(2, 1, 4, 1);
    model1.move(3, 0, 1, 0);
    model1.move(3, 3, 1, 1);
    model1.move(4, 4, 4, 2);
    model1.move(4, 1, 4, 3);

    assertTrue(model1.isGameOver());


  }

  /**
   * Tests the getBoardSize method.
   */
  @Test
  public void testGetBoardSize() {
    assertEquals(5, model1.getBoardSize());
    assertEquals(7, model2.getBoardSize());
    assertEquals(5, model3.getBoardSize());
    assertEquals(5, model4.getBoardSize());
    assertEquals(3, model5.getBoardSize());
  }

  /**
   * Tests the getScore method.
   */
  @Test
  public void testGetScore() {
    init();
    assertEquals(14, model1.getScore());
    assertEquals(27, model2.getScore());
    assertEquals(14, model3.getScore());
    assertEquals(14, model4.getScore());
    assertEquals(5, model5.getScore());
  }

  /**
   * Tests the getSlotAt method.
   */
  @Test
  public void testGetSlotAt() {
    try {
      model1.getSlotAt(-5, -3);
      fail("bad slot");
    } catch (IllegalArgumentException gs1) {
      assertEquals("Out of bounds.", gs1.getMessage());
    }

    try {
      model4.getSlotAt(-5, 3);
      fail("bad slot");
    } catch (IllegalArgumentException gs1) {
      assertEquals("Out of bounds.", gs1.getMessage());
    }

    try {
      model4.getSlotAt(100, 100);
      fail("bad slot");
    } catch (IllegalArgumentException gs1) {
      assertEquals("Out of bounds.", gs1.getMessage());
    }

    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model1.getSlotAt(2, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model2.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model3.getSlotAt(0, 0));
  }


}
