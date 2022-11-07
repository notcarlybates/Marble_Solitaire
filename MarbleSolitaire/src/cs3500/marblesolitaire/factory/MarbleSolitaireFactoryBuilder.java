package cs3500.marblesolitaire.factory;

/**
 * Factory builder for the three configurations of factories.
 */
public class MarbleSolitaireFactoryBuilder {
  private int scale;
  private int rowEmpty;
  private int columnEmpty;
  private final String name;

  /**
   * A method to build the kind of factory we need. It sets the default configuration based on the
   * string name input.
   *
   * @param name The name of the factory we want
   */
  public MarbleSolitaireFactoryBuilder(String name) {
    this.name = name;
    switch (name) {
      case "english":
      case "european":
        scale = 3;
        rowEmpty = 3;
        columnEmpty = 3;
        break;
      case "triangle":
        scale = 5;
        rowEmpty = 0;
        columnEmpty = 0;
        break;
      default: throw new IllegalArgumentException("Invalid model name");
    }
  }

  /**
   * To set the arm thickness or the length of the bottom row.
   *
   * @param scale The length of the arm thickness or bottom row
   * @return A factory builder.
   */
  public MarbleSolitaireFactoryBuilder armThickness(int scale) {
    this.scale = scale;
    return this;
  }

  /**
   * To set the empty row.
   *
   * @param rowEmpty The row we want the empty slot in
   * @return A factory builder.
   */
  public MarbleSolitaireFactoryBuilder rowEmpty(int rowEmpty) {
    this.rowEmpty = rowEmpty;
    return this;
  }

  /**
   * To set the empty column.
   *
   * @param columnEmpty The column we want the empty slot in
   * @return A factory builder.
   */
  public MarbleSolitaireFactoryBuilder columnEmpty(int columnEmpty) {
    this.columnEmpty = columnEmpty;
    return this;
  }

  /**
   * Builds the Factory that we need to build.
   *
   * @return A factory that we can get the mode-view pair from.
   */
  public MarbleSolitaireAbstractFactory build() {
    switch (name) {
      case "english":
        return new EnglishSolitaireFactory(scale, rowEmpty, columnEmpty);
      case "european":
        return new EuropeanSolitaireFactory(scale, rowEmpty, columnEmpty);
      case "triangle":
        return new TriangleSolitaireFactory(scale, rowEmpty, columnEmpty);
      default:
        throw new IllegalArgumentException("Invalid model name");
    }
  }
}
