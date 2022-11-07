import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;

import static org.junit.Assert.assertEquals;

/**
 * Tests the methods and integrations in English Solitaire Model.
 */
public class TestEnglishSolitaireModel {

  private EnglishSolitaireModel model1;
  private EnglishSolitaireModel model2;
  private EnglishSolitaireModel model3;
  private EnglishSolitaireModel model4;
  private EnglishSolitaireModel model5;

  @Before
  public void init() {
    model1 = new EnglishSolitaireModel();
    model2 = new EnglishSolitaireModel(7);
    model3 = new EnglishSolitaireModel(5, 3);
    model4 = new EnglishSolitaireModel(5, 5, 4);
    model5 = new EnglishSolitaireModel(3, 4, 4);
  }

  @Test
  public void testArmThickness() {
    try {
      EnglishSolitaireModel aT1 = new EnglishSolitaireModel(1);
    } catch (IllegalArgumentException aT1) {
      assertEquals("Arm thickness is invalid.", aT1.getMessage());
    }
    try {
      EnglishSolitaireModel aT0 = new EnglishSolitaireModel(0);
    } catch (IllegalArgumentException aT1) {
      assertEquals("Arm thickness is invalid.", aT1.getMessage());
    }
    try {
      EnglishSolitaireModel aTMinus1 = new EnglishSolitaireModel(-1);
    } catch (IllegalArgumentException aTMinus1) {
      assertEquals("Arm thickness is invalid.", aTMinus1.getMessage());
    }
    try {
      EnglishSolitaireModel aT10 = new EnglishSolitaireModel(10);
    } catch (IllegalArgumentException aT10) {
      assertEquals("Arm thickness is invalid.", aT10.getMessage());
    }

  }

  @Test
  public void testEmptySpace() {
    try {
      EnglishSolitaireModel eS1 = new EnglishSolitaireModel(-1, 2);
    } catch (IllegalArgumentException eS1) {
      assertEquals("Invalid empty cell position (-1,2)", eS1.getMessage());
    }
    try {
      EnglishSolitaireModel eS2 = new EnglishSolitaireModel(9, 9);
    } catch (IllegalArgumentException eS2) {
      assertEquals("Invalid empty cell position (9,9)", eS2.getMessage());
    }

  }

  @Test
  public void testATAndEmptySpace() {
    try {
      EnglishSolitaireModel ate1 = new EnglishSolitaireModel(-1, 3, 3);
    } catch (IllegalArgumentException ate1) {
      assertEquals("Arm thickness is invalid.", ate1.getMessage());
    }
    try {
      EnglishSolitaireModel ate2 = new EnglishSolitaireModel(3, -1, -1);
    } catch (IllegalArgumentException ate2) {
      assertEquals("Invalid empty cell position (-1,-1)", ate2.getMessage());
    }
    try {
      EnglishSolitaireModel ate3 = new EnglishSolitaireModel(2, 1, 1);
    } catch (IllegalArgumentException ate3) {
      assertEquals("Arm thickness is invalid.", ate3.getMessage());
    }
  }

  @Test
  public void testMove() {

    try {
      model1.move(1, 1, 3, 3);
    } catch (IllegalArgumentException m) {
      assertEquals("Invalid move.", m.getMessage());
    }

    try {
      model5.move(1, 3, 5, 3);
    } catch (IllegalArgumentException m) {
      assertEquals("Invalid move.", m.getMessage());
    }

    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(3, 2));
    model1.move(3, 1, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 2));

    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(1, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 2));
    model1.move(1, 2, 3, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(1, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(2, 2));

    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model5.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model5.getSlotAt(4, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model5.getSlotAt(4, 5));
    model5.move(4, 6, 4, 4);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model5.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model5.getSlotAt(4, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model5.getSlotAt(4, 6));

    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(3, 2));
    model1.move(4, 2, 2, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(4, 2));
  }

  @Test
  public void testIsGameOver() {

    assertEquals(false, model1.isGameOver());

    model1.move(5, 3, 3, 3);
    model1.move(2, 3, 4, 3);
    model1.move(3, 1, 3, 3);
    model1.move(3, 4, 3, 2);
    model1.move(3, 6, 3, 4);
    model1.move(0, 3, 2, 3);
    assertEquals(true, model1.isGameOver());

    init();
    assertEquals(false, model1.isGameOver());

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

    assertEquals(true, model1.isGameOver());

  }

  @Test
  public void testGetBoardSize() {
    assertEquals(7, model1.getBoardSize());
    assertEquals(19, model2.getBoardSize());
    assertEquals(7, model3.getBoardSize());
    assertEquals(13, model4.getBoardSize());
    assertEquals(7, model5.getBoardSize());
  }

  @Test
  public void testGetScore() {
    init();
    assertEquals(32, model1.getScore());
    assertEquals(216, model2.getScore());
    assertEquals(32, model3.getScore());
    assertEquals(104, model4.getScore());
    assertEquals(32, model5.getScore());
  }

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
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, model2.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model3.getSlotAt(5, 3));
  }

  @Test
  public void testValidConstructor() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model2.getSlotAt(9, 9));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model3.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model4.getSlotAt(5, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model5.getSlotAt(4, 4));

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
