import org.junit.Test;

import java.io.InputStreamReader;
import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the Marble Solitaire Game controller implementation
 * and its interactions with controller and view.
 */
public class TestMarbleSolitaireControllerImpl {

  @Test
  public void testNullConstructorInput() {
    MarbleSolitaireModel game = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(game);
    Readable read = new InputStreamReader(System.in);

    try {
      new MarbleSolitaireControllerImpl(null, view, read);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Null model.", e.getMessage());
    }

    try {
      new MarbleSolitaireControllerImpl(game, null, read);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Null view.", e.getMessage());
    }

    try {
      new MarbleSolitaireControllerImpl(game, view, null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Null readable.", e.getMessage());
    }

    try {
      new MarbleSolitaireControllerImpl(game, null, null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Null view.", e.getMessage());
    }

    try {
      new MarbleSolitaireControllerImpl(null, view, null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Null model.", e.getMessage());
    }

    try {
      new MarbleSolitaireControllerImpl(null, null, read);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Null model.", e.getMessage());
    }

    try {
      new MarbleSolitaireControllerImpl(null, null, null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Null model.", e.getMessage());
    }

  }

  @Test
  public void testIncorrectInput4() {
    StringReader readInput = new StringReader("1 2 3 -1");
    StringBuilder appendable = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            readInput);

    try {
      controller.playGame();
      assertEquals("    O O O\n" +
          "    O O O\n" +
          "O O O O O O O\n" +
          "O O O _ O O O\n" +
          "O O O O O O O\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 32\n" +
          "1 2 3 -1\n" +
          "Invalid entry. Try again.\n", appendable.toString());
    } catch (IllegalStateException e) {
      assertEquals("Unable to successfully receive input", e.getMessage());
    }
  }

  @Test
  public void testBadOutput() {
    String userInput = "4 2 4 4";
    StringReader readInput = new StringReader(userInput);
    BrokenAppendable appendable = new BrokenAppendable();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            readInput);
    try {
      controller.playGame();
    } catch (IllegalStateException e) {
      assertEquals("Unable to successfully transmit output", e.getMessage());
    }
  }

  @Test
  public void testBadInput() {
    String userInput = "4 2 4 4";
    Readable readInput = new BrokenReadable();
    StringBuilder appendable = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
        readInput);
    try {
      controller.playGame();
    } catch (IllegalStateException e) {
      assertEquals("Unable to successfully receive input", e.getMessage());
    }
  }

  @Test
  public void testIncorrectInput() {

    StringReader readInput = new StringReader("-1");
    StringBuilder appendable = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            readInput);

    try {
      controller.playGame();

      assertEquals("    O O O\n" +
          "    O O O\n" +
          "O O O O O O O\n" +
          "O O O _ O O O\n" +
          "O O O O O O O\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 32\n" +
          "-1\n" +
          "Invalid entry. Try again.\n", appendable.toString());
    } catch (IllegalStateException e) {
      assertEquals("Unable to successfully receive input", e.getMessage());
    }
  }

