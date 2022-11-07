import java.io.IOException;
import java.nio.CharBuffer;

/**
 * Throws IOExceptions for broken readables.
 */
public class BrokenReadable implements Readable {
  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException();
  }
}