  @Test
  public void testIncorrectInput2() {
    StringReader readInput = new StringReader("4 2 4 -1 4");
    StringBuilder appendable = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            readInput);
    try {
      controller.playGame();

      assertEquals("    O O O\n" +
          "    O O O\n" +
          "O O O O O O O\n" +
          "O O O _ O O O\n" +
          "O O O O O O O\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 32\n" +
          "4 2 4\n" +
          "-1\n" +
          "Invalid entry. Try again.\n" +
          "4\n" +
          "    O O O\n" +
          "    O O O\n" +
          "O O O O O O O\n" +
          "O _ _ O O O O\n" +
          "O O O O O O O\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 31", appendable.toString());
      fail("This shouldn't work");
    } catch (IllegalStateException e) {
      assertEquals("Unable to successfully receive input", e.getMessage());
    }
  }

  @Test
  public void testIncorrectInput3() {
    StringReader readInput = new StringReader("1 2 -1");
    StringBuilder appendable = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            readInput);
    try {
      controller.playGame();

      assertEquals("    O O O\n" +
          "    O O O\n" +
          "O O O O O O O\n" +
          "O O O _ O O O\n" +
          "O O O O O O O\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 32\n" +
          "1 2 -1\n" +
          "Invalid entry. Try again.\n", appendable.toString());
      fail("This shouldn't work");
    } catch (IllegalStateException e) {
      assertEquals("Unable to successfully receive input", e.getMessage());
    }
  }

  @Test
  public void testInsufficientInputs() {
    StringReader readInput = new StringReader("1 2");
    StringBuilder appendable = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            readInput);

    try {
      controller.playGame();
    } catch (IllegalStateException e) {
      assertEquals("Unable to successfully receive input", e.getMessage());
    }
  }

  @Test
  public void testQuitFirst() {
    StringReader readInput = new StringReader("q");
    StringBuilder appendable = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            readInput);

    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", appendable.toString());
  }

  @Test
  public void testQuitSecond() {
    StringReader readInput = new StringReader(" 1 q");
    StringBuilder appendable = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            readInput);

    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", appendable.toString());
  }

  @Test
  public void testQuitThird() {
    StringReader readInput = new StringReader("1 2 q");
    StringBuilder appendable = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            readInput);

    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", appendable.toString());
  }

  @Test
  public void testQuitFourth() {
    StringReader readInput = new StringReader("1 2 3 q");
    StringBuilder appendable = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            readInput);

    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", appendable.toString());
  }

  @Test
  public void testGameOver() {
    StringReader readInput = new StringReader(
            "6 4 4 4\n" +
                    "3 4 5 4\n" +
                    "4 2 4 4\n" +
                    "4 5 4 3\n" +
                    "4 7 4 5\n" +
                    "1 4 3 4\n");
    StringBuilder appendable = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            readInput);

    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O _ O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O _ O O O\n" +
            "O _ O _ _ O O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O _ O O O\n" +
            "O _ O _ O _ _\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "Game over!\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O _ O _ O _ _\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 26\n", appendable.toString());
  }

  @Test
  public void testGameWin() {
    StringReader readInput = new StringReader(
            "2 4 4 4\n" +
                    "3 6 3 4\n" +
                    "1 5 3 5\n" +
                    "1 3 1 5\n" +
                    "4 5 2 5\n" +
                    "1 5 3 5\n" +
                    "6 5 4 5\n" +
                    "5 7 5 5\n" +
                    "3 7 5 7\n" +
                    "5 4 5 6\n" +
                    "5 7 5 5\n" +
                    "5 2 5 4\n" +
                    "7 3 5 3\n" +
                    "7 5 7 3\n" +
                    "4 3 6 3\n" +
                    "7 3 5 3\n" +
                    "2 3 4 3\n" +
                    "3 1 3 3\n" +
                    "5 1 3 1\n" +
                    "3 4 3 2\n" +
                    "3 1 3 3\n" +
                    "5 4 5 6\n" +
                    "5 6 3 6\n" +
                    "3 6 3 4\n" +
                    "3 4 3 2\n" +
                    "3 2 5 2\n" +
                    "5 2 5 4\n" +
                    "4 4 4 2\n" +
                    "6 4 4 4\n" +
                    "4 5 4 3\n" +
                    "4 2 4 4\n");
    StringBuilder appendable = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            readInput);

    controller.playGame();
    assertEquals("    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 32\n" +
        "    O O O\n" +
        "    O _ O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 31\n" +
        "    O O O\n" +
        "    O _ O\n" +
        "O O O O _ _ O\n" +
        "O O O O O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 30\n" +
        "    O O _\n" +
        "    O _ _\n" +
        "O O O O O _ O\n" +
        "O O O O O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 29\n" +
        "    _ _ O\n" +
        "    O _ _\n" +
        "O O O O O _ O\n" +
        "O O O O O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 28\n" +
        "    _ _ O\n" +
        "    O _ O\n" +
        "O O O O _ _ O\n" +
        "O O O O _ O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 27\n" +
        "    _ _ _\n" +
        "    O _ _\n" +
        "O O O O O _ O\n" +
        "O O O O _ O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 26\n" +
        "    _ _ _\n" +
        "    O _ _\n" +
        "O O O O O _ O\n" +
        "O O O O O O O\n" +
        "O O O O _ O O\n" +
        "    O O _\n" +
        "    O O O\n" +
        "Score: 25\n" +
        "    _ _ _\n" +
        "    O _ _\n" +
        "O O O O O _ O\n" +
        "O O O O O O O\n" +
        "O O O O O _ _\n" +
        "    O O _\n" +
        "    O O O\n" +
        "Score: 24\n" +
        "    _ _ _\n" +
        "    O _ _\n" +
        "O O O O O _ _\n" +
        "O O O O O O _\n" +
        "O O O O O _ O\n" +
        "    O O _\n" +
        "    O O O\n" +
        "Score: 23\n" +
        "    _ _ _\n" +
        "    O _ _\n" +
        "O O O O O _ _\n" +
        "O O O O O O _\n" +
        "O O O _ _ O O\n" +
        "    O O _\n" +
        "    O O O\n" +
        "Score: 22\n" +
        "    _ _ _\n" +
        "    O _ _\n" +
        "O O O O O _ _\n" +
        "O O O O O O _\n" +
        "O O O _ O _ _\n" +
        "    O O _\n" +
        "    O O O\n" +
        "Score: 21\n" +
        "    _ _ _\n" +
        "    O _ _\n" +
        "O O O O O _ _\n" +
        "O O O O O O _\n" +
        "O _ _ O O _ _\n" +
        "    O O _\n" +
        "    O O O\n" +
        "Score: 20\n" +
        "    _ _ _\n" +
        "    O _ _\n" +
        "O O O O O _ _\n" +
        "O O O O O O _\n" +
        "O _ O O O _ _\n" +
        "    _ O _\n" +
        "    _ O O\n" +
        "Score: 19\n" +
        "    _ _ _\n" +
        "    O _ _\n" +
        "O O O O O _ _\n" +
        "O O O O O O _\n" +
        "O _ O O O _ _\n" +
        "    _ O _\n" +
        "    O _ _\n" +
        "Score: 18\n" +
        "    _ _ _\n" +
        "    O _ _\n" +
        "O O O O O _ _\n" +
        "O O _ O O O _\n" +
        "O _ _ O O _ _\n" +
        "    O O _\n" +
        "    O _ _\n" +
        "Score: 17\n" +
        "    _ _ _\n" +
        "    O _ _\n" +
        "O O O O O _ _\n" +
        "O O _ O O O _\n" +
        "O _ O O O _ _\n" +
        "    _ O _\n" +
        "    _ _ _\n" +
        "Score: 16\n" +
        "    _ _ _\n" +
        "    _ _ _\n" +
        "O O _ O O _ _\n" +
        "O O O O O O _\n" +
        "O _ O O O _ _\n" +
        "    _ O _\n" +
        "    _ _ _\n" +
        "Score: 15\n" +
        "    _ _ _\n" +
        "    _ _ _\n" +
        "_ _ O O O _ _\n" +
        "O O O O O O _\n" +
        "O _ O O O _ _\n" +
        "    _ O _\n" +
        "    _ _ _\n" +
        "Score: 14\n" +
        "    _ _ _\n" +
        "    _ _ _\n" +
        "O _ O O O _ _\n" +
        "_ O O O O O _\n" +
        "_ _ O O O _ _\n" +
        "    _ O _\n" +
        "    _ _ _\n" +
        "Score: 13\n" +
        "    _ _ _\n" +
        "    _ _ _\n" +
        "O O _ _ O _ _\n" +
        "_ O O O O O _\n" +
        "_ _ O O O _ _\n" +
        "    _ O _\n" +
        "    _ _ _\n" +
        "Score: 12\n" +
        "    _ _ _\n" +
        "    _ _ _\n" +
        "_ _ O _ O _ _\n" +
        "_ O O O O O _\n" +
        "_ _ O O O _ _\n" +
        "    _ O _\n" +
        "    _ _ _\n" +
        "Score: 11\n" +
        "    _ _ _\n" +
        "    _ _ _\n" +
        "_ _ O _ O _ _\n" +
        "_ O O O O O _\n" +
        "_ _ O _ _ O _\n" +
        "    _ O _\n" +
        "    _ _ _\n" +
        "Score: 10\n" +
        "    _ _ _\n" +
        "    _ _ _\n" +
        "_ _ O _ O O _\n" +
        "_ O O O O _ _\n" +
        "_ _ O _ _ _ _\n" +
        "    _ O _\n" +
        "    _ _ _\n" +
        "Score: 9\n" +
        "    _ _ _\n" +
        "    _ _ _\n" +
        "_ _ O O _ _ _\n" +
        "_ O O O O _ _\n" +
        "_ _ O _ _ _ _\n" +
        "    _ O _\n" +
        "    _ _ _\n" +
        "Score: 8\n" +
        "    _ _ _\n" +
        "    _ _ _\n" +
        "_ O _ _ _ _ _\n" +
        "_ O O O O _ _\n" +
        "_ _ O _ _ _ _\n" +
        "    _ O _\n" +
        "    _ _ _\n" +
        "Score: 7\n" +
        "    _ _ _\n" +
        "    _ _ _\n" +
        "_ _ _ _ _ _ _\n" +
        "_ _ O O O _ _\n" +
        "_ O O _ _ _ _\n" +
        "    _ O _\n" +
        "    _ _ _\n" +
        "Score: 6\n" +
        "    _ _ _\n" +
        "    _ _ _\n" +
        "_ _ _ _ _ _ _\n" +
        "_ _ O O O _ _\n" +
        "_ _ _ O _ _ _\n" +
        "    _ O _\n" +
        "    _ _ _\n" +
        "Score: 5\n" +
        "    _ _ _\n" +
        "    _ _ _\n" +
        "_ _ _ _ _ _ _\n" +
        "_ O _ _ O _ _\n" +
        "_ _ _ O _ _ _\n" +
        "    _ O _\n" +
        "    _ _ _\n" +
        "Score: 4\n" +
        "    _ _ _\n" +
        "    _ _ _\n" +
        "_ _ _ _ _ _ _\n" +
        "_ O _ O O _ _\n" +
        "_ _ _ _ _ _ _\n" +
        "    _ _ _\n" +
        "    _ _ _\n" +
        "Score: 3\n" +
        "    _ _ _\n" +
        "    _ _ _\n" +
        "_ _ _ _ _ _ _\n" +
        "_ O O _ _ _ _\n" +
        "_ _ _ _ _ _ _\n" +
        "    _ _ _\n" +
        "    _ _ _\n" +
        "Score: 2\n" +
        "Game over!\n" +
        "    _ _ _\n" +
        "    _ _ _\n" +
        "_ _ _ _ _ _ _\n" +
        "_ _ _ O _ _ _\n" +
        "_ _ _ _ _ _ _\n" +
        "    _ _ _\n" +
        "    _ _ _\n" +
        "Score: 1\n", appendable.toString());
  }


}
